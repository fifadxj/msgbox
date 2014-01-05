App.AlertHandler = Ember.Object.create({
    showDialog: false,
    message:'',
    okButtonTitle: '',
    secondButton: false,
    secondButtonTitle:'',
    customOkAction: null,
    customCancelAction:null,
    spacedClass: '',
    alertStyle: '',
    lightBoxStyle:'',
    editPersonaliaztion: false,
    _showAlert: function(){
        window.scrollTo(0,0);
        var topValue = (Math.round($(window).height()/2))-90;//90 half of avg alert plus buffer
        var lightBoxStyleValues = "top:" + topValue + "px;";
        var biggerHeight = ($('body').height() > $(window).height() ? $('body').height() : $(window).height());
        this.set('alertStyle', "height:" + biggerHeight + "px;");
        this.set('lightBoxStyle',lightBoxStyleValues);
        this.set('showDialog', true);
        $('a,li, input, dd, select').attr('disabled', 'disabled');
    },
    hideAlert: function(){
        this.set('showDialog', false);
        $('a,li, input, dd, select').removeAttr('disabled');
        return false;
    },
    okAction: function(){
        this.hideAlert();
        if(!Ember.isNone(this.customOkAction)) {
            this.customOkAction();
        }
        if(this.editPersonaliaztion){
            BoxUtils.deviceHelper.scrollToId('saveButton');
            this.editPersonaliaztion = false;
        }   
        return false;
    },
    cancelAction:function() {
        this.hideAlert();
        if(!Ember.isNone(this.customCancelAction)) {
            this.customCancelAction();
        }
        return false;
    },
    stopBubbLing: function(){
        return false;
    },
    
    
    showAlertDialog: function(_message, _okAction, _okButtonTitle, _secondButtonTitle, _secondButtonAction) {
        this.set('message', _message);
        this.customOkAction = _okAction;
        this.customCancelAction = _secondButtonAction;

        this.set('okButtonTitle', BoxUtils.isEmptyString(_okButtonTitle) ? "OK" : _okButtonTitle);
        if(!BoxUtils.isEmptyString(_secondButtonTitle)) {
            this.set('secondButton', true);
            this.set('spacedClass', 'spaced');
            this.set('secondButtonTitle', _secondButtonTitle);
        }
        else {
            this.set('secondButton', false);
            this.set('spacedClass', '');
            this.set('secondButtonTitle', '');
        }
        this._showAlert();
    },
    showError: function(errorMessage) {
        this.showAlertDialog(errorMessage);
    },
    showAlert: function(alertMessage, okAction, okButtonTitle, secondButtonTitle) {
        if(! (alertMessage instanceof Object)) {
            this.showAlertDialog(alertMessage, okAction, okButtonTitle, secondButtonTitle);
        }else{
            this.showAlertDialog(alertMessage.message, alertMessage.okAction, alertMessage.okMessage, alertMessage.cancelMessage,alertMessage.cancelAction);
        }
    },
    isShowingAlert: function(){
        return this.get('showDialog');
    }
});