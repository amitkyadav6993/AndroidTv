package amit.yadav.tvpiptask.di

import amit.yadav.tvpiptask.BuildConfig
import amit.yadav.tvpiptask.view.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val appModules = module {

    single(named("base_url")) { BuildConfig.BASE_URL }
    single(named("match_api_key")) { BuildConfig.MATCH_API_KEY }

    viewModel {
        MainViewModel(
            matchRemoteUseCase = get()
        )
    }
}