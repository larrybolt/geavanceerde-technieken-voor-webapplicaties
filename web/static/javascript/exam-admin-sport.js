document.addEventListener("DOMContentLoaded", function(event) {

    document.getElementById('sport').addEventListener('submit', function(e) {
        var form = this;
        e.preventDefault(); // stop the form from actually submitting

        // a good enough form-serialise for this case
        var postData = {};
        for (var i = 0; i<this.length; i++) {
            if (this[i].name) postData[this[i].name] = this[i].value;
        }

        ajaxCall('POST', this.action, JSON.stringify(postData)).then(function(res){
            form.reset();
        }, function(err){
        });
    })
});

