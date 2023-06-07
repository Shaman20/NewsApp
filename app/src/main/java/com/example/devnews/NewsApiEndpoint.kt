package com.example.devnews

import retrofit2.Call
import retrofit2.http.GET

interface NewsApiEndpoint {

    @GET("v2/everything?q=technology&apiKey=6f9acdb50d3a47a5a64a2055550039e6")
    fun getEverything(): Call<News>

    @GET("v2/everything?q=technology&apiKey=6f9acdb50d3a47a5a64a2055550039e6")
    fun getArticles(): Call<Article>
}