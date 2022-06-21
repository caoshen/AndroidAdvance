package io.github.caoshen.androidadvance.jetpack.room

import io.github.caoshen.androidadvance.jetpack.compose.util.AppHolder
import io.github.caoshen.baselib.base.BaseApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

/**
 * @author caoshen
 * @date 2020/12/9
 */
class WordsApplication : BaseApp() {

    val applicationScope = CoroutineScope(SupervisorJob())

    val database by lazy {
        WordRoomDatabase.getDatabase(this, applicationScope)
    }

    val repository by lazy {
        WordRepository(database.wordDao())
    }

    override fun onCreate() {
        super.onCreate()

        AppHolder.appContext = this
    }
}