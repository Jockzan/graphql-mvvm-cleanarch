package com.jcb.graphqlapp.domain.usecases

import com.jcb.graphqlapp.domain.datasource.CountryClient
import com.jcb.graphqlapp.domain.entities.SimpleCountry

class GetCountriesUSeCase(
    private val countryClient: CountryClient,
) {
    suspend fun execute(): List<SimpleCountry> =
        countryClient
            .getCountries()
            .sortedBy { it.name }
}
