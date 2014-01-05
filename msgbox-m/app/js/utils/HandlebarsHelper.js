Ember.Handlebars.registerBoundHelper('breaklines', function(text) {
    text = Handlebars.Utils.escapeExpression(text);
    text = text.toString();
    text = text.replace(/(\r\n|\n|\r)/gm, '<br>');
    return new Handlebars.SafeString(text);
});

Ember.Handlebars.registerBoundHelper('duration', function(ms) {
	
});

Ember.Handlebars.registerBoundHelper('i18n', function(key) {
	return App.i18n.text(key);
});

Ember.Handlebars.registerBoundHelper('equals', function(a, b) {
	return a == b;
});

//Handlebars.registerHelper('each', function(context, options) {
//    var ret = "";
//
//    for(var i=0, j=context.length; i<j; i++) {
//    	if (options.data) {
//	        data = Handlebars.createFrame(options.data || {});
//	        data.index = i;
//	    }
//        ret = ret + options.fn(context[i], {data: data});
//    }
//
//    return ret;
//});