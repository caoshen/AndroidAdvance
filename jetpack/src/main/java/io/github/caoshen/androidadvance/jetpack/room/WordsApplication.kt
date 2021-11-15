package io.github.caoshen.androidadvance.jetpack.room

import android.app.Application
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
}