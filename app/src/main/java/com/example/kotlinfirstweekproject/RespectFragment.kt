package com.example.kotlinfirstweekproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import app.rive.runtime.kotlin.core.Rive
import com.google.android.material.bottomnavigation.BottomNavigationView

class RespectFragment : Fragment() {
    private val bottomNav : BottomNavigationView? get() = (activity as? MainActivity)?.bottomNav


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_respect, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Rive.init(requireContext())
        bottomNav!!.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.bottom_respect))
    }
}