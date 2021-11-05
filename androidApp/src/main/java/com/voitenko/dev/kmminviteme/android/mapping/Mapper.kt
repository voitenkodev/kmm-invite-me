//package com.voitenko.dev.invitemekmm.mapping
//
//import org.koin.core.component.KoinComponent
//
//
///**
// * Mapping objects
// * @param M (Api Model)
// * @param P (Application model)
// */
//internal abstract class Mapper<M, P> : KoinComponent {
//    abstract fun map(model: M): P?
//    abstract fun inverseMap(model: P): M?
//}