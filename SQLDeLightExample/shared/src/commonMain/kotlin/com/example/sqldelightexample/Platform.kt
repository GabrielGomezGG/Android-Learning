package com.example.sqldelightexample

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform