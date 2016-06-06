'use strict';

var underscore = angular.module('underscore', []);
underscore.factory('_', ['$window', function($window) {
  return $window._; // assumes underscore has already been loaded on the page
}]);


// Declare app level module which depends on views, and components
angular.module('myApp', [
  'ngRoute',
  'myApp.services',
  'myApp.teams',
  'myApp.teaminfo',
  'myApp.games',
  'ui.grid',
  'myApp.version',
  'underscore'
]).
config(['$routeProvider', function($routeProvider) {
  $routeProvider.otherwise({redirectTo: '/teams'});
}]);

