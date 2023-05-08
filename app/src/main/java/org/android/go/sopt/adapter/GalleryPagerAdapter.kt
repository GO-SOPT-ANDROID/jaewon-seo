package org.android.go.sopt.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.android.go.sopt.databinding.ItemPagerGalleryBinding

class GalleryPagerAdapter(
    _itemList: List<Int> = listOf(),
) : RecyclerView.Adapter<GalleryPagerAdapter.GalleryPagerViewHolder>() {
    private var itemList: List<Int> = _itemList

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryPagerViewHolder {
        //parent.context를 사용하여 context를 받아올 수 있다.
        val binding = ItemPagerGalleryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GalleryPagerViewHolder(binding)
    }

    class GalleryPagerViewHolder(
        private val binding: ItemPagerGalleryBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(src : Int) {
            binding.imgGallery.setImageResource(src)
        }
    }

    override fun onBindViewHolder(holder: GalleryPagerViewHolder, position: Int) {
        holder.onBind(itemList[position])
    }

    override fun getItemCount() = itemList.size

    fun setItemList(itemList: List<Int>) { //외부에서 아이템 지정을 위해서  private 지정을 하지 않음.
        this.itemList = itemList
        notifyDataSetChanged()
    }
}