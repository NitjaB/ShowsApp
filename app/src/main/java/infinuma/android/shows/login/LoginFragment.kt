package infinuma.android.shows.login

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.BounceInterpolator
import android.view.animation.ScaleAnimation
import androidx.core.animation.addListener
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import infinuma.android.shows.R
import infinuma.android.shows.databinding.LoginActivityLayoutBinding
import infinuma.android.shows.login.domain.LoginInputValidator
import infinuma.android.shows.login.domain.UserRepository
import infinuma.android.shows.login.domain.mappers.UserMapper
import infinuma.android.shows.login.viewmodel.LoginViewModel
import infinuma.android.shows.network.RemoteApiSingleton
import infinuma.android.shows.register.placeCursorToEnd
import infinuma.android.shows.register.viewModel.LoginRegisterSharedViewModel
import infinuma.android.shows.utils.SharedPrefsSource
import infinuma.android.shows.utils.TokenRepositoryInstance

class LoginFragment : Fragment() {

    private val viewModel by lazy { ViewModelProvider(this)[LoginViewModel::class.java] }

    private val sharedViewModel: LoginRegisterSharedViewModel by activityViewModels()

    private lateinit var binding: LoginActivityLayoutBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = LoginActivityLayoutBinding.inflate(layoutInflater)
        viewModel.init(
            loginInputValidator = LoginInputValidator(),
            userRepository = UserRepository(
                sharedPreferences = SharedPrefsSource.getSharedPrefs(),
                context = requireContext(),
                showRemoteApi = RemoteApiSingleton.getRemoteApi(),
                userMapper = UserMapper()
            ),
            tokenRepository = TokenRepositoryInstance.get()
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
            binding.progressBarLayout.setOnClickListener {}
            binding.progressBarLayout.visibility = if (it.isLoading) View.VISIBLE else View.GONE
            if (it.didUserRegister) {
                binding.registerButton.visibility = View.GONE
                binding.loginTextView.text = resources.getString(R.string.login_screen_login_title_user_registered)

            } else {
                binding.registerButton.visibility = View.VISIBLE
                binding.loginTextView.text = resources.getString(R.string.login_screen_title)
            }
        }
        sharedViewModel.didUserRegister.observe(viewLifecycleOwner) {
            viewModel.userRegistered(it)
        }
        viewModel.navigateToShowScreen.observe(viewLifecycleOwner) {
            findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToShowsFragment())
        }
        viewModel.showErrorWhileLoginDialog.observe(viewLifecycleOwner) {
            showLoginErrorDialog()
        }
        with(binding) {
            registerButton.setOnClickListener {
                findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToRegisterFragment())
            }
            loginButton.setOnClickListener {
                viewModel.onLoginButtonClick(binding.saveLoginCheckBox.isChecked)
            }
            usernameInputEditText.addTextChangedListener { text ->
                viewModel.onEmailInputChanged(text.toString())
            }
            passwordInputEditText.addTextChangedListener { text ->
                viewModel.onPasswordInputChanged(text.toString())
            }
        }
        startAnimation()
    }

    private fun showLoginErrorDialog() = MaterialAlertDialogBuilder(requireContext(), R.style.AlertDialogTheme)
        .setTitle(R.string.login_screen_login_error_dialog_title)
        .setPositiveButton(R.string.OK) { _, _ -> }
        .setCancelable(false)
        .create()
        .show()

    private fun startAnimation() {
        val imageAnimation = ObjectAnimator.ofFloat(binding.playImageView, "translationY", -200f, 0f).apply {
            duration = 1000
            interpolator = BounceInterpolator()
            start()
        }
        imageAnimation.addListener(
            onEnd = {
                binding.showsTextView.visibility = View.VISIBLE
                val i = ScaleAnimation(0.5f, 1f, 0.5f, 1f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f).apply {
                    duration = 1000
                    interpolator = BounceInterpolator()
                }
                binding.showsTextView.startAnimation(i)
            }
        )
    }
}

