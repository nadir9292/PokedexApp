package com.example.pokedexapp.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.example.pokedexapp.databinding.ActivityMainBinding
import com.squareup.picasso.Picasso
import model.PokemonViewModel

class MainActivity : AppCompatActivity(), View.OnClickListener  {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val model by lazy { ViewModelProvider(this).get(PokemonViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        supportActionBar?.hide()

        model.data.observe(this) {
            if (it != null) {
                binding.pokeName.text = it.forms[0].name.uppercase()
                Picasso.get().load(it.sprites.front_default).into(binding.pokeSprite)
                binding.pokeHeight.text = it.height.toString()
                binding.pokeWeight.text = it.weight.toString()
                binding.pokeType.text = it.types[0].type.name
                binding.pokeHp.text = it.stats[0].base_stat.toString()
                binding.pokeAttack.text = it.stats[1].base_stat.toString()
                binding.pokeDefense.text = it.stats[2].base_stat.toString()
                binding.pokeSpeattack.text = it.stats[3].base_stat.toString()
                binding.pokeSpedefense.text = it.stats[4].base_stat.toString()
                binding.pokeSpeed.text = it.stats[5].base_stat.toString()
            }

        }

        model.errorMessage.observe(this) {
            if (it != null) {
                binding.tvError.isVisible = true
                binding.tvError.text = it
            } else {
                binding.tvError.isVisible = false
            }
        }

        binding.btLoad.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        model.loadData()
    }
}
