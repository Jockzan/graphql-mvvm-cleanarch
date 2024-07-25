package com.jcb.graphqlapp.domain.usecases

import com.jcb.graphqlapp.domain.datasource.CountryClient
import com.jcb.graphqlapp.domain.entities.DetailCountry

class GetCountryUseCase(
    private val countryClient: CountryClient,
) {
    suspend fun execute(code: String): DetailCountry? =
        countryClient
            .getCountry(code)
}
