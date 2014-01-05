var BrowserDetection = {
	whiteListMobileOS : {'iOS':'4.0','Android':'2.3.3','BlackBerry':'6.0','Symbian':'3.0','WinPhone':'7.8'},
	whiteListDesktopOS : ['Mac', 'Windows', 'Linux'],
	whiteListBrowser : ['Chrome','Safari','Firefox','Opera','WebKit'],
	init : function() {
		this.userAgent = window.navigator.userAgent;
		var _browserInfo = this.searchString(this._dataBrowser), 
			_osInfo = this.searchString(this._dataOS);

		this.browser = _browserInfo.info,
		this.browserVersion = _browserInfo.version,
		this.OS = _osInfo.info, 
		this.OSVersion = _osInfo.version;
		
		this._adjustOS();
	},
	searchString : function(data) {
		var _userAgent = this.userAgent, 
		_detectInfo = {
			info : 'Unknown',
			version : 'Unknown'
		};
		try {
			for ( var i = 0; i < data.length; i++) {
				var dataRegex = data[i].regex;
				var results = new RegExp(dataRegex).exec(_userAgent);
				if (results != null) {
					_detectInfo.info = data[i].subString,
					_detectInfo.version = this._getVersion(results[0]);
					break;
				}
			}
		} catch (e) {
		}

		return _detectInfo;
	},
	
	isExpectationBrowser : function(browserName, browserVersion){
		var _expect = false;
		if(this.browser.indexOf(browserName)>=0){
			_expect = true;
		}
		
		if(browserVersion && this.browserVersion.indexOf(browserVersion)<0){
			_expect = false;
		}
		
		return _expect;
	},
	isExpectationOS : function(OSName, OSVersion){
		var _expect = false;
		if(this.OS.indexOf(OSName)>=0){
			_expect = true;
		}
		
		if(OSVersion && this.OSVersion.indexOf(OSVersion)<0){
			_expect = false;
		}
		
		return _expect;
	},
	isCompatibleMobileBrowser : function() {
		var _supportOS = null,
			_osVersion = this.OSVersion;
		if(this.OS.indexOf('BlackBerry')>=0){
			_osVersion = parseFloat(_osVersion);
		}
		for(_supportOS in this.whiteListMobileOS){
			if(this.OS.indexOf(_supportOS)>=0){
				var _version = this.whiteListMobileOS[_supportOS];
				if(this.OS.indexOf('BlackBerry')>=0){
					_version = parseFloat(_version);
				}
				if(_osVersion>=_version){
					return true;
				}
			}
		}
		
		return false;
	},

	isDesktopBrowser : function() {
		return this._find(this.whiteListDesktopOS, this.OS);
	},

	isCompatibleDesktopBrowser : function() {
		return this._find(this.whiteListBrowser, this.browser);
	},
	_getVersion : function(versionInfo){
		var _version = 'Unknown';
		try{
			var _numberExp = /(\d+)\.(\d+)\.*(\d*)|(\d+)_(\d+)_*(\d*)/;
			var _results = new RegExp(_numberExp).exec(versionInfo);
			_version = _results[0].replace(/_/g, '.');
		}catch(e){
		}
		
		return _version;
	},
	_adjustOS : function(){
		try{
			this.OS = this.OS === 'Unknown' ? window.navigator.platform : this.OS;
			var _specialAndroidSymbol = ['Linux armv7l','Linux armv6l','Linux armv5l'];
			if(this._find(_specialAndroidSymbol, this.OS)){
				this.OS = 'Android';
			}
			
			var _userAgent = this.userAgent.toLowerCase(),
				_versionExp = /version\/(\d+)\.(\d+)\.*(\d*)/,
				_results = new RegExp(_versionExp).exec(_userAgent);
			
			if(this.OS.indexOf('BlackBerry')>=0 && _results != null){
				this.OSVersion = _results[0].replace('version\/', '');
			}else if(_results != null){
				this.browserVersion = _results[0].replace('version\/', '');
			}
					
		}catch(e){}
	},
	_find : function(container, key){
		try{
			for(var i=0,len=container.length; i<len; i++){
				if(key.indexOf(container[i])>=0){
					return true;
				}
			}
		}catch(e){}
		
		return false;
	}
};

