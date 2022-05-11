package com.example.homework2sort

import android.os.Bundle
import android.widget.ProgressBar
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.google.android.material.button.MaterialButton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class MainActivity : AppCompatActivity() {

    private lateinit var spinnerDataStructure: Spinner
    private lateinit var spinnerNumbers: Spinner
    private lateinit var spinnerAlgorithmSort: Spinner
    private lateinit var btnSort: MaterialButton
    private lateinit var tvTime: TextView
    private lateinit var progressBar: ProgressBar
    private lateinit var algorithmSortManager: AlgorithmSortManager

    private val scope: CoroutineScope = CoroutineScope(Dispatchers.IO)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()
        algorithmSortManager = AlgorithmSortManager()
        btnSort.setOnClickListener {
            sort()
        }
    }

    private fun initView() {
        spinnerAlgorithmSort = findViewById(R.id.spinner_algorithm_sort)
        spinnerDataStructure = findViewById(R.id.spinner_data_structure)
        spinnerNumbers = findViewById(R.id.spinner_number)
        btnSort = findViewById(R.id.btn_sort)
        tvTime = findViewById(R.id.tv_time)
        progressBar = findViewById(R.id.pb_main)
    }

    private fun sort() {
        progressBar.isVisible = true
        val dataStructure = spinnerDataStructure.selectedItem as String
        val algorithmSort = spinnerAlgorithmSort.selectedItem as String
        val size = (spinnerNumbers.selectedItem as String).toInt()

        Toast.makeText(this, "$dataStructure $algorithmSort $size", Toast.LENGTH_SHORT).show()
        val startTime = System.currentTimeMillis()

        when (algorithmSort) {
            ALGORITHM_DEFAULT_SORT -> {
                scope.launch {
                    algorithmSortManager.sortDefault(dataStructure, size)
                    showTime(startTime)
                }
            }
            ALGORITHM_BUBBLE_SORT -> {
                scope.launch {
                    algorithmSortManager.sortBubbleSort(dataStructure, size)
                    showTime(startTime)
                }
            }
            ALGORITHM_QUICK_SORT -> {
                scope.launch {
                    algorithmSortManager.sortQuickSOrt(dataStructure, size)
                    showTime(startTime)
                }
            }
        }
    }

    private suspend fun showTime(startTime: Long) = withContext(Dispatchers.Main) {
        tvTime.text = "Time: ${System.currentTimeMillis() - startTime} ms"
        progressBar.isVisible = false
    }

    companion object {
        private const val ALGORITHM_QUICK_SORT = "Quick Sort"
        private const val ALGORITHM_BUBBLE_SORT = "Bubble Sort"
        private const val ALGORITHM_DEFAULT_SORT = "Default Sort"
    }
}