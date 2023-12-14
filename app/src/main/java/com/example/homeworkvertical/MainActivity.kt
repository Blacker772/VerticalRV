package com.example.homeworkvertical

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.homeworkrvvertical.R

import com.example.homeworkrvvertical.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null
    private val list = mutableListOf<MyInformation>()
    private val adapter = MyAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        binding?.recyclerView?.adapter = adapter
        binding?.recyclerView?.layoutManager = LinearLayoutManager(
            this, RecyclerView.VERTICAL,
            false
        )
        list.addAll(listOfProducts())

        binding?.totalCount?.text = calculateSumOfPrice(list).toString()
        adapter.updateProductList(list)
        adapter.setOnClickListener(object : MyAdapter.ClickRecyclerView{
            override fun onClick(model: MyInformation) {

            }

            override fun onCountChange(newCount: Int, position: Int) {
                list[position].productCount = newCount
                val sum = calculateSumOfPrice(list)
                binding?.totalCount?.text = sum.toString()
                adapter.updateProductList(list)
            }

        })

    }

    private fun listOfProducts() = listOf(
        MyInformation(R.drawable.rectangle_7, "The Joni High Rise Loose 29L", "Black", "M","$100", 10, 1),
        MyInformation(R.drawable.rectangle_4, "Graydon Button-Up", "Brown", "XL", "$100",15, 1),
        MyInformation(R.drawable.rectangle_7, "Desire Vest", "Heather Oat Beige", "M", "$100",20,1 )
    )

    private fun calculateSumOfPrice(listOfProduct: List<MyInformation>): Int {
        var sum = 0
        listOfProduct.forEach {
            sum += it.priceValue * it.productCount
        }
        return sum
    }
}