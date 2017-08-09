document.addEventListener("DOMContentLoaded", function(event) {
    console.log('ready');

    // VanillaJS code to show the custom stock status field when custom is selected in the dropdown list
    var customStockStatus = document.getElementById('customstockstatus')
    document.getElementById('stockstatus').addEventListener("change", function(){
        if (this.value === 'custom') {
            // if we selected the custom status
            unHideField(customStockStatus);
            // and we might as well focus in the custom status input fields
            document.getElementById('stockstatuscustom').focus();
            // I don't think we have to *clear* the input, really depends on the client/situation
        } else {
            // if the value is not custom
            hideField(customStockStatus);
        }
    })

    document.getElementById('addproduct').addEventListener('submit', function(e) {
        e.preventDefault(); // stop the form from actually submitting

        // a good enough form-serialise for this case
        var postData = {};
        for (var i = 0; i<this.length; i++) {
            if (this[i].name === 'stockstatus' || this[i].name === 'customstockstatus') continue;
            if (this[i].name) postData[this[i].name] = this[i].value;
        }
        postData.stock = (this.stockstatus.value === 'custom')
            ? this.customstockstatus.value
            : this.stockstatus.value;

        ajaxCall('POST', this.action, JSON.stringify(postData)).then(function(res){
            console.log('it worked!', res);
        }, function(err){
            console.log('Error happened', err);
        });
    })
});

function stockstatuschange(el, custom_submit) {
    if (!custom_submit) {
        if (el.value == 'custom') {
            var customStockStatusInputGroup = el.parentElement.parentElement.getElementsByClassName('customstockstatus')[0];
            unHideField(customStockStatusInputGroup);
            customStockStatusInputGroup.getElementsByTagName('input')[0].focus();
        } else {
            hideField(el.parentElement.parentElement.getElementsByClassName('customstockstatus')[0]);
        }
    }
}

function hideField(field){
    if (field.className.indexOf('hidden') === -1) {
        // and the hidden classname is not set
        field.className += ' hidden';
    }
}
function unHideField(field) {
    if (field.className.indexOf('hidden') !== -1) {
        // and the field has the hidden class
        field.className = field.className
            .split(' ') // split current classname in classnames
            .filter(function(c){ return c !== 'hidden'}) // only return the non-hidden ones
            .join(' '); // join them again (PS, so glad MVC JS frameworks are a thing now, this is horrible)
    }
}
