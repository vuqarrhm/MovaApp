package com.example.film.screens

import android.os.Bundle
import android.text.InputType
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.film.R
import com.example.film.databinding.FragmentRegisterBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : Fragment() {
    lateinit var binding:FragmentRegisterBinding
    private val viewModel by viewModels<RegisterViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentRegisterBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeData()
        binding.textViewSignin.setOnClickListener {
            findNavController().navigate(RegisterFragmentDirections.actionRegisterFragmentToLoginFragment())
        }

        binding.imageViewPassword.setOnClickListener {
            if (binding.editTextTextPassword.inputType== InputType.TYPE_CLASS_TEXT){
                binding.editTextTextPassword.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD

            }else{
                binding.editTextTextPassword.inputType = InputType.TYPE_CLASS_TEXT
            }

        }


        binding.button.setOnClickListener {

            val email=binding.editTextTextEmail.text.toString().trim()
            val password=binding.editTextTextPassword.text.toString().trim()

            if (email.isNotEmpty()&&password.isNotEmpty()){
                if (password.length>=6){
                    viewModel.register(email, password)
                }else{
                    Toast.makeText(context,"Sifre min 6 olmalidir", Toast.LENGTH_LONG).show()
                }
            }else{
                Toast.makeText(context,"Bos ola bilmez", Toast.LENGTH_LONG).show()

            }
        }
    }


    fun observeData(){
        viewModel.userResgister.observe(viewLifecycleOwner){
            if (it==true){
                Toast.makeText(context,"Ugurlu qeydiyyat", Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(context,"Ugursuz qeydiyyat", Toast.LENGTH_LONG).show()
            }
        }

        viewModel.errorMessage.observe(viewLifecycleOwner){error->
            binding.editTextTextEmail.error=error
            binding.editTextTextPassword.error=error
            binding.editTextTextPassword.setBackgroundResource(R.drawable.edittext_xeta)
            binding.editTextTextEmail.setBackgroundResource(R.drawable.edittext_xeta)

        }
    }
}
