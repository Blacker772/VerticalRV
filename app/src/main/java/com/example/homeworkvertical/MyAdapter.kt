package com.example.homeworkvertical

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.homeworkrvvertical.databinding.ItemBinding

class MyAdapter(private var listener: ClickRecyclerView? = null): RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    private val listOfProducts = mutableListOf<MyInformation>()
    fun updateProductList(newList: List<MyInformation>){
        listOfProducts.clear()
        listOfProducts.addAll(newList)
        notifyDataSetChanged()
    }


    fun setOnClickListener(listener: ClickRecyclerView){
        this.listener = listener
    }
    interface ClickRecyclerView{
        fun onClick(model: MyInformation)
        fun onCountChange(newCount: Int, position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount() = listOfProducts.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(listOfProducts[position], listener, position)
    }

    inner class MyViewHolder(private val binding: ItemBinding): ViewHolder(binding.root){
        fun bind(data: MyInformation, click: ClickRecyclerView?, position: Int){
            binding.image.setImageResource(data.image)
            binding.tvName.text = data.name
            binding.tvColor.text = data.color
            binding.tvPrice.text = data.priceValue.toString()
            binding.tvSize.text = data.size
            binding.count.text = data.productCount.toString()


            binding.buttonMinus.setOnClickListener {
                if (data.productCount > 1){
                    data.productCount -= 1
                    click?.onCountChange(newCount = data.productCount, position)
                }
            }

            binding.buttonPlus.setOnClickListener {
                data.productCount += 1
                click?.onCountChange(newCount = data.productCount, position)
            }
        }
    }

}