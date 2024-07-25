package com.jcb.graphqlapp.domain.entities

data class DetailCountry(
    val code: String,
    val name: String,
    val emoji: String,
    val capital: String,
    val currency: String,
    val languages: List<String>,
    val continent: String,
)
