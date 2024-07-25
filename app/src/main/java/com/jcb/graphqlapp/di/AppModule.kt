package com.jcb.graphqlapp.di

import com.apollographql.apollo3.ApolloClient
import com.jcb.graphqlapp.data.datasource.ApolloCountryClient
import com.jcb.graphqlapp.domain.datasource.CountryClient
import com.jcb.graphqlapp.domain.usecases.GetCountriesUSeCase
import com.jcb.graphqlapp.domain.usecases.GetCountryUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideApolloClient(): ApolloClient =
        ApolloClient
            .Builder()
            .serverUrl("https://countries.trevorblades.com/graphql")
            .build()
    // https://studio.apollographql.com/public/countries/variant/current/home

    @Provides
    @Singleton
    fun provideCountryClient(apolloClient: ApolloClient): CountryClient =
        ApolloCountryClient(apolloClient)

    @Provides
    @Singleton
    fun provideGetCountriesUseCase(countryClient: CountryClient): GetCountriesUSeCase =
        GetCountriesUSeCase(countryClient)

    @Provides
    @Singleton
    fun provideGetCountryUseCase(countryClient: CountryClient): GetCountryUseCase =
        GetCountryUseCase(countryClient)
}
