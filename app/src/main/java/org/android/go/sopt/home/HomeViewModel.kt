package org.android.go.sopt.home

import androidx.lifecycle.ViewModel
import org.android.go.sopt.sampledata.Repo

class HomeViewModel : ViewModel() {
    val itemList: List<Repo> = listOf(
        Repo("repo1", "author1"),
        Repo("repo2", "author2"),
        Repo("repo3", "author3"),
        Repo("repo4", "author4"),
        Repo("repo5", "author5"),
        Repo("repo6", "author6"),
        Repo("repo7", "author7"),
        Repo("repo8", "author8")
    )

}