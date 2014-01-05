App.i18n = Em.Object.extend({
	language: null,
    languages: function() {
    	var langs = [
    		{
    			index: 0,
    			key: 'en',
    			text: 'English',
    			current: (this.language == 'en')
    		},
    		{
    			index: 1,
    			key: 'zh',
    			text: '中文',
    			current: (this.language == 'zh')
    		}
    	];
    	
    	return langs;
    }.property('language'),
    init: function() {
    	this.switchLanguage('zh');
    },
    switchLanguage: function(lang) {
    	this.set('language', lang);
    	var messages = $.extend(true, {}, this.rawMessages);
    	for (var i in messages) {
    		var module = messages[i];
    		for (var key in module) {
    			var texts = module[key];
    			
    			if (typeof texts != 'string') {
    				var text = texts[this.getCurrentIndex()];
    				text = text ? text : texts[0];
    				module[key] = text;
    			}
    		}
    	}
    	this.set('messages', messages);
    },
    getCurrentIndex: function() {
    	var langs = this.get('languages');
    	for (var i = 0; i < langs.length; i++) {
    		if (langs[i].current) {
    			return langs[i].index;
    		}
    	}
    	return 0;
    },
    getTranslations: function() {
        return this.translations[this.get('language')];
    },
	messages: null,
	rawMessages: {
		error: {
			UNKNOWN_ERROR: ['Unknown error', '我挂掉了。。'],
			ERR_SEC_USER_ID_PASSWORD_REQUIRED: ['Username or password needed', '用户名和密码不能为空'],
			CONNECTION_TIMEOUT: ['Connection timeout', '连接超时'],
			NO_INTERNET_ACCESS: ['There is no or poor internet connection.', '没有网络连接']
		},
		title: {
			login: ['login', '登录'],
			messageList: ['Message List', '消息列表'],
			messageDetails: ['Message Details', '消息详情']
		},
		common: {
			loadingText: ['Loading', '加载中'],
	        yes: ['Yes', '是'],
	        no: ['No', '否']
		},
		login: {
	        username : ['Username', '用户名'],
	        password : ['Password', '密码'],
	        loginBtn : ['Login', '登录'],
	    },
	    logout: {
	    	confirmText: ['Do you want to Logout now?', '你确认要注销吗？'],
	    	confirmBtn: ['Logout', '注销'],
	    	cancelBtn: ['Cancel', '取消'],
	    	duration: ['{0} hour, {1} minutes, {2} seconds', '{0}小时， {1}分， {2}秒'],
	    	summaryText: ['Logout successfully, session duration: ', '已注销， 本次登录时间: ']
	    },
		message: {
			content: ['Content', '内容'],
			category: ['Category', '类别'],
			rank: ['Rank', '评分'],
			source: ['Source', '来源'],
			topMessages: ['Top Messages', '置顶消息'],
			message: ['Messages', '消息'],
			title: ['Title', '标题']
		}
	}
}).create();