BrowserDetection._dataBrowser = [ {
	regex : /(Firefox)\/(\d+)\.(\d+)/,
	subString : 'Firefox'
}, {
	regex : /Mobile.*(Firefox)\/(\d+)\.(\d+)/,
	subString : 'Firefox Mobile'
}, {
	regex : /(Firefox).*Tablet browser (\d+)\.(\d+)\.(\d+)/,
	subString : 'Firefox Tablet Browser'
}, {
	regex : /(Opera Tablet).*Version\/(\d+)\.(\d+)(?:\.(\d+))?/,
	subString : 'Opera Mobile'
}, {
	regex : /(Opera)\/.+Opera Mobi.+Version\/(\d+)\.(\d+)?/,
	subString : 'Opera Mobile'
}, {
	regex : /Opera Mobi/,
	subString : 'Opera Mobile'
}, {
	regex : /(Opera Mini)\/(\d+)\.(\d+)/,
	subString : 'Opera Mini'
}, {
	regex : /(Opera Mini)\/att\/(\d+)\.(\d+)/,
	subString : 'Opera Mini'
}, {
	regex : /(Opera)\/9.80.*Version\/(\d+)\.(\d+)(?:\.(\d+))?/,
	subString : 'Opera'
}, {
	regex : /(Opera)\/(\d+)\.(\d+)/,
	subString : 'Opera'
}, {
	regex : /(CrMo)\/(\d+)\.(\d+)\.(\d+)\.(\d+)/,
	subString : 'Chrome Mobile'
}, {
	regex : /(CriOS)\/(\d+)\.(\d+)\.(\d+)\.(\d+)/,
	subString : 'Chrome Mobile iOS'
}, {
	regex : /(Chrome)\/(\d+)\.(\d+)\.(\d+)\.(\d+) Mobile/,
	subString : 'Chrome Mobile'
}, {
	regex : /(Chromium)\/(\d+)\.(\d+)\.(\d+)/,
	subString : 'Chromium'
}, {
	regex : /(Chrome)\/(\d+)\.(\d+)/,
	subString : 'Chrome'
}, {
	regex : /(iPod).+Version\/(\d+)\.(\d+)\.(\d+)/,
	subString : 'Mobile Safari'
}, {
	regex : /(iPod).*Version\/(\d+)\.(\d+)/,
	subString : 'Mobile Safari'
}, {
	regex : /(iPhone).*Version\/(\d+)\.(\d+)\.(\d+)/,
	subString : 'Mobile Safari'
}, {
	regex : /(iPhone).*Version\/(\d+)\.(\d+)/,
	subString : 'Mobile Safari'
}, {
	regex : /(iPad).*Version\/(\d+)\.(\d+)\.(\d+)/,
	subString : 'Mobile Safari'
}, {
	regex : /(iPad).*Version\/(\d+)\.(\d+)/,
	subString : 'Mobile Safari'
}, {
	regex : /(iPod|iPhone|iPad);.*CPU.*OS (\d+)(?:_\d+)?_(\d+).*Mobile/,
	subString : 'Mobile Safari'
}, {
	regex : /(iPod|iPhone|iPad)/,
	subString : 'Mobile Safari'
}, {
	regex : /(Safari)\/(\d+)\.(\d+)\.(\d+)/,
	subString : 'Safari'
}, {
	regex : /(IEMobile)[ \/](\d+)\.(\d+)/,
	subString : 'IE Mobile'
}, {
	regex : /(MSIE) (\d+)\.(\d+).*XBLWP7/,
	subString : 'IE Mobile'
}, {
	regex : /(MSIE) (\d+)\.(\d+).*(Windows Phone)/,
	subString : 'IE Mobile'
}, {
	regex : /(MSIE) (\d+)\.(\d+)/,
	subString : 'IE Browser'
}, {
	regex : /^(Nokia)/,
	subString : 'Nokia Services (WAP) Browser'
}, {
	regex : /(NokiaBrowser)\/(\d+)\.(\d+)/,
	subString : 'Nokia Browser'
}, {
	regex : /(BrowserNG)\/(\d+)\.(\d+).(\d+)/,
	subString : 'Nokia Browser'
}, {
	regex : /(Series60)\/5\.0/,
	subString : 'Nokia Browser'
}, {
	regex : /(Series60)\/(\d+)\.(\d+)/,
	subString : 'Nokia OSS Browser'
}, {
	regex : /(S40OviBrowser)\/(\d+)\.(\d+)\.(\d+)\.(\d+)/,
	subString : 'Ovi Browser'
}, {
	regex : /(BB10);/,
	subString : 'BlackBerry WebKit'
}, {
	regex : /(PlayBook).+RIM Tablet OS (\d+)\.(\d+)\.(\d+)/,
	subString : 'BlackBerry WebKit'
}, {
	regex : /(Black[bB]erry).+Version\/(\d+)\.(\d+)\.(\d+)/,
	subString : 'BlackBerry WebKit'
}, {
	regex : /(Black[bB]erry)\s?(\d+)/,
	subString : 'BlackBerry'
}, {
	regex : /(UCBrowser)[ \/](\d+)\.(\d+)\.(\d+)/,
	subString : 'UC Browser'
}, {
	regex : /(UC Browser)[ \/](\d+)\.(\d+)\.(\d+)/,
	subString : 'UC Browser'
}, {
	regex : /(UC Browser|UCBrowser|UCWEB)(\d+)\.(\d+)\.(\d+)/,
	subString : 'UC Browser'
}, {
	regex : /QQBrowser/,
	subString : 'QQ Browser'
}, {
	regex : /(Android)[a-zA-Z0-9]*/,
	subString : 'AndroidWebKit'
}, {
	regex : /(OneBrowser)\/(\d+).(\d+)/,
	subString : 'ONE Browser'
}, {
	regex : /(Skyfire)\/(\d+)\.(\d+)/,
	subString : 'Skyfire'
}, {
	regex : /(SE 2\.X) MetaSr (\d+)\.(\d+)/,
	subString : 'Sogou Explorer'
}, {
	regex : /(baidubrowser)[\/\s](\d+)/,
	subString : 'Baidu Browser'
}, {
	regex : /(FlyFlow)\/(\d+)\.(\d+)/,
	subString : 'Baidu Explorer'
}, {
	regex : /(Maxthon)\/(\d+)\.(\d+)\.(\d+)/,
	subString : 'Maxthon'
}, {
	regx : /WebKit/,
	subString : 'WebKit'
} ];

