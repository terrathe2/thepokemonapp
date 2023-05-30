package com.redhaputra.detailpokemon

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.redhaputra.commons.ui.Constants.POKEMON_NAME_KEY
import com.redhaputra.commons.ui.base.BaseFragment
import com.redhaputra.core.shared.SharedMainViewModel
import com.redhaputra.detailpokemon.databinding.FragmentDetailPokemonBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import com.redhaputra.commons.ui.R as RC

/**
 * Detail Pokemon Fragment to show pokemon data.
 */
@AndroidEntryPoint
class DetailPokemonFragment :
    BaseFragment<FragmentDetailPokemonBinding>(R.layout.fragment_detail_pokemon) {

    private val viewModel: DetailPokemonViewModel by viewModels()
    private val sharedViewModel: SharedMainViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initFragment()
        observeViewAction()
        observeViewModel()
    }

    override fun onStart() {
        super.onStart()
        // to hide bottom bar when detail screen showed
        sharedViewModel.toggleBottomBar(false)
    }

    override fun onInitDataBinding() {
        viewBinding.viewModel = viewModel
    }

    private fun initFragment() {
        val pokemonName = arguments?.getString(POKEMON_NAME_KEY)

        if (!pokemonName.isNullOrEmpty()) {
            viewModel.setPokemonName(pokemonName)
            viewModel.getDetailPokemon(pokemonName)
        }
    }

    private fun observeViewAction() {
        viewBinding.btnBack.setOnClickListener {
            onBackPressed()
        }
    }

    private fun observeViewModel() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.errorEvent.collectLatest {
                Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
            }
        }

        viewModel.pokemonData.observe(viewLifecycleOwner) {
            Glide.with(requireContext())
                .load(it.imgUrl)
                .centerCrop()
                .placeholder(RC.drawable.empty_pokemon_img_placeholder)
                .into(viewBinding.ivPokemon)
        }
    }

    private fun onBackPressed() {
        findNavController().popBackStack()
    }
}