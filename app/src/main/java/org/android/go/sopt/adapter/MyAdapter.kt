package org.android.go.sopt

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.android.go.sopt.databinding.ItemGithubRepoBinding
import org.android.go.sopt.sampledata.Repo

class MyAdapter(context: Context) : RecyclerView.Adapter<MyViewHolder>() {
    private  val inflater by lazy {LayoutInflater.from(context)} // context를 활용하여 인플레이터 제작

    val itemList: List<Repo> = listOf(Repo("repo1","author1"),
        Repo("repo2","author2"),Repo("repo3","author3"),Repo("repo4","author4"),Repo("repo5","author5"),Repo("repo6","author6"),Repo("repo7","author7"),Repo("repo8","author8"))
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder { //뷰 홀더 제작
        val binding: ItemGithubRepoBinding = ItemGithubRepoBinding.inflate ( // 아이템 매칭 필요
            inflater, // 레이아웃 인플레이터
            parent,false
        )
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int { //배열의 크기
        return itemList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) { // 홀더 제작 후 반환
        holder.onBind(itemList[position])  //배열에서 받아옴
    }

}


class  MyViewHolder(private val binding: ItemGithubRepoBinding):
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(item :Repo){ // 받아온 값을 하나씩 매칭
            binding.tvItemName.text = item.name
            binding.tvItemUrl.text = item.author
        }
}