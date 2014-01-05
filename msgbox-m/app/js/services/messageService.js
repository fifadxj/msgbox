App.messageService = App.BaseService.extend({
	getMessageList: function(success, fail){
		var self = this;
	
		var t_postValue = {
			header : this.getRequestHeader(),
		};

		ws.messageList(t_postValue).done(function(response) {
			if (ws.isSuccessResponse(response)) {
				success(response.messages);
			} else {
				fail(response.error);
			}
		});
	},
	
	getMessageDetails: function(id, success, fail) {
		var self = this;

		var t_postValue = {
			header : this.getRequestHeader(),
			id: id
		};
		
		ws.messageDetails(t_postValue).done(function(response) {
			if (ws.isSuccessResponse(response)) {
				success(response.message);
			} else {
				fail(response.error);
			}
		})
	}
}).create();