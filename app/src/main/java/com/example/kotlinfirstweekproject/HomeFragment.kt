package com.example.kotlinfirstweekproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Switch
import androidx.databinding.DataBindingUtil
import com.example.kotlinfirstweekproject.databinding.ActivityMainBinding
import com.example.kotlinfirstweekproject.databinding.FragmentAllSwitchBinding

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentAllSwitchBinding
    var isSwitchEnabled : Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_all_switch, container, false)
        binding.fragment = this
        return binding.root
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

    fun disableAllSwitches(){
        binding.swt1.isChecked = false
        binding.swt2.isChecked = false
        binding.swt3.isChecked = false
        binding.swt4.isChecked = false
        binding.swt5.isChecked = false
    }

}