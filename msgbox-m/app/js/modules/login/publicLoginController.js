App.PublicLoginController = App.BaseController.extend({
    moduleName: 'login',
    username:"",
	password:"",
    isLoginActive: false,

    prepareController : function(model){
    	this.setHeaderTitle('login');
        var self = this;
        this.resetInputs();
    },
    actions: {
    	submitLogin: function() {
	        var usernameVal, passwordVal;
	        usernameVal = this.get('username');
	        passwordVal = this.get('password');
	
	        try {
	            this.verifyInputs(usernameVal, passwordVal);
	        } catch(e) {
	            this._clearInput();
	            App.AlertHandler.showError(e.message);
	            return;
	        }
	
	        this.showLoading();
	

            var self = this;
            
            var success = function(user){
    			App.globalContext.data.set('user', user);
    			App.globalContext.model.set('user', user);
    			App.globalContext.model.set('isLoggedIn', true);
    			
    			self.hideLoading();
    			self._clearInput();
    			self.transitionToRoute('message.list');
            }
            var fail = function(e) {
            	self.hideLoading();
            	self._clearInput();
            	self.showError(e);
            }
            
            App.securityService.login(usernameVal, passwordVal).then(success, fail);
	    },
	    
	    switchLanguage: function(lang) {
	    	App.i18n.switchLanguage(lang);
	    	this.setHeaderTitle('login');
	    	this.transitionToRoute('public.login');
	    }
    },
    
    resetInputs: function() {
        this.set('username', '');
        this.set('password', '');
    },
    verifyInputs : function(userNameVal, passwordVal){
        
        if (Ember.isEmpty(userNameVal) || Ember.isEmpty(passwordVal)) {
            throw {
            	code: 'ERR_SEC_USER_ID_PASSWORD_REQUIRED',
            	message: App.i18n.messages.error.ERR_SEC_USER_ID_PASSWORD_REQUIRED
            };
        }
    },
	_clearInput : function() {
        this.set('username', null);
        this.set('password', null);
	}
});

