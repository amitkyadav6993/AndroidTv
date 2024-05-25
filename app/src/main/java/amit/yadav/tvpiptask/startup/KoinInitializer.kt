package amit.yadav.tvpiptask.startup

import android.content.Context
import androidx.startup.Initializer
import amit.yadav.tvpiptask.di.appModules
import amit.yadav.usecase.di.useCaseModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class KoinInitializer : Initializer<Unit> {

    override fun create(context: Context) {
        startKoin {
            androidContext(context)
            modules(useCaseModules + appModules)
        }
    }

    override fun dependencies() = emptyList<Class<out Initializer<*>>>()
}
