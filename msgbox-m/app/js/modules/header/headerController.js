App.HeaderController = App.BaseController.extend({
    actions: {
        goBack: function(){
            var prevRouteName = App.RouteHistory.popRoute();
            this.transitionToRoute(prevRouteName);
        },
        logout : function(){
        	var self = this;
    		App.AlertHandler.showAlert(
                App.i18n.messages.logout.confirmText,
                function(){
                	self.transitionToRoute('security.logout');
                },
                App.i18n.messages.logout.confirmBtn,
                App.i18n.messages.logout.cancelBtn
            );
    	}
    },

    showBack: false,
    navTitle: ''
});