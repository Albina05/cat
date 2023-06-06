package com.example.cats

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.cats.databinding.ActivityMainBinding
import com.example.cats.model.CatModel

class MainActivity : AppCompatActivity(), View.OnClickListener{

    private var binding: ActivityMainBinding? = null
    private val catArray: ArrayList<CatModel> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        setSupportActionBar(binding?.tbMainActivity)

        addCats()

        binding?.btnDetailsOne?.setOnClickListener(this)

        binding?.btnDetailsTwo?.setOnClickListener(this)

        binding?.btnDetailsThree?.setOnClickListener(this)
    }

    private fun addCats(){
        catArray.add(0, CatModel(1, binding?.tvCatThreeName?.text.toString(),
            binding?.tvCatThreeDescription?.text.toString())
        )

        catArray.add(0, CatModel(2, binding?.tvCatTwoName?.text.toString(),
            binding?.tvCatTwoDescription?.text.toString()))

        catArray.add(0, CatModel(3, binding?.tvCatOneName?.text.toString(),
            binding?.tvCatOneDescription?.text.toString()))
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    override fun onClick(v: View?) {

        val intent = Intent(this@MainActivity, CatsDetailsActivity::class.java)

        when (v){

            binding?.btnDetailsOne -> {
                intent.putExtra(CAT_DETAILS, catArray[0])
            }

            binding?.btnDetailsTwo -> {
                intent.putExtra(CAT_DETAILS, catArray[1])
            }

            binding?.btnDetailsThree -> {
                intent.putExtra(CAT_DETAILS, catArray[2])
            }
        }

        startActivity(intent)
    }

    companion object{
        internal const val CAT_DETAILS = "cat_details"
    }
}