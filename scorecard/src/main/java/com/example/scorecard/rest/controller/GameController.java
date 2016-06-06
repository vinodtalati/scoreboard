package com.example.scorecard.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.scorecard.adapter.RestAdapterVer1;
import com.example.scorecard.common.util.Constants;
import com.example.scorecard.rest.domain.GameResource;

@RestController
@RequestMapping(Constants.API_URL_V1 + "game")
public class GameController {

	private final RestAdapterVer1 apiAdapter;

	@Autowired
	public GameController(final RestAdapterVer1 apiAdapter) {
		this.apiAdapter = apiAdapter;
	}

	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public List<GameResource> getAllGames() throws Exception {
		return apiAdapter.getAllGames();
	}

	@RequestMapping(value = "/{gameId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<GameResource> getGame(@PathVariable int gameId)
			throws Exception {
		GameResource resource = apiAdapter.getGame(gameId);
		if (resource == null) {
			return new ResponseEntity<GameResource>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<GameResource>(resource, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<GameResource> addGame(
			@RequestBody GameResource gameResource, UriComponentsBuilder builder)
			throws Exception {
		GameResource resource = apiAdapter.addGame(gameResource);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(builder.path(Constants.API_URL_V1 + "games/{id}")
				.buildAndExpand(resource.getId()).toUri());
		return new ResponseEntity<GameResource>(resource, headers,
				HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{gameId}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<GameResource> updateGame(@PathVariable int gameId,
			@RequestBody GameResource gameResource, UriComponentsBuilder builder)
			throws Exception {
		if (gameResource == null || gameResource.getId() < 0 || gameId < 0
				|| gameId != gameResource.getId()) {
			throw new IllegalArgumentException("ValidationErrors.InvalidId");
		}

		GameResource resource = apiAdapter.updateGame(gameResource);
		if (resource == null) {
			return new ResponseEntity<GameResource>(HttpStatus.NOT_FOUND);
		}
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(builder.path(Constants.API_URL_V1 + "games/{id}")
				.buildAndExpand(resource.getId()).toUri());
		return new ResponseEntity<GameResource>(resource, headers,
				HttpStatus.OK);
	}

}
