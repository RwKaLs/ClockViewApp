package com.meganov.clockviewapp

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: ClockVM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this)[ClockVM::class.java]

        val previousButton = findViewById<Button>(R.id.previous)
        val nextButton = findViewById<Button>(R.id.next)

        // Observe the current state from viewModel
        viewModel.currentState.observe(this) { state ->
            val fragment = when (state) {
                0 -> MultipleClocksFragment()
                1 -> ClockXMLFragment()
                2 -> ClockCodeFragment()
                else -> throw IllegalStateException("Invalid state")
            }
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, fragment)
                .commit()
        }

        // Observe the visibility of the buttons
        viewModel.isPreviousVisible.observe(this) { isVisible ->
            previousButton.visibility = if (isVisible) View.VISIBLE else View.INVISIBLE
        }
        viewModel.isNextVisible.observe(this) { isVisible ->
            nextButton.visibility = if (isVisible) View.VISIBLE else View.INVISIBLE
        }

        previousButton.setOnClickListener {
            if (viewModel.currentState.value == 1) {
                viewModel.currentState.value = 0
                viewModel.isPreviousVisible.value = false
            } else if (viewModel.currentState.value == 2) {
                viewModel.currentState.value = 1
                viewModel.isNextVisible.value = true
            }
        }

        nextButton.setOnClickListener {
            if (viewModel.currentState.value == 0) {
                viewModel.currentState.value = 1
                viewModel.isPreviousVisible.value = true
            } else if (viewModel.currentState.value == 1) {
                viewModel.currentState.value = 2
                viewModel.isNextVisible.value = false
            }
        }
    }
}
