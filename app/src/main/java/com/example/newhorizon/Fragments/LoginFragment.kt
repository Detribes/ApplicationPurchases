package com.example.newhorizon.Fragments

import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.newhorizon.NavigationHost
import com.example.newhorizon.R
import com.example.newhorizon.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    private lateinit var _binding: FragmentLoginBinding

    private fun isPasswordValid(text: Editable?): Boolean {
        return text != null && text.length >= 8
    }
    private fun init() = with(_binding){
        nextButton.setOnClickListener{
            if (!isPasswordValid(passwordEditText.text)) {
                passwordTextInput.error = getString(R.string.zalupa_error_password)
            } else {
                passwordTextInput.error = null
                (activity as NavigationHost).navigateTo(ProductGridFragment(), false)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return _binding.root }

    companion object {
        @JvmStatic
        fun newInstance() = LoginFragment()
    }
}