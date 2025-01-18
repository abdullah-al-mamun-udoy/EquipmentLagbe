package com.metamamun.equipment.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class Graph{
    @Serializable
    data object HomeGraph: Graph()
}
