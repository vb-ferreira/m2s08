package com.rest.pokedex.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rest.pokedex.models.Pokemon;
import com.rest.pokedex.repositories.PokemonRepository;

@Service
public class PokemonService {

	@Autowired
	private PokemonRepository pokemonRepository;
	
	public Pokemon salvar(Pokemon pokemon) {
		return pokemonRepository.save(pokemon);
	}
	
	public List<Pokemon> buscarTodos() {
		return pokemonRepository.findAll();
	}
	
}
