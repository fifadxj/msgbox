var App = Ember.Application.create({

});

App.ready = function() {
	App.initGlobalContext();
	
	if (!String.prototype.format && !String.prototype.f) {
		String.prototype.format = String.prototype.f = function() {
		    var s = this,
		        i = arguments.length;

		    while (i--) {
		        s = s.replace(new RegExp('\\{' + i + '\\}', 'gm'), arguments[i]);
		    }
		    return s;
		};
	}
	else {
		alert("String.prototype.format already defined");
	}
	
}

App.initGlobalContext = function() {
	App.globalContext = {
		data: Ember.Object.create({
			user: null,
			productList: null,
			language: 'en',
			nonce: null,
			
			messageList: null
		}),
		model: Ember.Object.create({
			user: null,
			isLoggedIn: false
		})
	};

	App.destroyGlobalContext = function() {
		App.initGlobalContext();
	};
};