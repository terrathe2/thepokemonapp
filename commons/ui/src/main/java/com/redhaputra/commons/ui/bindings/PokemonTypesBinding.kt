package com.redhaputra.commons.ui.bindings

import android.graphics.drawable.GradientDrawable
import android.view.ViewGroup
import android.widget.GridLayout
import android.widget.LinearLayout
import android.widget.LinearLayout.LayoutParams
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.setPadding
import androidx.core.view.updateMargins
import androidx.databinding.BindingAdapter
import com.redhaputra.commons.ui.R
import com.redhaputra.commons.ui.model.PokemonDetailData
import java.util.Locale

private const val POKEMON_TYPE_MARGIN = 8

private fun getPokeTypesColor(type: String): Int =
    when (type) {
        "normal" -> R.color.normal_type
        "fire" -> R.color.fire_type
        "water" -> R.color.water_type
        "electric" -> R.color.electric_type
        "grass" -> R.color.grass_type
        "ice" -> R.color.ice_type
        "fighting" -> R.color.fighting_type
        "poison" -> R.color.poison_type
        "ground" -> R.color.ground_type
        "flying" -> R.color.flying_type
        "psychic" -> R.color.psychic_type
        "bug" -> R.color.bug_type
        "rock" -> R.color.rock_type
        "ghost" -> R.color.ghost_type
        "dragon" -> R.color.dragon_type
        "dark" -> R.color.dark_type
        "steel" -> R.color.steel_type
        else -> R.color.fairy_type
    }

/**
 * Set pokemon types list to show
 */
@BindingAdapter("pokemonTypes", requireAll = false)
fun LinearLayout.setPokemonTypes(
    listTypes: List<String>?
) {
    if (childCount > 1) {
        removeViews(0, childCount - 1)
    }

    listTypes?.forEach { type ->
        val newType = TextView(context)
        newType.text = type
        newType.elevation = 10f
        newType.setTextColor(ContextCompat.getColor(context, android.R.color.white))
        newType.setBackgroundResource(R.drawable.bg_round)
        val drw = newType.background as GradientDrawable
        drw.setColor(getPokeTypesColor(type.lowercase()))
        val layoutParams = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
        layoutParams.updateMargins(right = POKEMON_TYPE_MARGIN)
        addView(newType, childCount - 1, layoutParams)
    }
}

/**
 * Set pokemon moves list to show
 */
@BindingAdapter("pokemonMoves", requireAll = false)
fun GridLayout.setPokemonMoves(
    listMoves: List<String>?
) {
    if (childCount > 1) {
        removeViews(0, childCount - 1)
    }

    listMoves?.forEachIndexed { i, move ->
        if (i > 3) {
            return
        }

        val newType = TextView(context)
        newType.text = move.replaceFirstChar { c ->
            if (c.isLowerCase()) {
                c.titlecase(Locale.getDefault())
            } else {
                c.toString()
            }
        }
        newType.elevation = 2f
        newType.setTextColor(ContextCompat.getColor(context, android.R.color.white))
        newType.setBackgroundResource(R.drawable.bg_round)
        val drw = newType.background as GradientDrawable
        drw.setColor(ContextCompat.getColor(context, R.color.content_desc_color))
        val layoutParams = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
        layoutParams.updateMargins(right = POKEMON_TYPE_MARGIN, bottom = POKEMON_TYPE_MARGIN)
        addView(newType, childCount - 1, layoutParams)
    }
}