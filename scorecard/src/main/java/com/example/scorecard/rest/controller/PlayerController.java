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
import com.example.scorecard.rest.domain.PlayerResource;
import com.example.scorecard.rest.domain.PlayerStatResource;

@RestController
@RequestMapping(Constants.API_URL_V1 + "player")
public class PlayerController {

	private final RestAdapterVer1 apiAdapter;

	@Autowired
	public PlayerController(final RestAdapterVer1 apiAdapter) {
		this.apiAdapter = apiAdapter;
	}

	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public List<PlayerResource> getAllPlayers() throws Exception {
		return apiAdapter.getAllPlayers();
	}

	@RequestMapping(value = "/{playerId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<PlayerResource> getPlayer(@PathVariable int playerId)
			throws Exception {
		PlayerResource resource = apiAdapter.getPlayer(playerId);
		if (resource == null) {
			return new ResponseEntity<PlayerResource>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<PlayerResource>(resource, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PlayerResource> addPlayer(
			@RequestBody PlayerResource playerResource,
			UriComponentsBuilder builder) throws Exception {
		PlayerResource resource = apiAdapter.addPlayer(playerResource);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(builder.path(Constants.API_URL_V1 + "/{playerId}")
				.buildAndExpand(resource.getId()).toUri());
		return new ResponseEntity<PlayerResource>(resource, headers,
				HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{playerId}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PlayerResource> updatePlayer(
			@PathVariable int playerId,
			@RequestBody PlayerResource playerResource,
			UriComponentsBuilder builder) throws Exception {
		if (playerResource == null || playerResource.getId() < 0
				|| playerId < 0 || playerId != playerResource.getId()) {
			throw new IllegalArgumentException("ValidationErrors.InvalidId");
		}

		PlayerResource resource = apiAdapter.updatePlayer(playerResource);
		if (resource == null) {
			return new ResponseEntity<PlayerResource>(HttpStatus.NOT_FOUND);
		}
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(builder.path(Constants.API_URL_V1 + "/{playerId}")
				.buildAndExpand(resource.getId()).toUri());
		return new ResponseEntity<PlayerResource>(resource, headers,
				HttpStatus.OK);
	}

	@RequestMapping(value = "/gamestats", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PlayerStatResource> addPlayerStat(
			@RequestBody PlayerStatResource playerStatResource,
			UriComponentsBuilder builder) throws Exception {
		PlayerStatResource resource = apiAdapter
				.addPlayerStat(playerStatResource);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(builder
				.path(Constants.API_URL_V1
						+ "player/{playerid}/gamestats/{gameid}")
				.buildAndExpand(resource.getPlayerId(), resource.getGameId())
				.toUri());
		return new ResponseEntity<PlayerStatResource>(resource, headers,
				HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{playerId}/gamestats/{gameId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<PlayerStatResource> getPlayerStatsForGame(
			@PathVariable int playerId, @PathVariable int gameId)
			throws Exception {
		PlayerStatResource resource = apiAdapter.getPlayerStatsForGame(
				playerId, gameId);
		if (resource == null) {
			return new ResponseEntity<PlayerStatResource>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<PlayerStatResource>(resource, HttpStatus.OK);
	}

}
