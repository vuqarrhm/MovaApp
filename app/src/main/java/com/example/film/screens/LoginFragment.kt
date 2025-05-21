package com.example.film.screens

import android.content.SharedPreferences
import android.os.Bundle
import android.text.InputType
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.film.R
import com.example.film.databinding.FragmentLoginBinding
import com.example.film.util.goneItem
import com.example.film.util.visibleItem
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LoginFragment: Fragment() {
    lateinit var binding:FragmentLoginBinding
    val viewModel by viewModels<LoginViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentLoginBinding.inflate(inflater)
        return binding.root
    }

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeData()
        binding.textViewRegister.setOnClickListener {
            findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToRegisterFragment())
        }


        val isChecked = sharedPreferences.getBoolean("cheklendimi", false)
        if (isChecked && FirebaseAuth.getInstance().currentUser != null) {
            if (findNavController().currentDestination?.id == R.id.loginFragment) {
                findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToHomeFragment())
            }
        }

        binding.button.setOnClickListener {
            val email = binding.editTextTextEmail.text.toString().trim()
            val password = binding.editTextTextPassword.text.toString().trim()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                viewModel.login(email, password)

                viewModel.userLogin.observe(viewLifecycleOwner) { isSuccessful ->
                    if (isSuccessful) {
                        val isChecked = binding.checkBox.isChecked
                        sharedPreferences.edit().putBoolean("cheklendimi", isChecked).apply()

                        findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToHomeFragment())
                    } else {
                        Toast.makeText(context, "Giriş uğursuz oldu!", Toast.LENGTH_SHORT).show()
                    }
                }
            } else {
                Toast.makeText(context, "Boş qala bilməz!", Toast.LENGTH_SHORT).show()
            }
        }



        binding.imageViewPassword.setOnClickListener {
            if (binding.editTextTextPassword.inputType==InputType.TYPE_CLASS_TEXT){
                binding.editTextTextPassword.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD

            }else{
                binding.editTextTextPassword.inputType = InputType.TYPE_CLASS_TEXT
            }
        }

    }

    fun observeData(){
        viewModel.error.observe(viewLifecycleOwner){error->
            binding.editTextTextEmail.error = error
            binding.editTextTextPassword.error = error
            binding.editTextTextPassword.setBackgroundResource(R.drawable.edittext_xeta)
            binding.editTextTextEmail.setBackgroundResource(R.drawable.edittext_xeta)
        }

        viewModel.progresbar.observe(viewLifecycleOwner) { isLoading ->
            if (isLoading==true) {
                binding.progressBar.visibleItem()
            } else {
                binding.progressBar.goneItem()
            }
        }

        viewModel.isAuther.observe(viewLifecycleOwner){ isAuther->
            if (isAuther){

            }

        }

    }
}
