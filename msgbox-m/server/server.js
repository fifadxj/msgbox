const  fs = require('fs'), 
    https = require('https'), 
     http = require('http'),
httpProxy = require('http-proxy'), 
     path = require('path'),
	  url = require("url"),
 mockServer = require("./mock/mockServer.js");
    sleep = require('sleep');

const mimeTypes = {
	"html" : "text/html",
	"jpeg" : "image/jpeg",
	"jpg" : "image/jpeg",
	"png" : "image/png",
	"js" : "text/javascript",
	"css" : "text/css"
};

//var options = {
//	https : {
////		key : fs.readFileSync('server/privatekey.pem', 'utf8'),
////		cert : fs.readFileSync('server/certificate.pem', 'utf8')
//	}
//};

module.exports.run = function(options) {
	this.createServer(options);
	
	mockServer.run(options.wsContext);
};

module.exports.createServer = function(options) {
	var proxy = new httpProxy.HttpProxy({
	  target: {
		  host: options.wsConfig.host,
		  port: options.wsConfig.port
	  }
	});


    //var proxy = new httpProxy.RoutingProxy();
//	https.createServer(options.https, function(req, res) {
//		res.writeHead(200);
//		if (req.url.indexOf(options.wsContext) >= 0) {
//			//proxy.proxyRequest(req, res, options.host);
//			proxy.proxyRequest(req, res);
//		} else {
//			staticFileHandler(req, res);
//		}
//	}).listen(8443);

	http.createServer(function(req, res) {
		var uri = url.parse(req.url).pathname;
		//console.log("href " + url.parse(req.url).href);
		//console.log("pathname " + uri);
		
		//append client root context if it does not exist in request
		if(uri === '/' || uri === '/'+options.clientContext) {
			res.writeHead(302, {
				'Content-Type' : 'text/plain',
				'location' : '/' + options.clientContext + '/'
			});
			res.write('302 Found\n');
			res.end();
			return;
		}
		
		//if it is a request to server(webservice)
		else if (req.url.indexOf(options.wsContext) >= 0) {
			sleep.sleep(1);
			proxy.proxyRequest(req, res);
        } 
		
		//if it is a request to client(html, js, css, etc..)
		else if (uri.indexOf(options.clientContext) >= 0) {
            staticFileHandler(req, res);
		}
		
		//if it is a request to neither client & server, reject request
		else {
			res.writeHead(404, {
				'Content-Type' : 'text/plain'
			});
			res.write('404 Not Found\n');
			res.end();
			return;
		}
	}).listen(8000);

	var staticFileHandler = function(req, res) {
		var uri = url.parse(req.url).pathname;

	    uri = uri.replace('/' + options.clientContext + '/', '');
	    //console.log('=========' + uri);

		var t_projectSrcPath = [ process.cwd(),  options.src, unescape(uri) ].join('/');
		//console.log('++++++++' + t_projectSrcPath);
		var filename = path.join(t_projectSrcPath);

		var stats;
		try {
			stats = fs.lstatSync(filename); // throws if path doesn't exist
		} catch (e) {
			res.writeHead(404, {
				'Content-Type' : 'text/plain'
			});
			res.write('404 Not Found\n');
			res.end();
			return;
		}

		if (stats.isFile()) {
			// path exists, is a file
			var mimeType = mimeTypes[path.extname(filename).split(".")[1]];
			res.writeHead(200, {
				'Content-Type' : mimeType
			});

			var fileStream = fs.createReadStream(filename);
			fileStream.pipe(res);
		} else if (stats.isDirectory()) {
			var fileStream = fs.createReadStream(filename + '/index.html');
			fileStream.pipe(res);
		} else {
			// Symbolic link, other?
			// TODO: follow symlinks? security?
			res.writeHead(500, {
				'Content-Type' : 'text/plain'
			});
			res.write('500 Internal server error\n');
			res.end();
		}
	};

};
