package com.example.graphqlexample

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.network.okHttpClient
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response

//class AuthInterceptor(private val authToken: String) : Interceptor {
//    override fun intercept(chain: Interceptor.Chain): Response {
//        val requestBuilder = chain.request().newBuilder()
//            .header("Authorization", "Bearer $authToken")
//        val request = requestBuilder.build()
//        return chain.proceed(request)
//    }
//}
//
//val okHttpClient = OkHttpClient.Builder()
//    .addInterceptor(AuthInterceptor("52fbebbace5bc811ca33b2a73065f861"))
//    .build()
//
//val apolloClient = ApolloClient.builder()
//    .serverUrl("YOUR_GRAPHQL_API_URL_HERE")
//    .okHttpClient(okHttpClient)
//    .build()

val apolloClient = ApolloClient.Builder()
    .serverUrl("https://apollo-fullstack-tutorial.herokuapp.com/graphql")
    .build()