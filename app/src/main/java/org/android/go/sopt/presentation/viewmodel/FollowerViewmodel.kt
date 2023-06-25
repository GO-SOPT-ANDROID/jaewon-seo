package org.android.go.sopt.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.android.go.sopt.data.remote.ServicePool
import org.android.go.sopt.presentation.data.Follower

class FollowerViewModel : ViewModel() {

    val followerListLiveData: MutableLiveData<List<Follower>> = MutableLiveData()

    init {
        fetchFollowerData()
    }

    fun fetchFollowerData() {
        viewModelScope.launch {
            kotlin.runCatching {
                val followerService = ServicePool.followerService
                followerService.getFollowerList()
            }.onSuccess {
                followerListLiveData.value = it.toFollowerList()
            }.onFailure {

            }
        }
    }
}
