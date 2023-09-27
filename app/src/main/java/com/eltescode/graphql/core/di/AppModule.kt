package com.eltescode.graphql.core.di

import com.apollographql.apollo3.ApolloClient
import com.eltescode.graphql.feature.country.data.ApolloCountryClient
import com.eltescode.graphql.feature.country.domain.CountryClient
import com.eltescode.graphql.feature.country.domain.use_case.GetCountriesUseCase
import com.eltescode.graphql.feature.country.domain.use_case.GetCountryUseCase
import com.eltescode.graphql.feature.country.domain.use_case.UseCases
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
    fun provideApolloClient(): ApolloClient{
        return ApolloClient.Builder().serverUrl("https://countries.trevorblades.com/graphql").build()
    }

    @Provides
    @Singleton
    fun provideCountryClient(apolloClient:ApolloClient): CountryClient {
        return ApolloCountryClient(apolloClient)
    }

    @Provides
    @Singleton
    fun provideUseCases(countryClient: CountryClient): UseCases {
        return UseCases(
            getCountriesUseCase = GetCountriesUseCase(countryClient),
            getCountryUseCase = GetCountryUseCase(countryClient)
        )
    }
}