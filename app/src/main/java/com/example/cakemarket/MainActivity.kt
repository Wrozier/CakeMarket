package com.example.cakemarket

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cakemarket.databinding.ActivityMainBinding
import com.example.cakemarket.ui.CakeAdapter
import com.example.cakemarket.viewmodel.CakeViewModel
import kotlin.collections.get

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: CakeViewModel
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: CakeAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // setup recyclerview
        setUpRecyclerView()
        setUpViewModel()
        observeData()
        viewModel.fetchCakes()


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun setUpViewModel() {
        viewModel = ViewModelProvider(this)[CakeViewModel::class.java]

    }


    private fun setUpRecyclerView() {
        adapter = CakeAdapter(emptyList()) // initialize with empty list
        binding.rvCakes.layoutManager = LinearLayoutManager(this)
        binding.rvCakes.adapter = adapter
    }

    private fun observeData() {
        viewModel.cakes.observe(this) { cakes ->

            Log.i("DATA_SIZE", "Size = ${cakes.size}")

            // Always set adapter (even if empty)
            binding.rvCakes.adapter = CakeAdapter(cakes)
        }

        viewModel.loading.observe(this) { isLoading ->
            // show/hide progress bar
        }

        viewModel.error.observe(this) { errorMsg ->
            Toast.makeText(this, errorMsg, Toast.LENGTH_LONG).show()
        }
    }



}