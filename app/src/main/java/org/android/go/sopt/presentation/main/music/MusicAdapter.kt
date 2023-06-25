package org.android.go.sopt.presentation.main.music

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import org.android.go.sopt.data.remote.dto.ResponseGetMusicDto
import org.android.go.sopt.databinding.ItemMusicBinding

class MusicAdapter(
    private val context: Context
) : RecyclerView.Adapter<MusicAdapter.MyViewHolder>() {
    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var musicList: List<ResponseGetMusicDto.Data.Music>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemMusicBinding.inflate(inflater, parent, false)
        return MyViewHolder(binding)
    }

    interface AdapterCallback {
        fun onFailure()
    }

    override fun getItemCount(): Int {
        return musicList?.size ?: 0
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        musicList?.get(position)?.let { holder.bind(it) }
    }

    fun setMusicList(list: List<ResponseGetMusicDto.Data.Music>) {
        this.musicList = list
        notifyDataSetChanged()
    }

    inner class MyViewHolder(private val binding: ItemMusicBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(music: ResponseGetMusicDto.Data.Music) {
            with(binding) {
                tvItemMusicTitle.text = music.title
                tvItemMusicSinger.text = music.singer
                ivItemMusicImage.load(music.url)
            }
        }
    }
}