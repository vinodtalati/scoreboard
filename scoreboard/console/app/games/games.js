'use strict';

angular.module('myApp.games', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/games', {
    templateUrl: 'games/games.html',
    controller: 'GamesController'
  });
}])

.controller('GamesController', ["$scope", "$rootScope", "ScorecardApiService", "$log", 
	function($scope, $rootScope, ScorecardApiService,$log) {
		$scope.items = [];

		var reloadGroupData = function () {
			ScorecardApiService.getItems('http://localhost:8080/scorecard/api/v1/game').success(function (response) {
		        angular.copy( response, $scope.items )
		        $log.log("Got Games: " + response);
		    });
		};
		
		var init = function () {
			reloadGroupData();
		};

		init();

		$scope.gridOptions = {
			enableSorting : true,
			data: $scope.items
		};


	}
]);