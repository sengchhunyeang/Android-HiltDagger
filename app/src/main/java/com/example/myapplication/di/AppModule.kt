package com.example.myapplication.di

import com.example.myapplication.Const.Const
import com.example.myapplication.datasource.DatasourceImp
import com.example.myapplication.datasource.IDatasource
import com.example.myapplication.repository.IRepository
import com.example.myapplication.repository.RepositoryImp
import com.example.myapplication.service.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .retryOnConnectionFailure(true)
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Const.BASE_URL)
            .client(client)
            .build()
    }

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideDataSource(apiService: ApiService): IDatasource {
        return DatasourceImp(apiService)
    }

    @Singleton
    @Provides
    fun provideRepository(dataSource: IDatasource): IRepository {
        return RepositoryImp(dataSource)
    }
}
