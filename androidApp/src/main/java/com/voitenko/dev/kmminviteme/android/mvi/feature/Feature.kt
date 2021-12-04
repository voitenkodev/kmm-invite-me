package mvi.feature

import android.util.Log
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import mvi.IncorrectFeatureByTag
import mvi.MissingActorException

public typealias AsyncReducer<Async, State, Sync> = (wish: Async, state: State) -> Flow<Sync>
public typealias SyncReducer<Sync, State> = (wish: Sync, state: State) -> State
public typealias EffectDistributor<Sync, State> = (sync: Sync, state: State) -> Flow<Feature.Wish>

@OptIn(FlowPreview::class)
public abstract class Feature<Async : Feature.Wish.Async, Sync : Feature.Wish.Sync, Side : Feature.Wish.Side, State : Feature.State>(
    initial: State,
    private val asyncReducer: AsyncReducer<Async, State, Sync>? = null,
    private val syncReducer: SyncReducer<Sync, State>,
    private val effectDistributor: (EffectDistributor<Sync, State>)? = null,
) {

    val _side: Channel<Side> = Channel(Channel.BUFFERED)
    public val side: Flow<Side> = _side.receiveAsFlow()

    private val _state = MutableStateFlow(initial)
    public val state: StateFlow<State> get() = _state.asStateFlow()

    public interface State

    public interface Wish {
        public interface Async : Wish
        public interface Sync : Wish
        public interface Side : Wish
    }

    public fun want(wish: Async): Flow<Unit> = flowOf(wish)
        .flatMapConcat { asyncReducer?.invoke(it, _state.value) ?: throw MissingActorException }
        .flatMapConcat { want(it) }
        .toUnit()

    public fun want(wish: Sync): Flow<Unit> = flowOf(wish)
        .map { syncReducer.invoke(it, _state.value) }
        .distinctUntilChanged()
        .onEach { _state.emit(it) }
        .catch { throw IncorrectFeatureByTag }
        .obtainDistribution(wish)
        .map { Log.d("showLog", "MAPPPPPPPPPPPP") }
        .toUnit()

    public fun want(wish: Side): Flow<Unit> = flowOf(wish)
        .map { _side.send(wish) }
        .toUnit()

    private fun Flow<Any>.obtainDistribution(sync: Sync): Flow<Any> = flatMapConcat {
        effectDistributor?.invoke(sync, _state.value)
            ?.flatMapConcat { distributeEffect(it) }
            ?: flowOf(it)
    }

    private fun Flow<Any>.toUnit() = this.map { }

    @FlowPreview
    @Suppress("UNCHECKED_CAST")
    private fun distributeEffect(w: Wish): Flow<Any> = (w as? Sync)?.let { want(it) }
        ?: (w as? Async)?.let { want(it) }
        ?: (w as? Side)?.let { want(it) }
        ?: flowOf()
}