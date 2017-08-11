jQuery(function($) {
    $('body').on('submit', '.chat form', function(ev){
        ev.preventDefault();
        var postData = {};
        for (var i = 0; i<this.length; i++) {
            if (this[i].name) postData[this[i].name] = this[i].value;
        }
        this.reset();
        $.ajax({
            type: "POST",
            url: this.action,
            data: JSON.stringify(postData)
        }).then(function(res){
            $(this).find('input').focus();
        });
    })
    getLatestMessages();
});

function getLatestMessages(last){
    var last = last || 0;
    $.ajax({
        type: "GET",
        url: '/chat?last='+last
    }).then(function(res){
        var messages = res;
        if (messages.length > 0) {
            last = messages[messages.length-1].MessageId;
            appendLatestMessages(messages);
        }
        setTimeout(function() {
            getLatestMessages(last);
        }, 500);
    });
}

function appendLatestMessages(messages){
    for (var i = 0; i<messages.length; i++){
        var message = messages[i];

        var chatcontainer;
        var $template;

        if ($('#chats').length > 0) {
            // we are a support user
            var userId =  (message.from.role === 'USER' ? message.from.userId : message.to.userId);
            if ($('#chats #chatuser'+userId).length === 0) {
                var username =  (message.from.role === 'USER' ? message.from.userName : message.to.userName);
                $($('#chat-template').html())
                    .find('.chatheader').attr('href', '#chatuser'+userId).text(username)
                    .closest('.card')
                    .find('.collapse').attr('id', 'chatuser'+userId)
                    .find('input[name=toUserId]').attr('value', userId)
                    .closest('.card')
                    .appendTo('#chats');
            }
            chatcontainer = '#chatuser'+userId+' ul';
            $template = message.from.role === 'SUPPORT' ?
                $($('#chat-me-template').html()) :
                $($('#chat-other-template').html());
            if (message.from.role === 'USER') {
                $template.find('strong').text('User');
            }
        } else {
            chatcontainer = '.chat ul';
            $template = message.from.role === 'USER' ?
                $($('#chat-me-template').html()) :
                $($('#chat-other-template').html());
        }

        $template
            .find('.messagecontent').text(message.Message)
            .closest('li')
            .find('time').attr('datetime', formatDateTime(message.datetime))
            .closest('li')
            .appendTo(chatcontainer)
            .hide()
            .fadeIn('slow');
    }
    $(chatcontainer).animate({ scrollTop: $(chatcontainer).prop("scrollHeight")}, 1000);
    $('time').timeago();
}

function formatDateTime(dt){
    return dt.date.year+'-'+dt.date.month.pad(2)+'-'+dt.date.day.pad(2)+'T'+dt.time.hour.pad(2)+':'+dt.time.minute.pad(2)+':'+dt.time.second.pad(2)+'Z';
}

Number.prototype.pad = function(size) {
    var s = String(this);
    while (s.length < (size || 2)) {s = "0" + s;}
    return s;
}