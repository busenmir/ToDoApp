package com.example.todoapp1.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todoapp1.R
import com.example.todoapp1.adapter.UserAdapter
import com.example.todoapp1.data.UserViewModel
import com.example.todoapp1.databinding.FragmentListBinding

class ListFragment : Fragment() {
    private lateinit var recyclerViewAdapter : UserAdapter
    private lateinit var userViewModel : UserViewModel
    private lateinit var binding: FragmentListBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        binding.floatingActionButton.setOnClickListener{
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }

        recyclerViewAdapter = UserAdapter()
        with(binding){
            recyclerView.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = recyclerViewAdapter
            }
        }
        userViewModel.readAllData.observe(viewLifecycleOwner, { userList ->
            recyclerViewAdapter.setData(userList)
            if(userList.size == 0){
                Toast.makeText(context, "Liste Boş", Toast.LENGTH_LONG).show()
            }

        })
    }
}