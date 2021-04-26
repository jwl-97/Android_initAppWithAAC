package com.ljw.aactestapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.ljw.aactestapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val viewModel: TestViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main).apply {
            lifecycleOwner = this@MainActivity
            vm = viewModel
        }

        viewModel.setToast("test")

        viewModel.showErrorToast.eventObserve(this) {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }

        viewModel.openEvent.eventObserve(this) { sampleText ->
            val intent = Intent(this, SecondActivity::class.java)
            intent.putExtra("text", sampleText)
            startActivity(intent)
        }
    }
}