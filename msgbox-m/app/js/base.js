App.BaseRoute = Ember.Route.extend({
    renderTemplate : function(controller, model) {
    	this._super(controller, model);

        window.scrollTo(0, 0);

        this.render('header', {
            into : 'application',
            outlet : 'header'
        });
    },
    setupController: function(controller, model) {
    	this._super(controller, model);    
    	
        var headerController = this.controllerFor('header');
        headerController.set('content', App.globalContext.model);

    	if (controller.msgbox) { // if it is BaseController
    		controller.setShowBackButton(false);
    		controller.prepareController(model);
    		controller.set('content', App.globalContext.model);
    	}
    }
});

App.BaseView = Ember.View.extend({
	i18n: function() {
		return App.i18n.messages;
	}.property('App.i18n.language'),
    willInsertElement : function(){

    },
    didInsertElement : function() {

    },
    willDestroyElement : function(){

    },
    willRerender : function(){

    },
    becameVisible : function(){

    },
    becameHidden : function(){

    }
});

App.BaseController = Ember.ObjectController.extend({
	msgbox: true, // a flag indicating it is BaseController
	needs: ['header'],
	
	i18n: function() {
		return App.i18n.messages;
	}.property('App.i18n.language'),
	
	init: function() {
		this._super();
	},
    prepareController : function(model){
    },
    setHeaderTitle: function(key) {
        var headerController = this.get('controllers.header');
        headerController.set('navTitle', App.i18n.messages.title[key]);
    },
    setShowBackButton: function(isShown) {
        var headerController = this.get('controllers.header');
        headerController.set('showBack', isShown);
    },
    showError: function(e){
    	if('ERR_USER_NOT_LOGIN' === e.code){
    		var redirected = false;
    		App.destroyGlobalContext();
        	
        	App.AlertHandler.showAlert(e.message, function() {
        		App.AlertHandler.hideAlert();
        		self.transitionTo('public.login');
        		redirected = true;
        	});
        	setTimeout(function() {
        		if (! redirected) {
            		App.AlertHandler.hideAlert();
            		self.transitionTo('public.login');
        		}
        	}, 5000);
        }

        App.AlertHandler.showError(e.message);
    },
    showLoading:function(){
    	App.LoadingHandler.showLoading();
    },
    hideLoading:function(){
    	App.LoadingHandler.hideLoading();
    },
    getFail: function() {
    	var self = this;
    	return function(e){
	    	self.hideLoading();
	        self.showError(e);
	    };
    }
});

App.BaseService = Ember.ObjectController.extend({
	getRequestHeader: function() {
		var requestHeader = {
		    language: App.globalContext.data.language,
		    nonce: App.globalContext.data.nonce
		}
		return requestHeader;
	}
});