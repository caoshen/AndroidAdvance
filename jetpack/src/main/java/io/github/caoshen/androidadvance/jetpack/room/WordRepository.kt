package io.github.caoshen.androidadvance.jetpack.room

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow

class WordRepository(private val wordDao: WordDao) {

    val allWords: Flow<List<Word>> = wordDao.getAlphabetizeWords()

    @WorkerThread
    suspend fun insert(word: Word) {
        wordDao.insert(word)
    }


}