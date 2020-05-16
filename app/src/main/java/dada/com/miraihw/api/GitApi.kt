package dada.com.miraihw.api

import androidx.lifecycle.LiveData
import com.pivincii.livedata_retrofit.network.ApiResponse
import com.pivincii.livedata_retrofit.network.LiveDataCallAdapterFactory
import dada.com.miraihw.BuildConfig
import dada.com.miraihw.const.Const.Companion.BASE_URL
import dada.com.miraihw.data.GitUser
import dada.com.miraihw.data.GitUserInfo
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import java.util.concurrent.TimeUnit

interface GitApi {

    companion object {
        fun get(): GitApi {
            val clientBuilder = OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
            if (BuildConfig.DEBUG) {
                val loggingInterceptor = HttpLoggingInterceptor()
                loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
                clientBuilder.addInterceptor(loggingInterceptor)
            }

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(clientBuilder.build())
                .addCallAdapterFactory(LiveDataCallAdapterFactory())
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
                .create(GitApi::class.java)
        }


    }

    @GET("users")
    fun getGitAllUsers():LiveData<ApiResponse<List<GitUser>>>

    @GET("users/{login}")
    fun getGitUser(@Path("login") login:String):LiveData<ApiResponse<GitUserInfo>>

}