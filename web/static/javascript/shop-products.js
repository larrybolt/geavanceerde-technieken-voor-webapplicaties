document.addEventListener("DOMContentLoaded", function(event) {
    console.log('ready');
    // this contains the timestamp after first render
    var product_lastupdatetimestamp = parseInt(document.getElementById('products').getAttribute('data-lastupdatetimestamp'));

    getAllProducts(product_lastupdatetimestamp);
});

function getAllProducts(last_update_timestamp){
    ajaxCall('GET', '/products/all?last='+last_update_timestamp, null).then(function(res){
        var parsed = JSON.parse(res);
        if (parsed.products) {
            var template = document.getElementById('product-template').innerHTML,
                container = document.createElement('div');
            parsed.products.forEach(function(product) {
                var el = document.createElement('div');
                el.innerHTML = template;
                el.getElementsByClassName('product')[0].setAttribute('data-productid', product.productId);
                el.getElementsByTagName('a')[0].href = '/product/'+product.productId;
                el.getElementsByTagName('a')[1].href = '/product/'+product.productId;
                el.getElementsByTagName('a')[1].innerHTML = product.name;
                el.getElementsByClassName('template-productprice')[0].innerHTML = product.price;
                el.getElementsByClassName('card-text')[0].innerHTML = product.description;
                el.getElementsByClassName('stocktext')[0].innerHTML = product.stock;
                el.getElementsByTagName('img')[0].src = product.imageUrl;
                container.appendChild(el.children[0]);
            });
            document.getElementById('products').innerHTML = container.innerHTML;
            last_update_timestamp = parsed.updateTimeStamp;
        }
        setTimeout(function(){
            getAllProducts(last_update_timestamp);
        }, 1000);
    }, function(err){
        setTimeout(function(){
            getAllProducts(last_update_timestamp);
        }, 1000);
    });
}
