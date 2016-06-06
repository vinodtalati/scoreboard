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
import com.example.scorecard.rest.domain.TeamResource;

@RestController
@RequestMapping(Constants.API_URL_V1 + "team")
public class TeamController {

	private final RestAdapterVer1 apiAdapter;

	@Autowired
	public TeamController(final RestAdapterVer1 apiAdapter) {
		this.apiAdapter = apiAdapter;
	}

	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public List<TeamResource> getAllTeams() throws Exception {
		return apiAdapter.getAllTeams();
	}

	@RequestMapping(value = "/{teamId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<TeamResource> getTeam(@PathVariable int teamId)
			throws Exception {
		TeamResource resource = apiAdapter.getTeam(teamId);
		if (resource == null) {
			return new ResponseEntity<TeamResource>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<TeamResource>(resource, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<TeamResource> addTeam(
			@RequestBody TeamResource teamResource, UriComponentsBuilder builder)
			throws Exception {
		TeamResource resource = apiAdapter.addTeam(teamResource);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(builder.path(Constants.API_URL_V1 + "teams/{id}")
				.buildAndExpand(resource.getId()).toUri());
		return new ResponseEntity<TeamResource>(resource, headers,
				HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{teamId}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<TeamResource> updateTeam(@PathVariable int teamId,
			@RequestBody TeamResource teamResource, UriComponentsBuilder builder)
			throws Exception {
		if (teamResource == null || teamResource.getId() < 0 || teamId < 0
				|| teamId != teamResource.getId()) {
			throw new IllegalArgumentException("ValidationErrors.InvalidId");
		}

		TeamResource resource = apiAdapter.updateTeam(teamResource);
		if (resource == null) {
			return new ResponseEntity<TeamResource>(HttpStatus.NOT_FOUND);
		}
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(builder.path(Constants.API_URL_V1 + "teams/{id}")
				.buildAndExpand(resource.getId()).toUri());
		return new ResponseEntity<TeamResource>(resource, headers,
				HttpStatus.OK);
	}

	/*
	 * @RequestMapping(value = "/{teamId}", method = RequestMethod.DELETE,
	 * produces = MediaType.APPLICATION_JSON_VALUE) public
	 * ResponseEntity<TeamResource> removeTeam(@PathVariable int teamId) throws
	 * Exception { TeamResource resource = apiAdapter.removeTeam(teamId); if
	 * (resource == null) { return new
	 * ResponseEntity<TeamResource>(HttpStatus.NOT_FOUND); } return new
	 * ResponseEntity<TeamResource>(resource, HttpStatus.OK); }
	 */

}
