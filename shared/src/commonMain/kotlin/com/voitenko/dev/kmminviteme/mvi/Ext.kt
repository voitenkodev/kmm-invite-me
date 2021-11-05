package com.voitenko.dev.kmminviteme.mvi

import com.voitenko.dev.kmminviteme.mvi.builder.FeatureBuilder
import com.voitenko.dev.kmminviteme.mvi.builder.FeatureBuilderImpl
import com.voitenko.dev.kmminviteme.mvi.feature.Feature
import kotlinx.coroutines.CoroutineScope

/**
 * @param Model is data class of whole screen
 * @return [FeatureBuilder.Builder]  which responsible for adding [Feature] using method [FeatureBuilder.Builder.provide]
 **/

public fun <Model> CoroutineScope.createFeatureBuilder(m: Model): FeatureBuilder.Builder<Model> =
    FeatureBuilderImpl.BuilderImpl(this, m)

public inline infix fun <reified Model, State : Feature.State, reified F : Feature<*, *, *>> F.share(
    noinline l: Model.(State) -> Model
): Pair<F, Model.(State) -> Model> = Pair(this, l)