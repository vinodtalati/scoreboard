'use strict';

define(['services/services', 'angular', 'sock', 'stomp', 'services/dataService'],
	function (services) {
		services.service('uiEventService', ['$rootScope','$window', '$timeout', '$log', '$auth', '$state', '$modalStack', 'dataSource',
			function ($rootScope, $window, $timeout, $log, $auth, $state, $modalStack, dataSource) {
			
					var SOCKET_END_POINT = "/uievents";
					var STOMP_EVENTS_QUEUE = "/events/allui";
					var wsEndPoint = new EventsListener();
				//For mozzila, prevent logout on page unload
				var isUnloading = false;
				$window.addEventListener('beforeunload', function() {
					isUnloading = true;
			    });
			    var stats = {};
			    
			    var getFailoverServiceState = function () {
					dataSource.getItems( '/failover/service_state' ).then( function ( response ) {
						var newStatus = false;
						if ( response.data.failoverStatus.mode ) {
							if ( response.data.failoverStatus.mode === 'STANDBY' ) {
								newStatus = false;
							} else {
								newStatus = true;
							}
						}
						 if($rootScope.isActiveNode !== newStatus && $state.current.name !== 'login' && $state.current.name !== 'login.view'){
							 wsEndPoint.cancelHbExpiryHandler();
						     wsEndPoint.disconnect();
							 $auth.invalidateTokens();
							 $state.go('login',{returnState: $state.current.name,errors:["static.login.FAILOVER_MODE_CHANGED"]});
						 }
					} );
				};
				
				$rootScope.$on( 'FAILOVER_SERVICE_STATE', function () {
				    	getFailoverServiceState();
					} );
			    
				function EventsListener() {
					this.socket = null,
					this.stompClient = null,
					this.isConnected = false,
					this.hbExpiryHandler = null,

					this.cancelHbExpiryHandler = function() {
						if (this.hbExpiryHandler !== null &&
							this.hbExpiryHandler !== undefined) {
							$timeout.cancel(this.hbExpiryHandler);
						}
					},
					
					this.resetHbExpiry = function() {
						this.cancelHbExpiryHandler();
						this.hbExpiryHandler =
							$timeout(function() {
								wsEndPoint.connectionLost(true);
							}, 7000);
					},
					
					this.connectionLost = function(hbExpired) {
						if (hbExpired === true) {
							$log.log("No heartbeat received. Disconnecting.");
							this.disconnect();
						} else {
							$log.log("Connection Lost. Disconnecting.");
							this.cancelHbExpiryHandler();
						}
						if(isUnloading){
							return;
						}
						this.isConnected = false;
		
				  		$modalStack.dismissAll('connection-lost');
				  		if($state.current.name !== 'login' && $state.current.name !== 'login.view')
						{
				  			wsEndPoint.cancelHbExpiryHandler();
					    	wsEndPoint.disconnect();
					  		$auth.invalidateTokens();
					  		$state.go('login',{returnState: $state.current.name,errors:["static.login.CONN_ERROR"]});
						}
		      	     	
					},

					this.disconnect = function() {
				    	if (this.isConnected) {
				    		$log.log("Disconnecting SockJS connection.");
				    		this.stompClient.disconnect(function() {
				    			wsEndPoint.isConnected = false;	
				    		});
				    	}
					}

				};

				
				var handleMessage = function(message) {
					var uiEvent = JSON.parse(message.body);
					if (!stats[uiEvent.type]) {
						stats[uiEvent.type] = 0;
					}
					stats[uiEvent.type]++;
					$rootScope.$emit(uiEvent.type, uiEvent.data);
					wsEndPoint.resetHbExpiry();
				};

				var stompConnectSuccessCallback = function() {
					$log.log("*** WS Connected ***");
					wsEndPoint.isConnected = true;
					wsEndPoint.stompClient.subscribe(STOMP_EVENTS_QUEUE, handleMessage);
				};
				
				var init = function() {
			    	if (!wsEndPoint.isConnected) {
						$log.log("*** Initing UiEventService ***");
						wsEndPoint.socket = new SockJS(SOCKET_END_POINT);
						wsEndPoint.stompClient = Stomp.over(wsEndPoint.socket);
						wsEndPoint.stompClient.connect({}, stompConnectSuccessCallback, stompConnectErrorCallback);
						wsEndPoint.socket.onheartbeat = function () {
							wsEndPoint.resetHbExpiry();
						};
			    	}
			    };

				var stompConnectErrorCallback = function(msg, args) {
					$log.log("*** WS Connection Error *** "+ args!==undefined ? args.code : "");
					//Reconnect in case of 1006
					if(args !== undefined && (args.code === 1006 || args.code === 2000)){
						wsEndPoint.cancelHbExpiryHandler();
				    	wsEndPoint.disconnect();
				    	$log.log("*** Reconnecting ***");
				    	init();
				    	return;
					}
					
					$log.log("*** WS Connection Error ***");
					wsEndPoint.connectionLost();
				};

			    

			    this.start = function() {
			    	init();
			    };

			    this.stop = function() {
			    	wsEndPoint.cancelHbExpiryHandler();
			    	wsEndPoint.disconnect();
			    };

		   	}
		]);
	}
);