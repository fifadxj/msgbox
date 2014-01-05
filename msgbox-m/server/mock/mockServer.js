const fs = require('fs'),
    https = require('https'),
    http = require('http'),
    httpProxy = require('http-proxy'),
    path = require('path'),
    url = require("url"),
    sys = require("sys");

module.exports.run = function(wsContext) {
    this.createServer(wsContext);
};

module.exports.createServer = function(wsContext) {
    var mockServer = http.createServer(function(req, res) {
        function notFound(req, res) {
            var NOT_FOUND = "Not Found\n";
            res.writeHead(404, [["Content-Type", "text/plain"], ["Content-Length", NOT_FOUND.length]]);
            res.write(NOT_FOUND);
            res.end();
        }
        if (req.method === "GET" || req.method === "HEAD" || req.method === "POST") {
        	var path = url.parse(req.url).pathname;
        	console.log(path);
        	path = path.replace('/' + wsContext, '');
        	console.log(path);
            var handler = mockServer.getMapping[path] || notFound;

            res.simpleText = function(code, body) {
                res.writeHead(code, [ [ "Content-Type", "text/plain" ],	[ "Content-Length", body.length ] ]);
                res.write(body);
                res.end();
            };

            res.simpleJSON = function(code, obj) {
                var body = JSON.stringify(obj);
                res.writeHead(code, [ [ "Content-Type", "text/json" ], [ "Content-Length", body.length ] ]);
                res.write(body);
                res.end();
            };

            handler(req, res);
        }
    });

    mockServer.getMapping = {};
    mockServer.get = function (path, handler) {
    	mockServer.getMapping[path] = handler;
    };

    mockServer.readJSONfile = function(fileName, callback){
        fs.readFile(fileName, 'utf8', function(err, data) {
            if (err) throw err;
            callback(data);
        });
    };
    mockServer.staticHandler = function (filename) {
        function extname (path) {
            var index = path.lastIndexOf(".");
            return index < 0 ? "" : path.substring(index);
        };
        var body, headers;
        var content_type = mockServer.mime.lookupExtension(extname(filename));
        var encoding = (content_type.slice(0,4) === "text" ? "utf8" : "binary");

        function loadResponseData(callback) {
            if (body && headers) {
                callback();
                return;
            }

            sys.puts("loading " + filename + "...");
            fs.readFile(filename, encoding, function (err, data) {
                if (err) {
                    sys.puts("Error loading " + filename);
                } else {
                    body = data;
                    headers = [["Content-Type", content_type ], [ "Content-Length" , body.length]];

                    headers.push(["Cache-Control", "public"]);

                    sys.puts("static file " + filename + " loaded");
                    callback();
                }
            });
        };

        return function (req, res) {
            loadResponseData(function () {
                res.writeHead(200, headers);
                res.write(body, encoding);
                res.end();
            });
        };
    };
    mockServer.mime = {
        lookupExtension : function(ext, fallback) {
            return mockServer.mime.TYPES[ext.toLowerCase()] || fallback || 'application/octet-stream';
        },
        TYPES : {".html"  : "text/html",
            ".js"    : "application/javascript",
            ".json"  : "application/json",
            ".png"   : "image/png",
            ".xml"   : "application/xml"
        }
    };

    mockServer.get("/services/security/login", function(req, res){
        var body='';
        req.on('data', function (data) {
            body +=data;
        });
        req.on('end',function(){
            var resultObject = JSON.parse(body);
            var t_userId = resultObject.username,
                t_password = resultObject.password;

            if (t_userId === '111111'/* && t_password === 'password'*/ ){
            	mockServer.staticHandler("server/mock/login/login-success.json")(req, res);
            } else {
            	mockServer.staticHandler("server/mock/login/login-error.json")(req, res);
            }
        });
    });

    mockServer.get("/services/security/logout", mockServer.staticHandler("server/mock/logout/logout.json"));

    mockServer.get("/services/message/list", mockServer.staticHandler("server/mock/message/list.json"));
    mockServer.get("/services/message/view", mockServer.staticHandler("server/mock/message/view.json"));
    
    mockServer.listen(5555, null);
};
