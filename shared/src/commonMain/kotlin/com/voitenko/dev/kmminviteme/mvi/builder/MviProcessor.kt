package com.voitenko.dev.kmminviteme.mvi.builder

import com.voitenko.dev.kmminviteme.mvi.feature.Feature
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*

public interface FeatureTag

@Suppress("UNCHECKED_CAST")
@OptIn(FlowPreview::class)
public class MviProcessor<ROOT> private constructor(
    root: ROOT,
    private val scope: CoroutineScope,
    private val featureMap: FeatureMap = hashMapOf(),
    private val pushMap: SharingMap<ROOT> = hashMapOf(),
    private val automaticallyMap: ObtainMap<ROOT> = hashMapOf()
) {

    private val _state = MutableStateFlow(root)
    public val state: StateFlow<ROOT> get() = _state.asStateFlow()

    init {
        scope.launchFeatures()
    }

    public fun <WISH : Feature.Wish> want(tag: FeatureTag, wish: WISH) {
        (featureMap[tag] as? Feature<WISH, *, *>)?.want(wish)?.launchIn(scope)
    }

    public fun <NEWS : Feature.News> news(tag: FeatureTag): Flow<NEWS>? =
        (featureMap[tag] as? Feature<*, *, NEWS>)?.news

    public fun <STATE : Feature.State> automatically(
        tag: FeatureTag, automatically: MviProcessor<ROOT>.(STATE) -> Unit
    ): MviProcessor<ROOT> = apply {
        (automatically as? MviProcessor<ROOT>.(Feature.State) -> Unit)
            ?.let { this.automaticallyMap[tag] = it }
    }

    @FlowPreview
    private fun CoroutineScope.launchFeatures() = featureMap.onEach { item ->
        item.value.state.onEach {
            automaticallyMap[item.key]?.invoke(this@MviProcessor, it)
            pushMap[item.key]?.invoke(state.value, it)?.let { _state.value = it }
        }.launchIn(this)
    }

    public data class FeatureProcessor<ROOT>(private val root: ROOT) {

        private val features: FeatureMap = hashMapOf()

        private val pushing: SharingMap<ROOT> = hashMapOf()

        public fun <WISH : Feature.Wish, STATE : Feature.State, NEWS : Feature.News> feature(
            tag: FeatureTag,
            feature: (ROOT) -> Pair<Feature<WISH, STATE, NEWS>, ROOT.(STATE) -> ROOT>
        ): FeatureProcessor<ROOT> = apply {
            val entry = feature.invoke(root)
            features[tag] = entry.first
            (entry.second as? ROOT.(Feature.State) -> ROOT)?.let { this.pushing[tag] = it }
        }

        public fun launchIn(scope: CoroutineScope): MviProcessor<ROOT> =
            MviProcessor(root, scope, features, pushing)
    }
}

@FlowPreview
private typealias FeatureMap = HashMap<FeatureTag, Feature<*, *, *>>
private typealias ObtainMap<ROOT> = HashMap<FeatureTag, MviProcessor<ROOT>.(Feature.State) -> Unit>
private typealias SharingMap<ROOT> = HashMap<FeatureTag, (ROOT, Feature.State) -> ROOT>
