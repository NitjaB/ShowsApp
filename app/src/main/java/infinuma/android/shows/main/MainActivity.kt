package infinuma.android.shows.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import infinuma.android.shows.databinding.MainActivityLayoutBinding
import infinuma.android.shows.network.RemoteApiSingleton
import infinuma.android.shows.register.viewModel.LoginRegisterSharedViewModel
import infinuma.android.shows.utils.SharedPrefsSource
import infinuma.android.shows.utils.TokenRepositoryInstance
import infinuma.android.shows.utils.makeStatusBarTransparent

class MainActivity : AppCompatActivity() {

    private lateinit var binding: MainActivityLayoutBinding

    private val viewModel: LoginRegisterSharedViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        RemoteApiSingleton.bind(applicationContext)
        SharedPrefsSource.init(applicationContext)
        TokenRepositoryInstance.init(SharedPrefsSource.getSharedPrefs())
        makeStatusBarTransparent()
        binding = MainActivityLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}