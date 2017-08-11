var ws;
document.addEventListener("DOMContentLoaded", function(event) {
    ws = new WebSocket("ws://"+window.location.host+"/");
    ws.onopen = function(ev) {
        console.log("Socket connected", ev);
    }
    ws.onmessage = function(ev) {
        try {
            var message = JSON.parse(ev.data);
            console.log("Received mesasge", message);
            addComment(message);
        } catch (e) {}
    }
    ws.onclose = function(ev) {
    }
});

function postComment(el) {
    console.log(arguments);
    var form = el;
    var comment = form.comment.value, name = form.name.value, postId = form.postId.value;
    form.reset();
    ws.send(JSON.stringify({
        postId: postId,
        name: name,
        comment: comment
    }));

    // prevent form from being submitted
    return false;
}

function addComment(comment) {
    var template = document.getElementById('blog-comment-template').innerHTML,

    container = document.querySelector('[data-postid="'+comment.postId+'"] .comments');
    var el = document.createElement('div');
    el.innerHTML = template;

    el.getElementsByClassName('comment')[0].getElementsByTagName('div')[0].innerHTML = comment.comment;
    el.getElementsByClassName('comment')[0].getElementsByClassName('name')[0].innerHTML = comment.name;

    container.appendChild(el.children[0]);

}