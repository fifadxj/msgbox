ws = {
	getServiceUrl: function(serviceId){
		return "{0}?_={1}".f(BoxConstants.services[serviceId],  new Date().getTime());
	},
	sendRequest: function(serviceName, payload){
		var settings = {
		    async: true,
		    type: 'post',
			dataType: 'json',
			//cache: false,
			contentType: 'application/json',
			timeout: 10 * 1000,
			data: JSON.stringify(payload),
			
			error: function(xhr, textStatus, errorThrown) {
				console.error("ajax call error: [status: %s] [error: %s]", textStatus, errorThrown);
				
				if('timeout' === textStatus){
					App.LoadingHandler.hideLoading();
					App.AlertHandler.showError(App.i18n.messages.error.CONNECTION_TIMEOUT);
				}else {
					App.LoadingHandler.hideLoading();
					App.AlertHandler.showError(App.i18n.messages.error.UNKNOWN_ERROR);
				}
			},
			success: function(response, textStatus, jqXHR){
		
			},
			complete:function(jqXHR, textStatus){
			}
		};

	    if(/*window.navigator.onLine*/ true) {
	        var url = ws.getServiceUrl(serviceName);
	    	return $.ajax(url, settings);
	    } else {
	    	App.LoadingHandler.hideLoading();
			App.AlertHandler.showError(App.i18n.messages.error.NO_INTERNET_ACCESS);
	        
	        var deferred = $.Deferred();
	        deferred.reject();
	        
	        return deffered.promise();
	    }
	},
	isSuccessResponse: function(response) {
		return Ember.isNone(response.error) ? true : false;
	},
	login: function(payload){
		return ws.sendRequest('login', payload);
	},
	logout: function(payload){
		return ws.sendRequest('logout', payload);
	},
	messageList: function(payload){
		return ws.sendRequest('messageList', payload);
	},
	messageDetails: function(payload){
		return ws.sendRequest('messageDetails', payload);
	}
};
