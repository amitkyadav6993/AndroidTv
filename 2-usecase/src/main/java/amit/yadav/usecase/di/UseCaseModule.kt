package amit.yadav.usecase.di

import amit.yadav.data.di.dataModules
import amit.yadav.usecase.match.MatchRemoteUseCase
import amit.yadav.usecase.match.MatchRemoteUseCaseImpl
import org.koin.dsl.module

private val useCaseModule = module {

    factory<MatchRemoteUseCase> {
        MatchRemoteUseCaseImpl(matchRepository = get())
    }
}

val useCaseModules = useCaseModule + dataModules