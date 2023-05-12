package org.android.go.sopt.presentation.main.home

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import org.android.go.sopt.data.remote.ServicePool
import org.android.go.sopt.data.remote.dto.ResponseFollowerDto
import org.android.go.sopt.databinding.ItemPeopleBinding

class MyAdapter(
    private val context: Context,
    private val callback: AdapterCallback
    ) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {
    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var followerList: List<ResponseFollowerDto.Data>? = null

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

    fun fetchData() {
        val followerService = ServicePool.followerService
        followerService.getFollowerList().enqueue(object : retrofit2.Callback<ResponseFollowerDto> {
            override fun onResponse(
                call: retrofit2.Call<ResponseFollowerDto>,
                response: retrofit2.Response<ResponseFollowerDto>
            ) {
                if (response.isSuccessful) {
                    val followerDto = response.body()
                    followerList = followerDto?.datas
                    notifyDataSetChanged()
                } else{
                    callback.onFailure()
                }
            }
            override fun onFailure(call: retrofit2.Call<ResponseFollowerDto>, t: Throwable) {
                callback.onFailure()
            }
        })
    }

    inner class MyViewHolder(private val binding: ItemPeopleBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(follower: ResponseFollowerDto.Data) {
            with(binding) {
                tvItemName.text = "${follower.first_name} ${follower.last_name}"
                tvItemEmail.text = follower.email
                ivItemImage.load(follower.avatar)
            }
        }
    }
}
