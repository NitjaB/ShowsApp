package infinuma.android.shows.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import infinuma.android.shows.databinding.MainActivityLayoutBinding
import infinuma.android.shows.utils.SharedPrefsSource
import infinuma.android.shows.utils.makeStatusBarTransparent

class MainActivity : AppCompatActivity() {

    private lateinit var binding: MainActivityLayoutBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        SharedPrefsSource.init(applicationContext)
        makeStatusBarTransparent()
        binding = MainActivityLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}