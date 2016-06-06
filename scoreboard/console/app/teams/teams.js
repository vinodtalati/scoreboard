'use strict';

angular.module('myApp.teams', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/teams', {
    templateUrl: 'teams/teams.html',
    controller: 'TeamsController'
  });
}])

.controller('TeamsController', ["$scope", "$rootScope", "ScorecardApiService", "liveFeedService", "$log", "_", 
	function($scope, $rootScope, ScorecardApiService, liveFeedService,$log, _) {
		$scope.items = [];

		

	    
		var reloadGroupData = function () {
			ScorecardApiService.getItems('http://localhost:8080/scorecard/api/v1/team').success(function (response) {
		        angular.copy( response, $scope.items )
		        $log.log("Got teams: " + response);
		    });
		};
		
		var init = function () {
			reloadGroupData();
			var data1 = [
						    {
						      "firstName": "Cox",
						      "lastName": "Carney",
						      "company": "Enormo",
						      "gender": "male"
						    },
						    {
						      "firstName": "Lorraine",
						      "lastName": "Wise",
						      "company": "Comveyer",
						      "gender": "female"
						    }
		    			];
		    var teamToUpdate = _.find( data1, function ( team ) {
				return team.firstName === "Cox";
			} );
			if ( teamToUpdate !== undefined ) {
				console.log("got it");
			}


		};

		init();

		$scope.gridOptions = {
			enableSorting : true,
			columnDefs: [
		          { name:'Team Id', field: 'id', cellTemplate : '<div>' + '<a href="#/teaminfo?teamid={{row.entity.id}}">{{row.entity.id}}</a>' + '</div>' },
		          { name:'Team Name', field: 'name' },
		          { name:'City', field: 'city'},
		          { name:'Abbr', field: 'abbreviation'},
		          { name:'Wins', field: 'wins'},
		          { name:'Losses', field: 'losses'},
		          { name:'Ties', field: 'ties'}
	        ],
			data: $scope.items
		};

		$scope.gridOptions.onRegisterApi = function ( gridApi ) {
			$scope.gridApi = gridApi;
		};

		var addTeamEventHandler = $rootScope.$on('ADD_TEAM', function (event, data) {
			console.log("Got Message in Controller. ADD_TEAM handler." + " Data: " + data.name );
			//add to grid
			$scope.items.push(data);
    		//console.log("items size: " + $scope.items.size + " items:" + $scope.items);
    		$scope.gridApi.core.refresh();
		});

		var updateTeamEventHandler = $rootScope.$on('UPDATE_TEAM', function (event, data) {
			console.log("Got Message in Controller. UPDATE_TEAM handler:" + "Data: " + data.name );

			//find item in scope.items modify and refresh grid
			var teamToUpdate = _.find( $scope.items, function ( team ) {
				return team.id === data.id;
			} );

			if ( teamToUpdate !== undefined ) {
				angular.copy( data, teamToUpdate );
			}
    		$scope.gridApi.core.refresh();
		});


		// Destrpy event handlers
		$scope.$on( '$destroy', function () {
			addTeamEventHandler();
			updateTeamEventHandler();
		} );

		/*var updateGroupEventHandler = $rootScope.$on( 'groupUpdated', function ( event, updatedGroup ) {
			var groupToUpdate = _.find( $scope.items, function ( group ) {
				return group.id === updatedGroup.id;
			} );

			if ( groupToUpdate !== undefined ) {
				angular.copy( updatedGroup, groupToUpdate );
				refreshGrid();
			}
		} );
		
		var updateGroup = function(){
			if ( refreshList ) {
				var groups = clientGroupService.getGroups();
				angular.copy( groups, $scope.items );
				refreshGrid();
				refreshList = false;
			}
		};

		var updateGroupEventHandler = $rootScope.$on( 'groupUpdated', function ( event, updatedGroup ) {
			var groupToUpdate = _.find( $scope.items, function ( group ) {
				return group.id === updatedGroup.id;
			} );

			if ( groupToUpdate !== undefined ) {
				angular.copy( updatedGroup, groupToUpdate );
				refreshGrid();
			}
		} );

		var groupsListUpdateHandler = $rootScope.$on( 'GroupsListUpdateEvent', function () {
			updateGroup();
		} );

		var onFilter = function () {
			$scope.gridOptions.data = filterData();
		};
		
		var refreshGrid = function () {
			onFilter();
			$scope.gridApi.core.notifyDataChange( uiGridConstants.dataChange.ALL );
		};

		// Destrpy event handlers
		$scope.$on( '$destroy', function () {
			groupsListUpdateHandler();
			// requestGroupListFromServerHandler();
			addGroupEventHandler();
			updateGroupEventHandler();
			deleteGroupEventHandler();
			addClientEventHandler();
			deleteClientEventHandler();
		} );*/

	}
	]);