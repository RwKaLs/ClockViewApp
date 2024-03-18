package com.meganov.clockviewapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment

/**
 * Fragment to show many clocks of different sizes adding them from the code
 */
class MultipleClocksFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_multiple_clocks, container, false)
        val layout = view.findViewById<LinearLayout>(R.id.clock_layout)
        for (i in 100..1000 step 100) {
            val clockView = context?.let { ClockView(it) }
            val layoutParams = LinearLayout.LayoutParams(i, i)
            clockView?.layoutParams = layoutParams
            layout.addView(clockView)
        }

        return view
    }
}
