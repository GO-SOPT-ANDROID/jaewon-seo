package org.android.go.sopt.presentation.viewmodel

import androidx.lifecycle.ViewModel
import org.android.go.sopt.data.remote.ServicePool

class HomeViewModel :ViewModel() {
    private val follwerService = ServicePool.followerService

}

