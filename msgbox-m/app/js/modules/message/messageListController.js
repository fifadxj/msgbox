App.MessageListController = App.BaseController.extend({
    moduleName: 'message',
    messageList: [],
	prepareController : function(model) {
		this.setHeaderTitle('messageList');
	    var self = this;
	    this.showLoading();
	    
	    var success = function(messages){
	    	self.hideLoading();
	    	App.globalContext.data.set('messageList', messages)
	        self.set('messageList', messages);
	    };
	    
	    
	    App.messageService.getMessageList(success, this.getFail());
	},
	
	actions : {
		selectListItem : function(message) {
			App.RouteHistory.pushRoute('message.list');
			this.transitionToRoute('message.view', message);
		}
	}
});