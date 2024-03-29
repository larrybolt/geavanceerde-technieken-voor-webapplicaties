function ajaxCall(method, url, data) {
    return new Promise(function(resolve, reject) {
        var xmlhttp = new XMLHttpRequest(); // 0
        xmlhttp.onreadystatechange = function() { // 3
            if (xmlhttp.readyState === 4) {
                // 4
                if (xmlhttp.status === 200) {
                    resolve(xmlhttp.response);
                }
                else {
                    reject(xmlhttp.statusText);
                }
            }
        }
        xmlhttp.open(method, url, true); // 1
        // be a good citizen, do this so server knows it trough ajax
        xmlhttp.setRequestHeader('X-Requested-With', 'XMLHttpRequest');

        if (data && typeof data === 'object')
            data = JSON.stringify(data);
        xmlhttp.send(data);
        // 2
    })
}

function findAncestorByClassName (el, cls) {
    while ((el = el.parentElement) && !el.classList.contains(cls));
    return el;
}
