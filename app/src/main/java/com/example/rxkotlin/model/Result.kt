package com.example.rxkotlin.model

data class Result(
    val info: Info,
    val results: List<Result>
)