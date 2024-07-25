package com.jcb.graphqlapp.domain.datasource

import com.jcb.graphqlapp.domain.entities.DetailCountry
import com.jcb.graphqlapp.domain.entities.SimpleCountry

interface CountryClient {
    suspend fun getCountries(): List<SimpleCountry>

    suspend fun getCountry(code: String): DetailCountry?
}
