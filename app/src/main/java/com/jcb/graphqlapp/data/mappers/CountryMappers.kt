package com.jcb.graphqlapp.data.mappers

import com.jcb.CountriesQuery
import com.jcb.CountryQuery
import com.jcb.graphqlapp.domain.entities.DetailCountry
import com.jcb.graphqlapp.domain.entities.SimpleCountry

fun CountryQuery.Country.toDetailCountry(): DetailCountry =
    DetailCountry(
        code = code,
        name = name,
        emoji = emoji,
        capital = capital ?: "No capital",
        currency = currency ?: "No currency",
        languages = languages.map { it.name },
        continent = continent.name,
    )

fun CountriesQuery.Country.toSimpleCountry(): SimpleCountry =
    SimpleCountry(
        code = code,
        name = name,
        emoji = emoji,
        capital = capital ?: "No capital",
    )
