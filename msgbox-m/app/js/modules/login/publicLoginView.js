App.PublicLoginView = App.BaseView.extend({
    templateName : 'publicLogin',
    didInsertElement: function(){
        BoxUtils.setMinHeight();
    }
});