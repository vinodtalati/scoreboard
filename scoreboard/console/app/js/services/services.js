'use strict';
var servicesModule = angular.module('myApp.services', []);
servicesModule.factory('ScorecardApiService', function($http) {

    var scoreCardAPI = {};

    scoreCardAPI.getItems = function(url) {
    	console.log("In API service: url: " + url);
      	return $http.get(url, {
							// query string like { userId: user.id } -> ?userId=value
							//params : p,
							headers : {
								'xxxxx' : 'xxxxx'
							}
						} );
    }

    return scoreCardAPI;
  });

servicesModule.factory("liveFeedService", function($q, $timeout, $rootScope) {
    
    var service = {}, listener = $q.defer(), socket = {
      client: null,
      stomp: null
    }, messageIds = [];
    
    service.RECONNECT_TIMEOUT = 30000;
    service.SOCKET_URL = "/scorecard/uievents";
    service.CHAT_TOPIC = "/events/allui";
    service.CHAT_BROKER = "/app/chat";
    
    service.receive = function() {
      return listener.promise;
    };
    
    service.send = function(message) {
      var id = Math.floor(Math.random() * 1000000);
      socket.stomp.send(service.CHAT_BROKER, {
        priority: 9
      }, JSON.stringify({
        message: message,
        id: id
      }));
      messageIds.push(id);
    };
    
    var reconnect = function() {
      $timeout(function() {
        initialize();
      }, this.RECONNECT_TIMEOUT);
    };
    
    var getMessage = function(data) {
      var uiEvent = JSON.parse(data);
      console.log("Got Message in Service. Type: " +uiEvent.type + " data:" + uiEvent.data + " name:" + uiEvent.data.name);
      $rootScope.$emit(uiEvent.type, uiEvent.data);
    };
    
    var startListener = function() {
      socket.stomp.subscribe(service.CHAT_TOPIC, function(data) {
        listener.notify(getMessage(data.body));
      });
    };
    
    var initialize = function() {
      socket.client = new SockJS(service.SOCKET_URL);
      socket.stomp = Stomp.over(socket.client);
      socket.stomp.connect({}, startListener);
      socket.stomp.onclose = reconnect;
    };
    
    initialize();
    return service;
  });