package com.example.mytabswipe

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RatingBar
import android.widget.TextView
import android.widget.Toast
import com.example.dailyweather.R


class SecondFragment : Fragment() {



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_second, container, false)
        val rb : RatingBar = view.findViewById(R.id.simpleRatingBar)

        rb.setOnRatingBarChangeListener { _, rating, _ ->
            val ratingText = "Rating :: $rating"
            Toast.makeText(requireContext(), ratingText, Toast.LENGTH_SHORT).show()
        }

        return view
    }
}