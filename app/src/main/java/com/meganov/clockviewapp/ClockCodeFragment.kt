package com.meganov.clockviewapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

/**
 * Fragment to show the clock adding it from the code
 */
class ClockCodeFragment : Fragment() {
    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_clock_code, container, false)
        val clockView = context?.let { ClockView(it) }
        val layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        clockView?.layoutParams = layoutParams
        val layout = view.findViewById<ViewGroup>(R.id.clockFragment)
        layout.addView(clockView)
        return view
    }
}

