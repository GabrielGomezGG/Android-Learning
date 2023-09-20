package com.example.graphqlreview

fun CountriesQuery.Country.toCountry() : Country{
    return Country(
        name = this.name,
        code = this.code,
        emoji = this.emoji,
        emojiU = this.emojiU
    )
}