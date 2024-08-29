package com.example.kotlinfirstweekproject

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.Switch
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import app.rive.runtime.kotlin.core.Rive
import com.example.kotlinfirstweekproject.databinding.ActivityMainBinding
import com.example.kotlinfirstweekproject.databinding.FragmentAllSwitchBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.snackbar.Snackbar
import java.util.ArrayList

class HomeFragment : Fragment() {

    lateinit var binding: FragmentAllSwitchBinding
    private lateinit var bottomNav : BottomNavigationView
    var isSwitchEnabled : Boolean = false
    private val switchOrder = mutableListOf<Int>()  // acilan switchlerin sirasini tutmak icin liste olusturuldu.

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_all_switch, container, false)
        binding.fragment = this

        // mainactitvity'den bottomnavbar alindi.
        bottomNav = requireActivity().findViewById(R.id.bottomNav)

        // kaydedilen bottom nav state i
        if (savedInstanceState != null) {
            val restoredOrder = savedInstanceState.getIntegerArrayList("bottom_nav_items")
            if (restoredOrder != null) {
                for (id in restoredOrder) {
                    val (icon, text) = when (id) {
                        R.id.swt1 -> R.drawable.happy to "Happiness"
                        R.id.swt2 -> R.drawable.sunny to "Optimizm"
                        R.id.swt3 -> R.drawable.kindness to "Kindness"
                        R.id.swt4 -> R.drawable.give_love to "Giving"
                        R.id.swt5 -> R.drawable.relationship to "Respect"
                        else -> R.drawable.home to "Home"
                    }
                    bottomNav.menu.add(Menu.NONE, id, Menu.NONE, text).setIcon(icon)
                    switchOrder.add(id)
                }
            }
        } else {
            bottomNav.visibility = View.GONE
        }

//        bottomNav.setOnNavigationItemSelectedListener { item ->
//            navigateToFragment(item.itemId)
//            true
//        }

        Rive.init(requireContext())
        binding.swt6.isChecked = true
        bottomNav.visibility = View.GONE
        disableAllSwitches()
        return binding.root
    }


    override fun onResume() {
        super.onResume()
        if (!binding.swt6.isChecked) {
            bottomNav.visibility = View.VISIBLE
            enableAllSwitches()
        } else{
            disableAllSwitches()
        }
    }


    fun onSwitchClicked(switch: Switch){
        if (binding.swt6.isChecked){
            switch.isChecked = false
        }else{
            updateBottomNav(switch)
        }
    }

    fun onSwitch6Clicked(){
        isSwitchEnabled = !binding.swt6.isChecked
        binding.invalidateAll()

        if (binding.swt6.isChecked){
            bottomNav.visibility = View.GONE
            disableAllSwitches()
        }else{
            bottomNav.visibility = View.VISIBLE
            resetBottomNav()
        }
    }

    private fun enableAllSwitches() {
        isSwitchEnabled = true
        binding.swt1.isEnabled = true
        binding.swt2.isEnabled = true
        binding.swt3.isEnabled = true
        binding.swt4.isEnabled = true
        binding.swt5.isEnabled = true
    }

    private fun disableAllSwitches(){
        binding.swt1.isChecked = false
        binding.swt2.isChecked = false
        binding.swt3.isChecked = false
        binding.swt4.isChecked = false
        binding.swt5.isChecked = false
        isSwitchEnabled = false
        binding.invalidateAll()
    }


    private fun resetBottomNav() {
        bottomNav.menu.clear()
        bottomNav.menu.add(Menu.NONE, R.id.homeFragment, Menu.NONE, "Home").setIcon(R.drawable.home)
        switchOrder.clear()
    }

    private fun updateBottomNav(switch: Switch) {
        val switchId = when (switch.id) {
            R.id.swt1 -> R.id.swt1Fragment
            R.id.swt2 -> R.id.swt2Fragment
            R.id.swt3 -> R.id.swt3Fragment
            R.id.swt4 -> R.id.swt4Fragment
            R.id.swt5 -> R.id.switch5Fragment
            else -> return
        }

        if (switch.isChecked) {
            if (switchOrder.size < 4) {
                switchOrder.add(switchId)
                val (icon, text) = when (switch.id) {
                    R.id.swt1 -> R.drawable.happy to "Happiness"
                    R.id.swt2 -> R.drawable.sunny to "Optimizm"
                    R.id.swt3 -> R.drawable.kindness to "Kindness"
                    R.id.swt4 -> R.drawable.give_love to "Giving"
                    R.id.swt5 -> R.drawable.relationship to "Respect"
                    else -> R.drawable.home to "Home"
                }
                bottomNav.menu.add(Menu.NONE, switchId, Menu.NONE, text).setIcon(icon)
            } else {
                Snackbar.make(binding.root, "En fazla 4 item eklenebilir!!!!", Snackbar.LENGTH_LONG).show()
                Toast.makeText(requireContext(), "En fazla 4 item eklenebilir!!", Toast.LENGTH_SHORT).show()
                switchOrder.add(switchId)
            }
        } else {
            switchOrder.remove(switchId)
            bottomNav.menu.removeItem(switchId)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putIntegerArrayList("bottom_nav_items", ArrayList(switchOrder))
    }

    fun navigateToFragment(destinationId: Int) {
        val navOptions = NavOptions.Builder()
            .setEnterAnim(R.anim.slide_in_right)
            .setExitAnim(R.anim.lside_out_left)
            .setPopEnterAnim(R.anim.slide_in_left)
            .setPopExitAnim(R.anim.slide_out_right)
            .build()

        findNavController().navigate(destinationId, null, navOptions)
    }
}