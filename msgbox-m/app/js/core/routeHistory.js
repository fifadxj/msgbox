App.RouteHistory = Ember.Object.create({
    history: [],
    currentRoute: function(){
        return this.history[this.history.length-1];
    },
    pushRoute: function(routeName){
        var prevRoute = this.currentRoute();
        if(prevRoute !== routeName){
            this.history.push(routeName);
        }
    },
    popRoute: function(){
        return this.history.pop();
    },
    flush: function(){
        history = [];
    }
});