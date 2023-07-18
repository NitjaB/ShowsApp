package infinuma.android.shows.main

import android.app.Activity
import android.os.Bundle
import infinuma.android.shows.databinding.MainActivityLayoutBinding
import infinuma.android.shows.utils.makeStatusBarTransparent

class MainActivity : Activity() {

    private lateinit var binding: MainActivityLayoutBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        makeStatusBarTransparent()
        binding = MainActivityLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}