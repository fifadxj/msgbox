App.securityService = App.BaseService.extend({
	login : function(userName, pswd) {
		var self = this; 
		
		var t_postValue = {
			header : this.getRequestHeader(),
			username : userName.trim(),
			password : pswd
		};
		
		var deferred = $.Deferred();
		ws.login(t_postValue).done(function(response) {
			self._processLogin(response, deferred);
			
		});
		
		return deferred.promise();

	},
	_processLogin :  function(response, deferred) {
		if (ws.isSuccessResponse(response)) {
			deferred.resolve(response.user);
		} else {
			deferred.reject(response.error);
		}
		
	},
	logout : function(success, fail){
		var self = this;

		var t_postValue = {
			header : this.getRequestHeader()
		};
		
        ws.logout(t_postValue).done(function(response) {
        	if (ws.isSuccessResponse(response)) {
        		success(response.duration);
        	} else {
        		fail(response.error);
        	}
        });
    }
}).create();