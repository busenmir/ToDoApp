package com.example.todoapp1.fragment

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavArgs
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.todoapp1.R
import com.example.todoapp1.data.User
import com.example.todoapp1.data.UserViewModel
import com.example.todoapp1.databinding.FragmentUpdateBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

class UpdateFragment : Fragment() {
    private lateinit var binding: FragmentUpdateBinding
    private lateinit var userViewModel : UserViewModel
    private val args by navArgs<UpdateFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentUpdateBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        binding.editName.setText(args.currentUser.not)
        binding.editAge.setText(args.currentUser.aciklama)

        binding.buttonGuncelle.setOnClickListener {
            val updateUser = User(args.currentUser.id,binding.editName.text.toString(),binding.editAge.text.toString())
            userViewModel.updateUser(updateUser)
            Toast.makeText(requireContext(), "Notunuz Güncellendi", Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment2)
        }

        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_item,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_delete) {
            val builder = AlertDialog.Builder(requireContext())
            builder.setTitle("${args.currentUser.not} mi Silinecek ?")
            builder.setMessage("${args.currentUser.id} notu silmek istediğinize emin misiniz ?")
            builder.setPositiveButton("Evet") { _, _ ->
                userViewModel.deleteUser(args.currentUser)
                Toast.makeText(
                    requireContext(),
                    "Seçtiğiniz Not Silindi!",
                    Toast.LENGTH_SHORT
                ).show()
                findNavController().navigate(R.id.action_updateFragment_to_listFragment2)
            }
            builder.setNegativeButton("Hayır") { _, _ -> }
            builder.create().show()
        }
        return super.onOptionsItemSelected(item)
    }
}