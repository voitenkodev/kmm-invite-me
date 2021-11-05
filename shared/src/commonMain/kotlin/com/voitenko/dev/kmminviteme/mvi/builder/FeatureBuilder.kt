package com.voitenko.dev.kmminviteme.mvi.builder

import com.voitenko.dev.kmminviteme.mvi.feature.Feature
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

public interface FeatureTag

@OptIn(FlowPreview::class)
public interface FeatureBuilder<Model> {

    /**
     * Last state of screen [Model]
     **/

    public val state: StateFlow<Model>

    /**
     * @param [FeatureTag] Identifier -> any enum class which extend [FeatureTag]
     * @param [Wish] any 'wish class' which extend [Feature.Wish]
     **/

    public fun <Wish : Feature.Wish> want(tag: FeatureTag, wish: Wish)

    /**
     * @param [FeatureTag] Identifier -> any enum class which extend [FeatureTag]
     * @return [Flow] with [News] -> any class which extend [Feature.News]
     **/

    public fun <News : Feature.News> news(tag: FeatureTag): Flow<News>

    /**
     * @param lambda with  [FeatureBuilder] with [Model]
     **/

    public fun <State : Feature.State> obtain(
        tag: FeatureTag,
        obtain: FeatureBuilder<Model>.(State) -> Unit
    ): FeatureBuilder<Model>

    /**
     * @param [FeatureTag] Identifier -> any enum class which extend [FeatureTag]
     **/

    public interface Builder<Model> {
        public fun <Wish : Feature.Wish, State : Feature.State, New : Feature.News> provide(
            tag: FeatureTag,
            feature: CoroutineScope.(Model) -> Pair<Feature<Wish, State, New>, Model.(State) -> Model>
        ): Builder<Model>

        public fun build(): FeatureBuilder<Model>
    }
}