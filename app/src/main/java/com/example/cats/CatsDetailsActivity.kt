package com.example.cats

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.example.cats.databinding.ActivityCatsDetailsBinding
import com.example.cats.model.CatModel
import java.io.Serializable

class CatsDetailsActivity : AppCompatActivity() {

    private var binding : ActivityCatsDetailsBinding? = null
    private var catDetails : CatModel? = null

    private inline fun <reified T : Serializable> Intent.serializable(key: String): T? = when {
        Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU -> getSerializableExtra(key, T::class.java)
        else -> @Suppress("DEPRECATION") getSerializableExtra(key) as? T
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCatsDetailsBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        if(intent.hasExtra(MainActivity.CAT_DETAILS)){
            catDetails = intent.serializable(MainActivity.CAT_DETAILS)
                    as CatModel?
        } 

        setSupportActionBar()

        setDescriptionAndIcon()
    }

    private fun setDescriptionAndIcon(){
        binding?.tvCatDescription?.text = catDetails?.description

        when(catDetails?.id){

            1 -> {
                binding?.ivCatIcon?.setImageDrawable(ContextCompat.getDrawable(this,
                    R.drawable.cat_one))

            }

            2 -> {
                binding?.ivCatIcon?.setImageDrawable(ContextCompat.getDrawable(this,
                    R.drawable.cat_two))

            }

            3 -> {
                binding?.ivCatIcon?.setImageDrawable(ContextCompat.getDrawable(this,
                    R.drawable.cat_three))

            }

        }
    }

    private fun setSupportActionBar(){
        if (catDetails != null) {
            setSupportActionBar(binding?.tbDetailsActivity)

            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar!!.title = catDetails!!.name

            binding?.tbDetailsActivity?.setNavigationOnClickListener {
                onBackPressedDispatcher.onBackPressed()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        binding = null
    }
}