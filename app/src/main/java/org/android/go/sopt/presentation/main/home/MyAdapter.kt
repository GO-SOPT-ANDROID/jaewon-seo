package org.android.go.sopt.presentation.main.home

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import org.android.go.sopt.data.remote.dto.ResponseFollowerDto
import org.android.go.sopt.databinding.ItemPeopleBinding
import org.android.go.sopt.presentation.data.Follower

class MyAdapter(
    private val context: Context
) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {
    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var followerList: List<Follower>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemPeopleBinding.inflate(inflater, parent, false)
        return MyViewHolder(binding)
    }

    interface AdapterCallback {
        fun onFailure()
    }

    override fun getItemCount(): Int {
        return followerList?.size ?: 0
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        followerList?.get(position)?.let { holder.bind(it) }
    }

    fun setFollowerList(list: List<Follower>) {
        this.followerList = list
        notifyDataSetChanged()
    }

    inner class MyViewHolder(private val binding: ItemPeopleBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(follower: Follower) {
            with(binding) {
                tvItemName.text = "${follower.first_name} ${follower.last_name}"
                tvItemEmail.text = follower.email
                Log.d("G", follower.first_name)
                ivItemImage.load(follower.avatar)
            }
        }
    }
}
