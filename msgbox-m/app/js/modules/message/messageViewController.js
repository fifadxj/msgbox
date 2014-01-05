App.MessageViewController = App.BaseController.extend({
    moduleName: 'message',
    message: null,
	prepareController : function(model) {
		this.setHeaderTitle('messageDetails');
		this.setShowBackButton(true);

		var self = this;
	    this.showLoading();
	    
	    var success = function(message){
	    	self.hideLoading();
	        self.set('message', message);
	    };
	    
	    App.messageService.getMessageDetails(model.id, success, this.getFail());
	}
});