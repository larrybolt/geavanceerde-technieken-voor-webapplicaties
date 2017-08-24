document.addEventListener("DOMContentLoaded", function(event) {
    // this contains the timestamp after first render
    updateSport();
});

function updateSport(){
    ajaxCall('GET', '/sport/all', null).then(function(res){
        var parsed = JSON.parse(res);
            var container = '';
            parsed.forEach(function(a) {
                // [{"ActivityId":0,"Day":"Maandag","Time":"18:00","Sport":"Hockey","Name":"Miyo","Location":"Boom"}]
                var all = '';
                all += a.Day;
                all += ' - ';
                all += a.Time;
                all += ' - ';
                all += a.Sport;
                all += ' - ';
                all += a.Name;
                all += ' - ';
                all += a.Location;
                container += '<div class="row">'+all+'</div>';
            });
            document.getElementById('activities').innerHTML = container;
        setTimeout(function(){
            updateSport();
        }, 3000);
    }, function(err){
        setTimeout(function(){
            updateSport();
        }, 3000);
    });
}
