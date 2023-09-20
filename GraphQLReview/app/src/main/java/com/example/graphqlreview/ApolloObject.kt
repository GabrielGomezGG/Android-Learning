package com.example.graphqlreview

import com.apollographql.apollo3.ApolloClient

object ApolloObject {

    val apolloClient = ApolloClient.Builder()
        .serverUrl("https://countries.trevorblades.com/graphql")
        .build()
}