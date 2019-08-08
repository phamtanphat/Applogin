package com.example.applogin.api

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

import java.util.concurrent.TimeUnit

class ApiConnection {
    companion object{
        var token : String = "AQVIddwrBxGTGJMtdz010gxjq19E6MuTGip_DXn5FJSWo9f7xllwpXCgA4wwwhWhE2aX4wnVFm7uLWS0jJHoMqqmygbP7RfDkGm98tA4OmtIv6ZnnLSdGsgpdyA1ioLE3SeGBYrQNYoGCT_VHEUzH3gkv7OmmUxbS6G_8fXsn1rIb4WwFWrnGbIG75YT9A0k_0Szy0TUX19k_grfI18VAVAdzKium8LBnBT5OYYWXqWVCFuNPL1a4KB0WSSTZSyGLisKtkpg03PFYYts6Z012ublPAZozV5E9ZQ9slqhBUooZY5e47r9gbuNwQcNkIgcDlhy1wgsyWzHhlavzvpmPXZ_0gg8-Q"

        private var  retrofit: Retrofit? = null
        private var api: ApiRepository? = null

        fun getAPi() : ApiRepository?{
            if (api == null){
                api = getInstance("https://api.linkedin.com/v2/").create(ApiRepository::class.java)
                return api
            }
            return api
        }
        private fun getInstance(url : String) : Retrofit{
            if (retrofit == null){
                val gson : Gson  = GsonBuilder().disableHtmlEscaping().create()
                retrofit = Retrofit.Builder()
                    .baseUrl(url)
                    .client(getOkhttpClient())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build()
                return retrofit as Retrofit
            }
            return retrofit as Retrofit
        }

        private fun getOkhttpClient() : OkHttpClient{
            var okHttpClient : OkHttpClient.Builder = OkHttpClient.Builder()
                .connectTimeout(30 , TimeUnit.SECONDS)
                .writeTimeout(30 , TimeUnit.SECONDS)
                .readTimeout(30 , TimeUnit.SECONDS)
                .addInterceptor( Interceptor { chain ->
                    var request  = chain.request().newBuilder()
                        .addHeader("Authorization","Bearer " +  token)
                        .addHeader("cache-control","no-cache")
                        .addHeader("X-Restli-Protocol-Version","2.0.0")
                        .build()
                    chain.proceed(request)
                } )
            return okHttpClient.build()
        }
    }
}