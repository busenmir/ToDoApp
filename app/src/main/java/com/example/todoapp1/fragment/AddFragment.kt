package com.example.todoapp1.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.todoapp1.R
import com.example.todoapp1.data.User
import com.example.todoapp1.data.UserViewModel
import com.example.todoapp1.databinding.FragmentAddBinding

class AddFragment : Fragment() {
    private lateinit var userViewModel: UserViewModel
    private lateinit var binding: FragmentAddBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentAddBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        binding.buttonEkle.setOnClickListener{
            val user = User(0,binding.editName.text.toString(),binding.editAge.text.toString())
            userViewModel.addUser(user)
            Toast.makeText(requireContext(), "Notunuz eklendi", Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
            //findNavController().popBackStack()
            //Bir öncekine geri dön.
        }
    }
}