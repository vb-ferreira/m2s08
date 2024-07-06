package com.rest.pokedex.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rest.pokedex.dtos.PokemonCapturadoRequest;
import com.rest.pokedex.dtos.PokemonCapturadoResponse;
import com.rest.pokedex.dtos.PokemonVistoRequest;
import com.rest.pokedex.dtos.PokemonVistoResponse;
import com.rest.pokedex.dtos.mappers.PokemonMapper;
import com.rest.pokedex.models.Pokemon;
import com.rest.pokedex.services.PokemonService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/pokemon")
public class PokemonController {

	@Autowired
	private PokemonService pokemonService;
	
	@Autowired
	private PokemonMapper pokemonMapper;
	
	@PostMapping("/vistos")
	public ResponseEntity<PokemonVistoResponse> salvar(@RequestBody @Valid PokemonVistoRequest pokemonVistoRequest) {
		Pokemon pokemon = pokemonMapper.vistoToEntity(pokemonVistoRequest);
		Pokemon pokemonSalvo = pokemonService.salvar(pokemon);
		PokemonVistoResponse pokemonVistoResponse = pokemonMapper.vistoToDTO(pokemonSalvo);
		return ResponseEntity.status(HttpStatus.CREATED).body(pokemonVistoResponse);
	}
	
	@PostMapping("/capturados")
	public ResponseEntity<PokemonCapturadoResponse> salvar(@RequestBody @Valid PokemonCapturadoRequest pokemonCapturadoRequest) {
		Pokemon pokemon = pokemonMapper.capturadoToEntity(pokemonCapturadoRequest);
		Pokemon pokemonSalvo = pokemonService.salvar(pokemon);
		PokemonCapturadoResponse pokemonCapturadoResponse = pokemonMapper.capturadoToDTO(pokemonSalvo);
		return ResponseEntity.status(HttpStatus.CREATED).body(pokemonCapturadoResponse);
	}

	@GetMapping("/vistos")
	public ResponseEntity<List<PokemonVistoResponse>> buscarTodosVistos() {
		List<Pokemon> pokemon = pokemonService.buscarTodos(); 
		List<PokemonVistoResponse> pokemonVistoResponse = pokemonMapper.vistosToDTO(pokemon);
		return ResponseEntity.status(HttpStatus.OK).body(pokemonVistoResponse);
	}
	
}
