//Module Wrapper Function
(function (exports, require, module, __filename, __dirname) {

    var url = 'https://mylogger.io/log';

    function log(message) {
        // Send an HTTP request
        console.log(message);
    }

//Export single function
    module.exports = log;

//Export an Object
//module.exports.log = log;
//module.exports.endPoint = url;
})