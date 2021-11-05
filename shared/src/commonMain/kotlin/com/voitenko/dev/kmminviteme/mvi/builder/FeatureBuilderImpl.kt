package com.voitenko.dev.kmminviteme.mvi.builder

import com.voitenko.dev.kmminviteme.mvi.feature.Feature
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*

@OptIn(FlowPreview::class)
internal class FeatureBuilderImpl<Model> private constructor(
    initial: Model,
    scope: CoroutineScope,
    private val features: FeatureMap = hashMapOf(),
    private val reflectors: ReflectMap<Model> = hashMapOf(),
    private val obtain: ObtainMap<Model> = hashMapOf()
) : FeatureBuilder<Model> {

    private val _state = MutableStateFlow(initial)
    override val state: StateFlow<Model> get() = _state.asStateFlow()

    init {
        features.onEach { item -> item.launchFeatures(scope) }
    }

    override fun <Wish : Feature.Wish> want(tag: FeatureTag, wish: Wish) =
        from<Feature<Feature.Wish, *, *>>(tag).want(wish)

    override fun <News : Feature.News> news(tag: FeatureTag) =
        from<Feature<Feature.Wish, *, News>>(tag).news

    @Suppress("UNCHECKED_CAST")
    override fun <State : Feature.State> obtain(
        tag: FeatureTag, obtain: FeatureBuilder<Model>.(State) -> Unit
    ) = apply {
        (obtain as? FeatureBuilderImpl<Model>.(Feature.State) -> Unit)?.let {
            this.obtain[tag] = it
        }
    }

    internal data class BuilderImpl<Model>(
        val scope: CoroutineScope,
        val initial: Model,
        val features: FeatureMap = hashMapOf(),
        val reflection: ReflectMap<Model> = hashMapOf()
    ) : FeatureBuilder.Builder<Model> {

        @Suppress("UNCHECKED_CAST")
        override fun <Wish : Feature.Wish, State : Feature.State, New : Feature.News> provide(
            tag: FeatureTag,
            feature: CoroutineScope.(Model) -> Pair<Feature<Wish, State, New>, Model.(State) -> Model>
        ) = apply {
            val entry = feature.invoke(scope, initial)
            features[tag] = entry.first
            (entry.second as? Model.(Feature.State) -> Model)?.let { this.reflection[tag] = it }
        }

        override fun build() = FeatureBuilderImpl(initial, scope, features, reflection)
    }

    @FlowPreview
    private fun Map.Entry<FeatureTag, Feature<*, *, *>>.launchFeatures(scope: CoroutineScope) {
        value.state.onEach {
            obtain[key]?.invoke(this@FeatureBuilderImpl, it)
            reflectors[key]?.invoke(state.value, it)?.let { _state.value = it }
        }.launchIn(scope)
    }

    private inline fun <reified Feature> from(tag: FeatureTag) = (features[tag] as Feature)
}
@FlowPreview
private typealias ObtainMap<Model> = HashMap<FeatureTag, FeatureBuilderImpl<Model>.(Feature.State) -> Unit>
private typealias FeatureMap = HashMap<FeatureTag, Feature<*, *, *>>
private typealias ReflectMap<Model> = HashMap<FeatureTag, (Model, Feature.State) -> Model>