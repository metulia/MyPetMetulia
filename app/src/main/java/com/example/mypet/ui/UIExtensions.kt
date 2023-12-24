package com.example.mypet.ui

import android.app.Activity
import android.content.Context
import android.text.format.DateFormat
import android.view.View
import android.widget.Toast
import android.widget.Toolbar
import androidx.fragment.app.Fragment
import com.example.mypet.app.R
import com.example.mypet.domain.pet.breed.PetBreedCat
import com.example.mypet.domain.pet.breed.PetBreedChameleon
import com.example.mypet.domain.pet.breed.PetBreedDog
import com.example.mypet.domain.pet.breed.PetBreedSnake
import com.example.mypet.domain.pet.breed.PetBreedSpider
import com.example.mypet.domain.pet.kind.PetKind
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import java.util.Calendar

fun View.snackMessage(text: String, length: Int = Snackbar.LENGTH_SHORT) {
    Snackbar.make(this, text, length).show()
}

fun getPetsAge(timeMillis: Long): String {
    val birthDate = Calendar.getInstance()
    birthDate.timeInMillis = timeMillis

    val currentDate = Calendar.getInstance()

    val years = currentDate.get(Calendar.YEAR) - birthDate.get(Calendar.YEAR)
    val months = currentDate.get(Calendar.MONTH) - birthDate.get(Calendar.MONTH)

    return if (months < 0) {
        val correctedYears = years - 1
        val correctedMonths = months + 12
        getAgeString(correctedYears, correctedMonths)
    } else {
        getAgeString(years, months)
    }
}

private fun getAgeString(years: Int, months: Int): String {
    val yearsString = when {
        years % 10 == 1 && years != 11 -> "$years год"
        years % 10 in 2..4 && (years % 100 < 10 || years % 100 >= 20) -> "$years года"
        else -> "$years лет"
    }

    val monthsString = when {
        months % 10 == 1 && months != 11 -> "$months месяц"
        months % 10 in 2..4 && (months % 100 < 10 || months % 100 >= 20) -> "$months месяца"
        else -> "$months месяцев"
    }

    return "$yearsString $monthsString"
}

fun Activity.showToast(text: String, length: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, text, length).show()
}

fun getPetName(kindOrdinal: Int, breedOrdinal: Int?) =
    getPetBreedName(kindOrdinal, breedOrdinal) ?: PetKind.values()[kindOrdinal].nameResId

private fun getPetBreedName(kindOrdinal: Int, breedOrdinal: Int?) =
    breedOrdinal?.let {
        try {
            when (kindOrdinal) {
                0 -> PetBreedCat.values()[breedOrdinal].nameResId
                1 -> PetBreedDog.values()[breedOrdinal].nameResId
                12 -> PetBreedChameleon.values()[breedOrdinal].nameResId
                14 -> PetBreedSnake.values()[breedOrdinal].nameResId
                17 -> PetBreedSpider.values()[breedOrdinal].nameResId
                else -> null
            }
        } catch (e: Exception) {
            println("UIExtensions -> getPetBreedIcon(): ${e.message}")
            null
        }
    }

fun getPetBreedList(kindOrdinal: Int) =
    try {
        when (kindOrdinal) {
            0 -> PetBreedCat.values().map { it.nameResId }
            1 -> PetBreedDog.values().map { it.nameResId }
            12 -> PetBreedChameleon.values().map { it.nameResId }
            14 -> PetBreedSnake.values().map { it.nameResId }
            17 -> PetBreedSpider.values().map { it.nameResId }
            else -> null
        }
    } catch (e: Exception) {
        println("UIExtensions -> getPetBreedIcon(): ${e.message}")
        null
    }

fun getPetIcon(kindOrdinal: Int, breedOrdinal: Int?) =
    getPetBreedIcon(kindOrdinal, breedOrdinal) ?: PetKind.values()[kindOrdinal].iconResId

private fun getPetBreedIcon(kindOrdinal: Int, breedOrdinal: Int?) =
    breedOrdinal?.let {
        try {
            when (kindOrdinal) {
                1 -> PetBreedCat.values()[breedOrdinal].iconResId
                2 -> PetBreedDog.values()[breedOrdinal].iconResId
                13 -> PetBreedChameleon.values()[breedOrdinal].iconResId
                15 -> PetBreedSnake.values()[breedOrdinal].iconResId
                18 -> PetBreedSpider.values()[breedOrdinal].iconResId
                else -> null
            }
        } catch (e: Exception) {
            println("UIExtensions -> getPetBreedIcon(): ${e.message}")
            null
        }
    }

fun Fragment.getActionBar() =
    (requireActivity() as? MainActivity)?.supportActionBar

fun Fragment.getToolbar() =
    (requireActivity() as? MainActivity)?.findViewById<Toolbar>(R.id.toolbar)

fun Fragment.getFloatingActionButton() =
    (requireActivity() as? MainActivity)?.findViewById<FloatingActionButton>(R.id.floatingActionButton)

val Context.is24HourFormat
    get() = DateFormat.is24HourFormat(this)