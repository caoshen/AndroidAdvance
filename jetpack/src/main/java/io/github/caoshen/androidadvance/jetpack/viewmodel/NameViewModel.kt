package io.github.caoshen.androidadvance.jetpack.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import io.github.caoshen.androidadvance.jetpack.livedata.StockLiveData
import io.github.caoshen.baselib.utils.SingleLiveEvent

class NameViewModel : ViewModel() {
    val currentName: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    val stockLiveData = StockLiveData("1")

    val userLiveData: MutableLiveData<String> = MutableLiveData("user")

    val userNameLiveData: LiveData<String> = Transformations.map(userLiveData) { user ->
        "Hi, $user"
    }

    private val userIdLiveData: MutableLiveData<String> = MutableLiveData("1000")

    val userIdSwitch = Transformations.switchMap(userIdLiveData) { id ->
        getUser(id)
    }

    private fun getUser(id: String): LiveData<String> {
        return MutableLiveData<String>("${id}-001")
    }

    private val _navigateToDetails = SingleLiveEvent<Boolean>()

    val navigateToDetails: SingleLiveEvent<Boolean>
        get() = _navigateToDetails

    fun userClickOnButton() {
        _navigateToDetails.value = true
    }

    fun onUserClick() {
//        val cur: String? = currentName.value
//        val next: Int = if (cur != null) {
//            cur.toInt() + 1
//        } else {
//            1
//        }
//        currentName.value = next.toString()
        userIdLiveData.value = "101010"
    }
}