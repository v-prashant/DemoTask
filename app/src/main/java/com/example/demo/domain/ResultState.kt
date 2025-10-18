package com.example.demo.domain

sealed interface ResultState<out T> {
    data class Success<T>(val data: T): ResultState<T>
    data class Error(val msg: String): ResultState<Nothing>
}