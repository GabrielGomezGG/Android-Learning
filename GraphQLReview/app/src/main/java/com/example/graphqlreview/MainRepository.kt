package com.example.graphqlreview

import com.apollographql.apollo3.ApolloClient
import javax.inject.Inject

interface MainRepository {
    suspend fun getCountries(): List<Country>
}

class MainRepositoryImpl @Inject constructor(
    private val apolloClient : ApolloClient
) : MainRepository {

    override suspend fun getCountries(): List<Country> {
        val response = apolloClient.query(CountriesQuery()).execute()
        return response.data!!.countries.map { it.toCountry() }
    }

}