package org.android.go.sopt

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.android.go.sopt.databinding.ItemHomeTitleBinding

class TitleAdapter(context: Context) : RecyclerView.Adapter<TitleViewHolder>() {
    val itemList: List<Text> = listOf(Text("Jaewon's GithubRepo"),Text("@librarywon"))
    private  val inflater by lazy {LayoutInflater.from(context)} // context를 활용하여 인프레이터 제작
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TitleViewHolder { //뷰 홀더 제작
        val binding: ItemHomeTitleBinding = ItemHomeTitleBinding.inflate ( // 아이템 매칭 필요
            inflater, // 레이아웃 인플레이터
            parent,false
        )
        return TitleViewHolder(binding)
    }
    override fun getItemCount(): Int { // 배열아 크기
        return itemList.size
    }

    override fun onBindViewHolder(holder: TitleViewHolder, position: Int) { // 홀더 제작 후 반환
        holder.onBind(itemList[position])  //배열에서 받아옴
    }

}
data class Text(
    val text: String
)
class  TitleViewHolder(private val binding: ItemHomeTitleBinding):
    RecyclerView.ViewHolder(binding.root) {
    fun onBind(item :Text){ // 받아온 값을 하나씩 매칭
        binding.tvHomeTitle.text = item.text
    }
}