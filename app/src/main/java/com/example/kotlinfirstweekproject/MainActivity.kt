package com.example.kotlinfirstweekproject

import android.os.Bundle
import android.widget.Switch
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import com.example.kotlinfirstweekproject.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    var isSwitchEnabled : Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.activity = this
    }

    fun onSwitchClicked(switch: Switch){
        if (binding.swt6.isChecked){
            switch.isChecked = false
        }
    }

    fun onSwitch6Clicked(){
        isSwitchEnabled = !binding.swt6.isChecked
        binding.invalidateAll()

        if (binding.swt6.isChecked){
            disableAllSwitches()
        }
    }

    private fun disableAllSwitches(){
        binding.swt1.isChecked = false
        binding.swt2.isChecked = false
        binding.swt3.isChecked = false
        binding.swt4.isChecked = false
        binding.swt5.isChecked = false
    }
}