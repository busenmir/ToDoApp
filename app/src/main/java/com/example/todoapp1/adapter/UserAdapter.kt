package com.example.todoapp1.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp1.data.User
import com.example.todoapp1.databinding.UserItemBinding
import com.example.todoapp1.fragment.ListFragmentDirections

class UserAdapter: RecyclerView.Adapter<UserAdapter.ViewHolder>() {
    private var userList = emptyList<User>()
    class ViewHolder(var binding: UserItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(user: User){
            binding.textName.text = user.not
            binding.textNumber.text = user.aciklama
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = UserItemBinding.inflate(layoutInflater,parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = userList[position]
        holder.bind(item)
        holder.itemView.setOnClickListener {
            val action = ListFragmentDirections.actionListFragmentToUpdateFragment(item)
            holder.itemView.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(userList: List<User>){
        this.userList = userList
        notifyDataSetChanged()
    }
}