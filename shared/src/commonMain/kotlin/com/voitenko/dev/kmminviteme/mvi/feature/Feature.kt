package com.voitenko.dev.kmminviteme.mvi.feature

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*

/*
class ExampleFeature(scope: CoroutineScope) : Feature<
        ExampleFeature.Wish,
        ExampleFeature.Effect,
        ExampleFeature.State,
        ExampleFeature.News>(
    scope = scope,
    initial = State(),
    actor = ActorImpl(),
    reducer = ReducerImpl(),
    newsPublisher = NewsPublisherImpl()
) {
    sealed class Wish : FWish {}
    sealed class Effect : FEffect {}
    sealed class News : FNews {}
    data class State() : FState
    class ActorImpl : Actor<Wish, Effect, State> {}
    class ReducerImpl : Reducer<Effect, State> {}
    class NewsPublisherImpl : NewsPublisherImpl<Effect, State, News> {}
}
*/

public abstract class Feature<Wish : Feature.Wish, State : Feature.State, News : Feature.News>(
    private val scope: CoroutineScope,
    initial: State,
    private val actor: Actor<Wish, State>,
    private val reducer: Reducer<Wish, State>,
    private val newsPublisher: (NewsPublisher<Wish, State, News>)? = null,
) {

    public interface Wish
    public interface State
    public interface News

    private val _state = MutableStateFlow(initial)
    public val state: StateFlow<State> get() = _state

    private val _news: Channel<News> = Channel(Channel.BUFFERED)
    public val news: Flow<News> = _news.receiveAsFlow()

    @FlowPreview
    public fun want(w: Wish) {
        flowOf(w)
            .flatMapConcat {
                it.className().log("[__Wish____]")
                actor.invoke(it, state.value)
            }.onEach {
                newsPublisher?.invoke(it, _state.value)?.let {
                    it.className().log("[__News____]")
                    _news.send(it)
                }
            }.map {
                reducer.invoke(it, _state.value)
            }.onEach {
                "${it.className().substringBefore("::")}::${it}".log("[__State___]")
                _state.emit(it)
            }.distinctUntilChanged()
            .catch {
                println("[__CRASH___] -> $it")
                throw it
            }
            .launchIn(scope)
    }

    private fun Any.log(msg: String) = println("$msg -> $this")
    private fun Any.className() = this::class.simpleName ?: "unknown"
}

public typealias Actor<Wish, State> = (wish: Wish, state: State) -> Flow<Wish>
public typealias Reducer<Wish, State> = (wish: Wish, state: State) -> State
public typealias NewsPublisher<Wish, State, News> = (wish: Wish, state: State) -> News?
