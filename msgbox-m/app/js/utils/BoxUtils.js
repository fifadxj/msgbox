var BoxUtils = {
	browserInfo : null
};

BoxUtils.isNullOrUndefinedList = function(variable) {
	return (Ember.isNone(variable) || variable.length === 0);
};

BoxUtils.isEmptyString = function(variable) {
	return (Ember.isNone(variable) || $.trim(variable).length === 0);
};

BoxUtils.isEmpty = function(obj) {
	for ( var prop in obj) {
		if (obj.hasOwnProperty(prop)) {
			return false;
		}
	}
	return true;
};

BoxUtils.isNotEmptyorZeroString = function(variable) {
	if(variable === 0){
		return true;
	}else{
		if(Ember.isEmpty(variable)){
			return false;
		}
		return true;
	}
		
};

BoxUtils.isEmptyOrZero = function(variable) {
	if(Ember.isEmpty(variable) || !BoxUtils.isNumber(variable) || Number(variable) === 0){
		return true;
	}
	return false;
};

BoxUtils.isNumber = function(num){
	var _num = parseFloat(num);
    return !isNaN(_num) && isFinite(_num);
};

BoxUtils.getBrowserInfo = function() {
	if (null == BoxUtils.browserInfo) {
		var t_nav = window.navigator;
		BoxUtils.browserInfo = {};
		BoxUtils.browserInfo.appCodeName = t_nav.appCodeName;
		BoxUtils.browserInfo.appName = t_nav.appName;
		BoxUtils.browserInfo.appVersion = t_nav.appVersion;
		BoxUtils.browserInfo.buildID = t_nav.buildID;
		BoxUtils.browserInfo.cookieEnabled = t_nav.cookieEnabled;
		BoxUtils.browserInfo.language = t_nav.language ? t_nav.language : t_nav.browserLanguage;
		BoxUtils.browserInfo.userAgent = t_nav.userAgent;
		BoxUtils.browserInfo.platform = t_nav.platform;
	}
	return BoxUtils.browserInfo;
};

BoxUtils.isFirefox = function() {
	var t_userAgent = BoxUtils.getBrowserInfo().userAgent;
	if (t_userAgent.indexOf("Firefox") >= 0) {
		return true;
	}
	return false;
};

BoxUtils.isOpera = function() {
	var t_userAgent = BoxUtils.getBrowserInfo().userAgent;
	if (t_userAgent.indexOf("Opera") >= 0) {
		return true;
	}
	return false;
};

BoxUtils.isIOSDevice = function(){
	var t_userAgent = BoxUtils.getBrowserInfo().userAgent.toLowerCase();
	
	return  (t_userAgent.search('ipad')>-1 && t_userAgent.search('webkit')) || t_userAgent.search('iphone')>-1 ? true : false;
};

BoxUtils.isIphone = function(){
	var t_userAgent = BoxUtils.getBrowserInfo().userAgent.toLowerCase();
	return  t_userAgent.search('iphone')>-1 ? true : false;
};

BoxUtils.isWinPhone = function(){
	var t_userAgent = BoxUtils.getBrowserInfo().userAgent;
	if (t_userAgent.indexOf("Windows") >= 0) {
		return true;
	}else{
		return false;
	}
};

BoxUtils.isSymbian = function(){
	var t_userAgent = BoxUtils.getBrowserInfo().userAgent;
	if (t_userAgent.indexOf("Symbian") >= 0) {
		return true;
	}else{
		return false;
	}
};

BoxUtils.isAndroidDevice = function(){
	var t_userAgent = BoxUtils.getBrowserInfo().userAgent;
	if (t_userAgent.indexOf("Android") >= 0) {
		return true;
	}else{
		return false;
	}
};

BoxUtils.deviceHelper = (function() {
	var obj = null;
	return {
		originalHeight: -1,
        scrollToId : function(id) {
            if (BoxUtils.isBB5()) {
                if ((obj = document.getElementById(id)) && obj != null) {
                    window.scrollTo(0, obj.offsetTop);
                }
            } else {
				if ($("#" + id).offset()) {
					$('html, body').animate({
                        scrollTop : $("#" + id).offset().top
                    }, 1000);
				}
            }
		}
	};
})();

BoxUtils.setMinHeight = function(){
    var subWrapper = $('.sub-wrapper');
    var mainNav = $('.main-nav');

    var viewportHeight = window.innerHeight;

    subWrapper.css('min-height', viewportHeight);
    mainNav.css('min-height', viewportHeight);
};

BoxUtils.resetMinHeight = function(){
    var subWrapper = $('.sub-wrapper');
    var mainNav = $('.main-nav');

    var viewportHeight = window.innerHeight;

    if(BoxUtils.isAndroidOS2_3()) {
        $('[placeholder]').focus(function() {
            subWrapper.css('min-height', viewportHeight + 200);
            mainNav.css('min-height', viewportHeight + 200);
        }).blur(function() {
            subWrapper.css('min-height', viewportHeight);
            mainNav.css('min-height', viewportHeight);
        });
    }
};

BoxUtils.clearInputText = function (input) {

    input.addClass('plain-placeholder');
    if(input.attr('type') !== 'text'){
       input.attr('tempHolder', input.attr('type'));
       input.attr('type', 'text');
    }
    input.val(input.attr('placeholder'));
};

BoxUtils.replaceBRText = function(text){
    if(Ember.isEmpty(text)){
        return [];
    }else{
        text = String(text);
        text = text.replace(/(<\s*br\s*>|<\s*BR\s*>|<\s*br\s*\/\s*>|<\s*BR\s*\/\s*>)/gm, '<br>');
        return text.split("<br>");
    }
};
BoxUtils.replaceText = function(text){
	if(Ember.isEmpty(text)){
		return [];
	}else{
		text = String(text);
		text = text.replace(/(<\s*br\s*>|<\s*BR\s*>|<\s*br\s*\/\s*>|<\s*BR\s*\/\s*>)/gm, ' ');
		return text;
	}
};