package com.example.adityadfn_tib.Message.tutorial

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.content.Intent
import com.example.adityadfn_tib.R
import com.example.adityadfn_tib.SplashScreenActivity

class Tutorial3Fragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_tutorial3, container, false)
        
        val btnStartNow = view.findViewById<Button>(R.id.btnStartNow)
        btnStartNow.setOnClickListener {
            startActivity(Intent(requireContext(), SplashScreenActivity::class.java))
            requireActivity().finish()
        }
        
        return view
    }
}