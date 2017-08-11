document.addEventListener("DOMContentLoaded", function(event) {

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
        var form = this;
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
            form.reset();
        }, function(err){
        });
    })
});

function stockstatuschange(el, custom_submit) {
    var submit = false, value = null, productId = null;
    if (!custom_submit) {
        if (el.value == 'custom') {
            var customStockStatusInputGroup = el.parentElement.parentElement.getElementsByClassName('customstockstatus')[0];
            unHideField(customStockStatusInputGroup);
            customStockStatusInputGroup.getElementsByTagName('input')[0].focus();
        } else {
            hideField(el.parentElement.parentElement.getElementsByClassName('customstockstatus')[0]);
            submit = true; value = el.value;
        }
    } else {
        submit = true; value = el.parentElement.parentElement.getElementsByTagName('input')[0].value;
    }
    if (submit) {
        productId = findAncestorByClassName(el, 'product').getAttribute('data-productid');
        ajaxCall('POST', '/products/'+productId, { stock: value}).then(function(res){

        }, function(err){
            alert('something went wrong:'+err);
        })
    }
}

function editproductstock(el) {
    unHideField(el.parentElement.parentElement.getElementsByClassName('productstockedit')[0]);
    return false;
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
