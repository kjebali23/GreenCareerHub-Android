package tn.esprit.gestionuser.viewmodel

import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import tn.esprit.gestionuser.R
import tn.esprit.gestionuser.databinding.PopupLoginBinding

class LoginFragment: Fragment() {
    private lateinit var loginViewModel: LoginViewModel
    private lateinit var binding: PopupLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = PopupLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tiEmail = view.findViewById<EditText>(R.id.tiEmail)
        val tiPassword = view.findViewById<EditText>(R.id.tiPassword)
        val btnLogin = view.findViewById<Button>(R.id.btnLogin)

        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        loginViewModel.loginResult.observe(viewLifecycleOwner, { loginResult ->
            if (loginResult.status == "success") {
                // Handle successful authentication
                Toast.makeText(requireContext(), "Login Successful", Toast.LENGTH_SHORT).show()
            } else {
                // Handle authentication error
                val errorMessage = loginResult.error?.message ?: "Unknown error"
                Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_SHORT).show()
            }
        })

        btnLogin.setOnClickListener {
            val email = tiEmail.text.toString()
            val password = tiPassword.text.toString()

            if (isValidEmail(email) && isValidPassword(password)) {
                loginViewModel.login(email, password)
            } else {
                if (!isValidEmail(email)) {
                    tiEmail.error = "Invalid email"
                }
                if (!isValidPassword(password)) {
                    tiPassword.error = "Invalid password"
                }
            }
        }
    }

    private fun isValidEmail(email: String): Boolean {
        val trimmedEmail = email.trim()
        return trimmedEmail.isNotEmpty() && android.util.Patterns.EMAIL_ADDRESS.matcher(
            trimmedEmail
        ).matches()
    }

    private fun isValidPassword(password: String): Boolean {
        return password.isNotEmpty() && password.length >= 6
    }

}

