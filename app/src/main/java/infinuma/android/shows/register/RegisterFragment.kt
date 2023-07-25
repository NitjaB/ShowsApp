package infinuma.android.shows.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import infinuma.android.shows.databinding.RegisterFragmentLayoutBinding
import infinuma.android.shows.login.LoginFragmentDirections
import infinuma.android.shows.register.viewModel.RegisterViewModel

class RegisterFragment : Fragment() {

    private val viewModel by lazy { ViewModelProvider(this)[RegisterViewModel::class.java] }

    private lateinit var binding: RegisterFragmentLayoutBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = RegisterFragmentLayoutBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.state.observe(viewLifecycleOwner) {
            binding.usernameInputEditText.setText(it.email)
            binding.usernameInputEditText.placeCursorToEnd()
            binding.passwordInputEditText.setText(it.password)
            binding.passwordInputEditText.placeCursorToEnd()
            binding.repeatPasswordInputEditText.setText(it.repeatPassword)
            binding.repeatPasswordInputEditText.placeCursorToEnd()
            binding.registerButton.isEnabled = it.registerButtonEnabled
        }
        binding.usernameInputEditText.doOnTextChanged { text, _, _, _ ->
            viewModel.onEmailInputChanged(text.toString())
        }
        binding.passwordInputEditText.doOnTextChanged { text, _, _, _ ->
            viewModel.onPasswordInputChanged(text.toString())
        }
        binding.repeatPasswordInputEditText.doOnTextChanged { text, _, _, _ ->
            viewModel.onRepeatPasswordInputChanged(text.toString())
        }
        binding.registerButton.setOnClickListener {
            findNavController().navigate(LoginFragmentDirections.actionGlobalLoginFragment())

        }
    }
}

fun EditText.placeCursorToEnd() {
    this.setSelection(this.text.length)
}
