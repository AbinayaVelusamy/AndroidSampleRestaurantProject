package com.example.myrestaurantapp.ui.practise

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myrestaurantapp.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PractiseActivity : AppCompatActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_practise)
	}
}