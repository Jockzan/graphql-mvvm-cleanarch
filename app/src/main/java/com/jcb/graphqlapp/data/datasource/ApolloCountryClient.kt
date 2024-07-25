package com.jcb.graphqlapp.data.datasource

import com.apollographql.apollo3.ApolloClient
import com.jcb.CountriesQuery
import com.jcb.CountryQuery
import com.jcb.graphqlapp.data.mappers.toDetailCountry
import com.jcb.graphqlapp.data.mappers.toSimpleCountry
import com.jcb.graphqlapp.domain.datasource.CountryClient
import com.jcb.graphqlapp.domain.entities.DetailCountry
import com.jcb.graphqlapp.domain.entities.SimpleCountry

class ApolloCountryClient(
    private val apolloClient: ApolloClient,
) : CountryClient {
    override suspend fun getCountries(): List<SimpleCountry> =
        apolloClient
            .query(CountriesQuery())
            .execute()
            .data
            ?.countries
            ?.map { it.toSimpleCountry() }
            ?: emptyList()

    override suspend fun getCountry(code: String): DetailCountry? =
        apolloClient
            .query(CountryQuery(code))
            .execute()
            .data
            ?.country
            ?.toDetailCountry()
}
