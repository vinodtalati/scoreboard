define( [
	'services/services'
], function ( services ) {
	services.service( 'dataSource', [
			'$http', '$q', 'app.config', '$upload', function ( $http, $q, config, $upload ) {
				function webRoot ( url ) {
					return config.basePath + url;
				}
				return {
					reject : function ( errMsg ) {
						return $q.reject( errMsg );
					},
					getItems : function ( url, p ) {
						return $http.get( webRoot( url ), {
							// query string like { userId: user.id } -> ?userId=value
							params : p,
							headers : {
								'xxxxx' : 'xxxxx'
							}
						} );
					},
					postItem : function ( url, p ) {
						return $http.post( webRoot( url ), {
							// query string like { userId: user.id } -> ?userId=value
							params : p,
							headers : {
								'Content-Type' : 'application/json'
							}
						} );
					},
					addItem : function ( url, item ) {
						return $http( {
							url : webRoot( url ),
							method : 'POST',
							data : item,
							headers : {
								'xxxxx' : 'xxxxx'
							}
						} );
					},
					deleteItem : function ( url, item ) {
						return $http( {
							url : webRoot( url ) + ( item === undefined ? '' : '/' + item.id ),
							method : 'DELETE',
							headers : {
								'xxxxx' : 'xxxxx'
							}
						} );
					},
					// when item does not have "Id" field
					// we can pass name etc. as id here
					deleteById : function ( url, id ) {
						return $http( {
							url : webRoot( url ) + '/' + id,
							method : 'DELETE',
							headers : {
								'xxxxx' : 'xxxxx'
							}
						} );
					},
					// delete selected items
					deleteChecked : function ( url, items ) {
						return $http( {
							url : webRoot( url ),
							method : 'DELETE',
							data : items,
							headers : {
								'Content-Type' : 'application/json'
							}
						} );
					},
					updateItem : function ( url, item ) {
						return $http( {
							url : webRoot( url ),
							method : 'PUT',
							data : item,
							headers : {
								'Content-Type' : 'application/json'
							}
						} );
					},
					// pass list to process all in one go
					processChecked : function ( url, items ) {
						return $http( {
							url : webRoot( url ),
							method : 'POST',
							data : items,
							headers : {
								'Content-Type' : 'application/json'
							}
						} );
					},
					// file upload
					uploadFileFD : function ( url, formData ) {
						return $http( {
							url : webRoot( url ),
							method : 'POST',
							data : formData,
							headers : {
								'Content-Type' : undefined
							},
							transformRequest : angular.identity
						} );
					},
					// file upload
					uploadFile : function ( url, file, p ) {
						return $upload.upload( {
							url : webRoot( url ),
							method : 'POST',
							file : file,
							fields : p
						} );
					},
					invokeOperation : function ( url, params, item ) {
						return $http( {
							url : webRoot( url ),
							method : 'POST',
							params : params,
							data : item,
							headers : {
								'xxxxx' : 'xxxxx'
							}
						} );
					},
					downloadOperation : function ( url, item ) {
						return $http( {
							url : webRoot( url ),
							method : 'POST',
							data : item,
							headers : {
								'xxxxx' : 'xxxxx'
							}
						} ).success( function ( data ) {
							if ( navigator.userAgent.indexOf( 'MSIE' ) !== -1 || navigator.appVersion.indexOf( 'Trident/' ) > -1 ) {
								if ( window.File && window.FileReader && window.FileList && window.Blob ) {
									var blobObject = new Blob( [
										data
									] );

									window.navigator.msSaveBlob( blobObject, 'clientInfoCSV.csv' );
								} else {
									alert( 'Use IE 11 or Latest version.' );
								}
								return; // MSIE
							}
							var file = new Blob( [
								data
							], {
								type : 'application/csv'
							} );

							var fileURL = URL.createObjectURL( file );
							var a = document.createElement( 'a' );
							a.href = fileURL;
							a.target = '_blank';
							a.download = 'clientInfoCSV.csv';
							document.body.appendChild( a );
							a.click();
						} ).error( function () {

						} );
					}

				};
			}
	] );
} );