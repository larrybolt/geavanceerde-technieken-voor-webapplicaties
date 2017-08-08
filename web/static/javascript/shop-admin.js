document.addEventListener("DOMContentLoaded", function(event) {
    console.log('ready');

    // VanillaJS code to show the custom stock status field when custom is selected in the dropdown list
    var customStockStatus = document.getElementById('customstockstatus')
    document.getElementById('stockstatus').addEventListener("change", function(){
        if (this.value === 'custom') {
            // if we selected the custom status
            if (customStockStatus.className.indexOf('hidden') !== -1) {
                // and the field has the hidden class
                customStockStatus.className = customStockStatus.className
                    .split(' ') // split current classname in classnames
                    .filter(function(c){ return c !== 'hidden'}) // only return the non-hidden ones
                    .join(' '); // join them again (PS, so glad MVC JS frameworks are a thing now, this is horrible)
                // and we might as well focus in the custom status input fields
                document.getElementById('stockstatuscustom').focus();
                // I don't think we have to *clear* the input, really depends on the client/situation
            }
        } else {
            // if the value is not custom
            if (customStockStatus.className.indexOf('hidden') === -1) {
                // and the hidden classname is not set
                customStockStatus.className += ' hidden';
            }
        }
    })

    document.getElementById('addproduct').addEventListener('submit', function(e) {
        e.preventDefault(); // stop the form from actually submitting
        var postData = {};
        console.log(this, this.length);
        for (var i = 0; i<this.length; i++) {
            if (this[i].name) postData[this[i].name] = this[i].value;
        }
        console.log('ready to post', postData);

    })
});

