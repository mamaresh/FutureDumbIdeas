/* Future Dumb Ideas Plagarised Helper JS */
/* 8-23-2015 */

var form;

form.onsubmit = function (e) {
    e.preventDefault();
    
    var loginCredentials = {};
    for (var i = 0, ii = form.length; i < ii; ++i) {
        var input = form[i];
        if (input.name) {
            loginCredentials[input.name] = input.value;
        }
    }
    
    console.log(data);
    
    // HTTP
    var postRequest = new XMLHttpRequest();
    postRequest.open(form.method, form.action, true);
    postRequest.setRequestHeader('Content-Type', 'application/json; charset=UTF-8');
    
    // send JSON
    postRequest.send(JSON.stringify(data));
    
    // WTF is this?
    postRequest.onloadend = function () {
        // done
    };
};