package amit.yadav.network.di

import amit.yadav.network.api.MatchApiServices
import com.squareup.moshi.KotlinJsonAdapterFactory
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {

    single {
        OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .followSslRedirects(true)
            .build()
    }

    single<Moshi> {
        Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    single<Retrofit.Builder> {
        Retrofit.Builder()
            .client(get())
            .addConverterFactory(MoshiConverterFactory.create(get()))
    }

    single<MatchApiServices> {
        get<Retrofit.Builder>()
            .baseUrl(get<String>(named("base_url")))
            .build()
            .create(MatchApiServices::class.java)
    }
}