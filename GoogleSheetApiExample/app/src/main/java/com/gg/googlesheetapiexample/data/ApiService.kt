package com.gg.googlesheetapiexample.data

import com.gg.googlesheetapiexample.data.model.Product
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("echo?user_content_key=E-cIegXsx44VZa1h6sijZ7sL8b0seer1jTjt5xyrC5mgBKsaPnqEHuIQTfXCZgkfeEvo_U24-YZyOHCBQyPZsGKhddoXINaCm5_BxDlH2jW0nuo2oDemN9CCS2h10ox_1xSncGQajx_ryfhECjZEnBYZPgbqxhXbThemwDMMBMT0vjxcxs_LWkm8haP4xnZIxz5iOX8cvBbWdqwmDM08Ll5jtUKZP_PrqO6debywDxPo1D4YVhq0Otz9Jw9Md8uu&lib=MrA12R9rYqRSW3_f6SxYtW8WqwqEpKsGi")
    suspend fun getSheetData(): Response<List<Product>>
}