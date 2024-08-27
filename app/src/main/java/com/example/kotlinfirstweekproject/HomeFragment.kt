package com.example.kotlinfirstweekproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.Switch
import androidx.databinding.DataBindingUtil
import com.example.kotlinfirstweekproject.databinding.ActivityMainBinding
import com.example.kotlinfirstweekproject.databinding.FragmentAllSwitchBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.util.ArrayList

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentAllSwitchBinding
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

        // BottomNavigationBar'ı geri yükle
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

        binding.swt6.isChecked = true
        bottomNav.visibility = View.GONE
        disableAllSwitches()
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        // HomeFragment geri geldiğinde BottomNavigationBar görünür olsun
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
                // 5. öğeyi eklemek yerine en son eklenen öğeyi kaldırın
                val removedId = switchOrder.removeAt(0)
                bottomNav.menu.removeItem(removedId)
                switchOrder.add(switchId)
                val (icon,text) = when (switch.id){
                    R.id.swt1 -> R.drawable.happy to "Happiness"
                    R.id.swt2 -> R.drawable.sunny to "Optimizm"
                    R.id.swt3 -> R.drawable.kindness to "Kindness"
                    R.id.swt4 -> R.drawable.give_love to "Giving"
                    R.id.swt5 -> R.drawable.relationship to "Respect"
                    else -> R.drawable.home to "Home"
                }
                bottomNav.menu.add(Menu.NONE, switchId, Menu.NONE, text).setIcon(icon)
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
}