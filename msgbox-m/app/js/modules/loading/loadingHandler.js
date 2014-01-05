App.LoadingHandler = Ember.Object.create({
    loading:false,
    topStyle: '',
    loadingStyle:'',
    
    showLoading: function (){
        // var fromTop = 150 + window.scrollY;
        var fromTop =  $(window).scrollTop()+(Math.round($(window).height()/2))-50;//50 half of loading

        this.set('topStyle',"top:"+fromTop+"px;");

        var biggerHeight = ($('body').height() > $(window).height() ? $('body').height() : $(window).height());

        var layerHeight = "height:"+biggerHeight+"px;";// we may change this one to window.innerHeight, if we change it here then we should do the same thing showAlert


        this.set('loadingStyle',layerHeight);

        this.set('loading', true);
        $('a,li, input, dd, select').attr('disabled', 'disabled');
    },
    hideLoading: function(){
        this.set('loading', false);
        $('a,li, input, dd, select').removeAttr('disabled'); 
        return false;
    },
    stopBubbLing: function(){
        return false;
    },
    isShowingLoading: function(){
        return this.get('loading');
    }
});