BrowserDetection._dataOS = [
		{
			regex : /(Windows NT 6\.1)/,
			subString : 'Windows 7'
		},
		{
			regex : /(Windows (?:NT 5\.2|NT 5\.1))/,
			subString : 'Windows XP'
		},
		{
			regex : /(Windows NT 6\.0)/,
			subString : 'Windows Vista'
		},
		{
			regex : /(Windows NT 5\.0)/,
			subString : 'Windows 2000'
		},
		{
			regex : /(Windows NT 6\.2)/,
			subString : 'Windows 8'
		},
		{
			regex : /(Windows NT 6\.2; ARM;)/,
			subString : 'Windows RT'
		},
		{
			regex : /(Windows ?Mobile)/,
			subString : 'Windows Mobile'
		},
		{
			regex : /(Android) (\d+)\.(\d+)(?:[.\-]([a-z0-9]+))?/,
			subString : 'Android'
		},
		{
			regex : /(Android)\-(\d+)\.(\d+)(?:[.\-]([a-z0-9]+))?/,
			subString : 'Android'
		},
		{
			regex : /(Android)[a-zA-Z0-9]*/,
			subString : 'Android'
		},
		{
			regex : /(Android) Donut/,
			subString : 'Android'
		},
		{
			regex : /(Android) Eclair/,
			subString : 'Android'
		},
		{
			regex : /(Android) Froyo/,
			subString : 'Android'
		},
		{
			regex : /(Android) Gingerbread/,
			subString : 'Android'
		},
		{
			regex : /(Android) Honeycomb/,
			subString : 'Android'
		},
		{
			regex : /(Silk-Accelerated=[a-z]{4,5})/,
			subString : 'Kindle Android'
		},
		{
			regex : /(Mac OS X) (\d+)[_.](\d+)(?:[_.](\d+))?/,
			subString : 'Mac OS'
		},
		{
			regex : /Mac_PowerPC/,
			subString : 'Mac OS'
		},
		{
			regex : /(?:PPC|Intel) (Mac OS X)/,
			subString : 'Mac OS'
		},
		{
			regex : /(CPU OS|iPhone OS) (\d+)_(\d+)(?:_(\d+))?/,
			subString : 'iOS'
		},
		{
			regex : /(iPhone|iPad|iPod); Opera/,
			subString : 'iOS'
		},
		{
			regex : /(iPhone|iPad|iPod).*Mac OS X.*Version\/(\d+)\.(\d+)/,
			subString : 'iOS'
		},
		{
			regex : /(AppleTV)\/(\d+)\.(\d+)/,
			subString : 'ATV OS X'
		},
		{
			regex : /(CrOS) [a-z0-9_]+ (\d+)\.(\d+)(?:\.(\d+))?/,
			subString : 'Chrome OS'
		},
		{
			regex : /(Debian)-(\d+)\.(\d+)\.(\d+)(?:\.(\d+))?/,
			subString : 'Linux'
		},
		{
			regex : /(Linux Mint)(?:\/(\d+))?/,
			subString : 'Linux'
		},
		{
			regex : /(Mandriva)(?: Linux)?\/(\d+)\.(\d+)\.(\d+)(?:\.(\d+))?/,
			subString : 'Linux'
		},
		{
			regex : /(Symbian[Oo][Ss])\/(\d+)\.(\d+)/,
			subString : 'Symbian OS'
		},
		{
			regex : /(Symbian\/3).+NokiaBrowser\/7\.3/,
			subString : 'Symbian^3 Anna'
		},
		{
			regex : /(Symbian\/3).+NokiaBrowser\/7\.4/,
			subString : 'Symbian^3 Belle'
		},
		{
			regex : /(Symbian\/3)/,
			subString : 'Symbian^3'
		},
		{
			regex : /(Series 60|SymbOS|S60)/,
			subString : 'Symbian OS'
		},
		{
			regex : /(MeeGo)/,
			subString : 'Symbian OS'
		},
		{
			regex : /Symbian [Oo][Ss]/,
			subString : 'Symbian OS'
		},
		{
			regex : /Series40;/,
			subString : 'Nokia Series 40'
		},
		{
			regex : /(BB10)/,
			subString : 'BlackBerry OS'
		},
		{
			regex : /(Black[Bb]erry)[0-9a-z]+\/(\d+)\.(\d+)\.(\d+)(?:\.(\d+))?/,
			subString : 'BlackBerry OS'
		},
		{
			regex : /(Black[Bb]erry).+Version\/(\d+)\.(\d+)\.(\d+)(?:\.(\d+))?/,
			subString : 'BlackBerry OS'
		},
		{
			regex : /(RIM Tablet OS) (\d+)\.(\d+)\.(\d+)/,
			subString : 'BlackBerry Tablet OS'
		},
		{
			regex : /(Play[Bb]ook)/,
			subString : 'BlackBerry Tablet OS'
		},
		{
			regex : /(Black[Bb]erry)/,
			subString : 'BlackBerry OS'
		},
		{
			regex : /\(Mobile;.+Firefox\/\d+\.\d+/,
			subString : 'Firefox OS'
		},
		{
			regex : /(hpw|web)OS\/(\d+)\.(\d+)(?:\.(\d+))?/,
			subString : 'webOS'
		},
		{
			regex : /(SUSE|Fedora|Red Hat|PCLinuxOS)\/(\d+)\.(\d+)\.(\d+)\.(\d+)/,
			subString : 'Linux'
		},
		{
			regex : /(SUSE|Fedora|Red Hat|Puppy|PCLinuxOS|CentOS)\/(\d+)\.(\d+)\.(\d+)/,
			subString : 'Linux'
		},
		{
			regex : /(Ubuntu|Kindle|Bada|Lubuntu|BackTrack|Red Hat|Slackware)\/(\d+)\.(\d+)/,
			subString : 'Linux'
		}, 
		{
			regex : /SunOS/,
			subString : 'Solaris'
		}, 
		{
			regex : /(Windows Phone|Windows Phone OS) (\d+)\.(\d+)\.*(\d*)|(\d+)_(\d+)_*(\d*)/,
			subString : 'WinPhone'
		}, ];

/*
 * Usage: always use BrowserDetection.init() first Redirect to respective page
 * if not supported browser
 */
{
	BrowserDetection.init();
	if (!BrowserDetection.isCompatibleMobileBrowser()) {
		var t_pathName = window.document.location.pathname;
		var t_projectName = t_pathName.substring(0, t_pathName.substr(1).lastIndexOf('/') + 1);
		if (BrowserDetection.isDesktopBrowser()) {
			if (!BrowserDetection.isCompatibleDesktopBrowser()) {
				location.href = t_projectName + "/non-supported/desktop.html";
			}
		} else {
			location.href = t_projectName + "/non-supported/mobile.html";
		}
	}
}