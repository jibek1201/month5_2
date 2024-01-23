package com.example.month5_2

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.month5_2.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initClicker()
    }

    private fun initClicker() {
        with(binding) {
            btn.setOnClickListener {
                RetrofitService().api.getCompatibility(
                    firstEd.text.toString(),
                    secondEd.text.toString()
                ).enqueue(object : Callback<LoveModel>{
                    @SuppressLint("SetTextI18n")
                    override fun onResponse(call: Call<LoveModel>, response: Response<LoveModel>) {
                        if (response.isSuccessful)
                            response.body()?.let {
                                resultTv.text = "${it.percentage}\n ${it.result}"
                            }
                    }

                    override fun onFailure(call: Call<LoveModel>, t: Throwable) {
                        Log.e("ololo" , "onFailure: ${t.message}")
                    }

                })
        }
}
    }
}




