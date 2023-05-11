package org.android.go.sopt

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.android.go.sopt.databinding.ItemHomeTitleBinding

class TitleAdapter(context: Context) : RecyclerView.Adapter<TitleViewHolder>() {
    val title = Text("Reqres Follwer List")
    private  val inflater by lazy {LayoutInflater.from(context)} // context를 활용하여 인프레이터 제작
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TitleViewHolder { //뷰 홀더 제작
        val binding: ItemHomeTitleBinding = ItemHomeTitleBinding.inflate ( // 아이템 매칭 필요
            inflater, // 레이아웃 인플레이터
            parent,false
        )
        return TitleViewHolder(binding)
    }
    override fun getItemCount(): Int { return 1 }

    override fun onBindViewHolder(holder: TitleViewHolder, position: Int) { // 홀더 제작 후 반환
        holder.onBind(title)  //배열에서 받아옴
    }

}
data class Text(
    val text: String
)
class  TitleViewHolder(private val binding: ItemHomeTitleBinding):
    RecyclerView.ViewHolder(binding.root) {
    fun onBind(item :Text){
        binding.tvHomeTitle.text = item.text
    }
}