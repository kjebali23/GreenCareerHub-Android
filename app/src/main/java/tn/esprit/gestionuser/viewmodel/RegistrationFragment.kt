package tn.esprit.gestionuser.viewmodel

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import tn.esprit.gestionuser.R

class RegistrationFragment : Fragment() {
    private lateinit var registrationViewModel: RegistrationViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.signup, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val emailEditText = view.findViewById<EditText>(R.id.tiEmail)
        val passwordEditText = view.findViewById<EditText>(R.id.tiPassword)
        val registerButton = view.findViewById<Button>(R.id.btnSignUp)

        registrationViewModel = ViewModelProvider(this).get(RegistrationViewModel::class.java)

        registrationViewModel.registrationResult.observe(viewLifecycleOwner) { registrationResult ->
            if (registrationResult.status == "success") {

            } else {
                val errorMessage = registrationResult.error?.message ?: "Unknown error"
                Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_SHORT).show()
            }
        }

        registerButton.setOnClickListener {
            // Get user input
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()

            // Call the register function in the ViewModel
            registrationViewModel.register(email, password)
        }
    }
    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }
}
