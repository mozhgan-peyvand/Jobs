package com.example.data.jobs.di

import android.content.Context
import androidx.room.Room
import com.example.data.jobs.BuildConfig
import com.example.data.jobs.db.AppDataBase
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.ConnectionPool
import okhttp3.Dispatcher
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Singleton
    @Provides
    fun provideDb(@ApplicationContext context: Context): AppDataBase{
        return Room
            .databaseBuilder(context, AppDataBase::class.java, "com.mozhgan.peivandian.db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideMoshi(): Moshi = Moshi.Builder()
        .add(ApplicationJsonAdapterFactory)
        .build()

    @Singleton
    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        }
    }

    @Singleton
    @Provides
    fun provideRetrofit(
        httpClient: OkHttpClient,
        moshi: Moshi
    ): Retrofit = Retrofit.Builder()
        .client(httpClient)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .baseUrl(ApiUrlHelper.API_URL)
        .build()


    @Provides
    fun provideOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        customInterceptor: CustomInterceptor
    ): OkHttpClient {


        val timeOut = 30L

        val dispatcher = Dispatcher(Executors.newFixedThreadPool(20))
        dispatcher.maxRequests = 20
        dispatcher.maxRequestsPerHost = 20

        val okHttpClientBuilder = OkHttpClient.Builder()
        okHttpClientBuilder.addNetworkInterceptor(httpLoggingInterceptor)
            .dispatcher(dispatcher)
            .connectionPool(ConnectionPool(100, timeOut, TimeUnit.SECONDS))
            .addInterceptor(customInterceptor)
            .readTimeout(timeOut, TimeUnit.SECONDS)
            .writeTimeout(timeOut, TimeUnit.SECONDS)
            .connectTimeout(timeOut, TimeUnit.SECONDS)
            .sslSocketFactory(
                TLSSocketFactory(),
                TLSSocketFactory().trustManager
            )

        return okHttpClientBuilder.build()
    }
}