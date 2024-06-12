package com.gg.openrouteservicewithgooglemapexample

import com.gg.openrouteservicewithgooglemapexample.data.RouteService
import com.gg.openrouteservicewithgooglemapexample.data.models.BodyApi
import com.gg.openrouteservicewithgooglemapexample.utils.Helper
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    private lateinit var mockWebServer: MockWebServer
    private lateinit var routeService: RouteService

    @Before
    fun setUp() {
        //instancia de mockwebserver
        mockWebServer = MockWebServer()

        //instancia "falsa" de retrofit
        routeService = Retrofit.Builder()
            .baseUrl(mockWebServer.url(""))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RouteService::class.java)
    }

    @After
    fun tearDown() {
        // "apaga" el mock server
        mockWebServer.shutdown()
    }
    @Test
    fun `probando la api` () = runTest{
        //objeto de mockResponse
        val mockResponse = MockResponse()

        //leer el json
        val content = Helper.readFileResources("/FakeJson.json")
        mockResponse.setResponseCode(200)
        mockResponse.setBody(content)
        mockWebServer.enqueue(mockResponse)

        val response = routeService.getDistance(BodyApi(
            listOf(
                listOf(-58.615703,-34.794611),
                listOf(-58.615709,-34.759035)
            )
        ))
        mockWebServer.takeRequest()

        assertEquals(200, response.code())
        assertEquals("Magan", response.body()?.destinations?.first()?.name)
        assertEquals(2, response.body()?.destinations?.get(0)?.location?.size)
        assertEquals(2, response.body()?.distances?.first()?.first())
    }
}