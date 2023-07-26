package infinuma.android.shows.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import infinuma.android.shows.R
import infinuma.android.shows.databinding.LoginActivityLayoutBinding
import infinuma.android.shows.login.domain.LoginInputValidator
import infinuma.android.shows.login.domain.UserRepository
import infinuma.android.shows.login.viewmodel.LoginViewModel
import infinuma.android.shows.network.RemoteApiSingleton
import infinuma.android.shows.register.placeCursorToEnd
import infinuma.android.shows.utils.SharedPrefsSource

class LoginFragment : Fragment() {

    private val viewModel by lazy { ViewModelProvider(this)[LoginViewModel::class.java] }

    private lateinit var binding: LoginActivityLayoutBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = LoginActivityLayoutBinding.inflate(layoutInflater)
        viewModel.init(
            loginInputValidator = LoginInputValidator(),
            userRepository = UserRepository(
                sharedPreferences = SharedPrefsSource.getSharedPrefs(),
                context = requireContext(),
                showRemoteApi = RemoteApiSingleton.getRemoteApi()
            )
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.state.observe(viewLifecycleOwner) {
            binding.usernameInputEditText.setText(it.email)
            binding.usernameInputEditText.placeCursorToEnd()
            if (it.isEmailError) {
                binding.usernameInputEditText.error = resources.getString(R.string.login_screen_email_error)
            }
            binding.passwordInputEditText.setText(it.password)
            binding.passwordInputEditText.placeCursorToEnd()
            binding.loginButton.isEnabled = it.loginButtonEnabled
        }
        viewModel.navigateToShowScreen.observe(viewLifecycleOwner) {
            findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToShowsFragment())
        }
        with(binding) {
            registerButton.setOnClickListener {
                findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToRegisterFragment())
            }
            loginButton.setOnClickListener {
                viewModel.onLoginButtonClick()
            }
            usernameInputEditText.addTextChangedListener { text ->
                viewModel.onEmailInputChanged(text.toString())
            }
            passwordInputEditText.addTextChangedListener { text ->
                viewModel.onPasswordInputChanged(text.toString())
            }
        }
    }
}

