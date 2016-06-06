'use strict';

angular.module('myApp.teaminfo', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/teaminfo', {
    templateUrl: 'teaminfo/teaminfo.html',
    controller: 'TeaminfoController'
  });
}])

.controller('TeaminfoController', ["$scope", "$rootScope", "ScorecardApiService", "$routeParams","$log", 
	function($scope, $rootScope, ScorecardApiService, $routeParams, $log) {
		$scope.items = [];

		var reloadGroupData = function () {
			/*ScorecardApiService.getItems('http://localhost:8080/scorecard/api/v1/game').success(function (response) {
		        angular.copy( response, $scope.items )
		        $log.log("Got Games: " + response);
		    });*/
		};
		
		var init = function () {
			reloadGroupData();
			$log.log("got param: " + $routeParams.teamid);
		};

		init();

		$scope.gridOptions = {
			enableSorting : true,
			data: $scope.items
		};


	}
]);