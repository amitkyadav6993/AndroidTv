package amit.yadav.data.di

import amit.yadav.data.repositoriesImpl.MatchRepositoryImpl
import amit.yadav.domain.repositories.MatchesRepository
import amit.yadav.network.di.networkModule
import org.koin.core.qualifier.named
import org.koin.dsl.module

private val repositoriesModule = module {

    factory<MatchesRepository> {
        MatchRepositoryImpl(
            matchApiServices = get(),
            matchApiKey = get(named("match_api_key"))
        )
    }
}

val dataModules = repositoriesModule + networkModule