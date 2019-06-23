var http = require('http');

var port=8000;
var httpServer = http.createServer(function(req, res){
res.writeHead(200,{"content-Type":"text/html"})
res.write("<html><body><b>Helloworld</b></body></html>");
res.end();
}).listen(port);

//httpServer.listen(port);