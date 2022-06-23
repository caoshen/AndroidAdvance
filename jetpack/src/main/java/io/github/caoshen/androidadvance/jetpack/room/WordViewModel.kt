package io.github.caoshen.androidadvance.jetpack.room

import androidx.lifecycle.*
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

/**
 * @author caoshen
 * @date 2020/12/9
 */
class WordViewModel(private val repository: WordRepository) : ViewModel() {

    val allWords: LiveData<List<Word>> = repository.allWords.asLiveData()

    fun insert(word: Word) = viewModelScope.launch {
        repository.insert(word)
    }

    class WordViewModelFactory(private val repository: WordRepository) : ViewModelProvider.Factory {


    }

}