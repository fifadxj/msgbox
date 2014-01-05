App.Router.map(function() {
	this.resource('public', { path: '/public' }, function() {
		this.route('login');
		this.route('language');
	});

	this.resource('security', { path: '/security' }, function() {
		this.route('logout');
		this.resource('message', {path: '/message'}, function() {
			this.route('list', {path: 'list'});
			this.route('view', {path: 'view/:id'});
		})
	});
});

App.IndexRoute = App.BaseRoute.extend({
	redirect : function() {
		this.transitionTo('public.login');
	}
});

App.PublicRoute = App.BaseRoute.extend({

});

App.SecurityRoute = App.BaseRoute.extend({
	activate : function() {
        var isLoggedIn = App.globalContext.model.isLoggedIn;
        if (! isLoggedIn) {
        	this.transitionTo('public.login');
        }
	}
});

App.PublicLoginRoute = App.BaseRoute.extend({
	renderTemplate : function(controller, model) {    
        this._super(controller, model);

//        var headerController = this.controllerFor('header');
//        headerController.set('navTitle', App.i18n.messages.title.login);

		this.render('publicLogin', {
			into : 'application',
			outlet : 'appbody'
		});
	}
});

App.SecurityLogoutRoute = App.BaseRoute.extend({
	redirect : function() {
		var self = this;
		App.LoadingHandler.showLoading();
		
		var success = function(duration) {
        	var redirected = false;
        	App.LoadingHandler.hideLoading();
        	App.destroyGlobalContext();
        	
        	var formatDuration = function(ms) {
        		var sec = Math.floor(ms / 1000);
        		var hour = Math.floor(sec / 3600);
        		sec = sec - (hour * 3600);
        		var min = Math.floor(sec / 60);
        		sec = sec - min * 60;
        		
        		return App.i18n.messages.logout.duration.f(hour, min, sec);
        	}
        	
        	App.AlertHandler.showAlert(App.i18n.messages.logout.summaryText + formatDuration(duration), function() {
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
        };
        
        var fail = function(e) {
        	self.hideLoading();
	        self.showError(e);
        };
        
		App.securityService.logout(success, fail);	
	}
});


App.MessageListRoute = App.BaseRoute.extend({
	renderTemplate: function(controller, model) {
		this._super(controller, model);
        
        this.render('messageList', {
            into : 'application',
            outlet : 'appbody'
        });
	}
});

App.MessageViewRoute = App.BaseRoute.extend({
	renderTemplate : function(controller, model){
        this._super(controller, model);

        this.render('messageView', {
            into : 'application',
            outlet : 'appbody'
        });
	},
	serialize: function(model) {
		return {
			id : model.id
		};
	},
    model: function(param) {
		return Ember.Object.create({
			id : param.id
		});
	}
});
