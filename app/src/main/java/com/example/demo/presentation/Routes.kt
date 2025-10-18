package com.example.demo.presentation

import kotlinx.serialization.Serializable

sealed interface Routes {
    @Serializable
    data object HoldingNav: Routes
}