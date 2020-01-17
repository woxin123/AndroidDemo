package com.example.androiddemo.jetpack_demo.navigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation

import com.example.androiddemo.R
import kotlinx.android.synthetic.main.fragment_my_page2.*


class MyPage2Fragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_page2, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn.setOnClickListener {
            Navigation.findNavController(it).navigateUp()
        }

        btn2.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_page3)
        }
    }

}
