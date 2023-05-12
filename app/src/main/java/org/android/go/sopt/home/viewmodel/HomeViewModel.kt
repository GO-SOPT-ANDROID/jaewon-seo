package org.android.go.sopt.home.viewmodel

import androidx.lifecycle.ViewModel
import org.android.go.sopt.data.remote.ServicePool

class HomeViewModel :ViewModel() {
    private val follwerService = ServicePool.followerService

}

