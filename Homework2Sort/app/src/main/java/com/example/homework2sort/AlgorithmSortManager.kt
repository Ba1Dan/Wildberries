package com.example.homework2sort

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.*
import kotlin.random.Random

class AlgorithmSortManager {

    suspend fun sortDefault(dataStructure: String, size: Int) {
        when (dataStructure) {
            DATA_STRUCTURE_ARRAY -> {
                sortDefault(randomArray(size))
            }
            DATA_STRUCTURE_ARRAY_LIST -> {
                sortDefault(randomArrayList(size))
            }
            DATA_STRUCTURE_LINKED_LIST -> {
                sortDefault(randomLinkedList(size))
            }
        }
    }

    suspend fun sortBubbleSort(dataStructure: String, size: Int) {
        when (dataStructure) {
            DATA_STRUCTURE_ARRAY -> {
                sortBubbleSort(randomArray(size))
            }
            DATA_STRUCTURE_ARRAY_LIST -> {
                sortBubbleSort(randomArrayList(size))
            }
            DATA_STRUCTURE_LINKED_LIST -> {
                sortBubbleSort(randomLinkedList(size))
            }
        }
    }

    suspend fun sortQuickSOrt(dataStructure: String, size: Int) {
        when (dataStructure) {
            DATA_STRUCTURE_ARRAY -> {
                sortQuickSort(randomArray(size))
            }
            DATA_STRUCTURE_ARRAY_LIST -> {
                sortQuickSort(randomArrayList(size))
            }
            DATA_STRUCTURE_LINKED_LIST -> {
                sortQuickSort(randomLinkedList(size))
            }
        }
    }

    private suspend fun sortDefault(array: Array<Int>) = withContext(Dispatchers.IO) {
        array.sort()
    }

    private suspend fun sortDefault(arrayList: MutableList<Int>) = withContext(Dispatchers.IO) {
        arrayList.sort()
    }

    private suspend fun sortBubbleSort(array: Array<Int>) = withContext(Dispatchers.IO) {
        for (i in 0 until (array.size - 1)) {
            for (j in 0 until (array.size - i - 1)) {
                if (array[j] > array[j + 1]) {
                    val tmp = array[j]
                    array[j] = array[j + 1]
                    array[j + 1] = tmp
                }
            }
        }
    }

    private suspend fun sortBubbleSort(list: MutableList<Int>) = withContext(Dispatchers.IO) {
        for (i in 0 until (list.size - 1)) {
            for (j in 0 until (list.size - i - 1)) {
                if (list[j] > list[j + 1]) {
                    val tmp = list[j]
                    list[j] = list[j + 1]
                    list[j + 1] = tmp
                }
                Log.d("sortBubbleSort", "i: $i  j: $j")
            }
        }
    }

    private suspend fun sortQuickSort(array: Array<Int>) = withContext(Dispatchers.IO) {
        quickSort(array, 0, array.size - 1)
    }

    private suspend fun sortQuickSort(list: MutableList<Int>) = withContext(Dispatchers.IO) {
        quickSort(list, 0, list.size - 1)
    }

    private fun quickSort(array: Array<Int>, low: Int, high: Int) {
        if (array.isEmpty()) return
        if (low >= high) return

        val pivot = array[low + (high - low) / 2]

        var i: Int = low
        var j: Int = high
        while (i <= j) {
            while (array[i] < pivot) {
                i++
            }
            while (array[j] > pivot) {
                j--
            }
            if (i <= j) {
                val temp = array[i]
                array[i] = array[j]
                array[j] = temp
                i++
                j--
            }
        }

        if (low < j) quickSort(array, low, j)

        if (high > i) quickSort(array, i, high)
    }


    private fun quickSort(list: MutableList<Int>, low: Int, high: Int) {
        if (list.isEmpty()) return
        if (low >= high) return

        val pivot = list[low + (high - low) / 2]

        var i: Int = low
        var j: Int = high
        while (i <= j) {
            while (list[i] < pivot) {
                i++
            }
            while (list[j] > pivot) {
                j--
            }
            if (i <= j) {
                val temp = list[i]
                list[i] = list[j]
                list[j] = temp
                i++
                j--
            }
        }

        if (low < j) quickSort(list, low, j)

        if (high > i) quickSort(list, i, high)
    }

    private fun randomArray(length: Int): Array<Int> {
        val array = Array(length) {
            0
        }
        for (i in 0 until length) {
            array[i] = Random.nextInt(0, 1000)
        }
        return array
    }

    private fun randomArrayList(length: Int): ArrayList<Int> {
        val list = ArrayList<Int>()
        for (i in 0 until length) {
            list.add(Random.nextInt(0, 1000))
        }
        return list
    }

    private fun randomLinkedList(length: Int): LinkedList<Int> {
        val list = LinkedList<Int>()
        for (i in 0 until length) {
            list.add(Random.nextInt(0, 1000))
        }
        return list
    }

    companion object {
        private const val DATA_STRUCTURE_ARRAY = "Array"
        private const val DATA_STRUCTURE_ARRAY_LIST = "ArrayList"
        private const val DATA_STRUCTURE_LINKED_LIST = "LinkedList"
    }
}