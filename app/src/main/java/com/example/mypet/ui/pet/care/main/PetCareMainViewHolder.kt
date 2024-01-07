package com.example.mypet.ui.pet.care.main

import android.icu.util.Calendar
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.mypet.app.databinding.FragmentPetRecyclerCareMainBinding
import com.example.mypet.domain.DAY_IN_MILLIS
import com.example.mypet.domain.pet.care.PetCareModel
import com.example.mypet.domain.toAppNextDateTime

class PetCareMainViewHolder(
    private val binding: FragmentPetRecyclerCareMainBinding,
    private val callback: PetCareMainCallback,
) : RecyclerView.ViewHolder(binding.root) {
    private val context = binding.root.context
    private lateinit var petCareModel: PetCareModel

    init {
        binding.root.setOnClickListener {
            callback.onPetCareClick(petCareModel)
        }
    }

    fun bind(petCareModel: PetCareModel) {
        this.petCareModel = petCareModel

        binding.imageViewPetCareRecyclerMainIcon.setImageResource(petCareModel.careType.iconResId)
        binding.textViewPetCareRecyclerMainTitle.text =
            context.getString(petCareModel.careType.titleResId)

        petCareModel.nextStart?.let {
            binding.textViewPetCareRecyclerMainDate.text = toAppNextDateTime(it, context)
        }

        binding.textViewPetCareRecyclerMainDate.visibility =
            petCareModel.nextStart?.let { View.VISIBLE } ?: run { View.INVISIBLE }

        binding.progressBarPetCareRecyclerMain.progress =
            petCareModel.nextStart?.let {
                val calendar = Calendar.getInstance()
                println(it - calendar.timeInMillis)
                println(((it - calendar.timeInMillis) * 100 / DAY_IN_MILLIS).toInt())
                ((it - calendar.timeInMillis) * 100 / DAY_IN_MILLIS).toInt()
            } ?: run { 0 }
    }
}