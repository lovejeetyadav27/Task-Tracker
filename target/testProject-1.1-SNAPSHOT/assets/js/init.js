var ProductsApp = (function() {
   /* var productsData = [{
        id: 1,
        name: 'JBL headphones',
        description: 'This is a jbl headphone',
        count: 0
    }, {
        id: 2,
        name: 'Shampoo',
        description: 'This is a head and shoulders shampp',
        count: 0
    }, {
        id: 3,
        name: 'iPhone',
        description: 'This is an apple Iphone',
        count: 0
    }, {
        id: 4,
        name: 'Tag heuer spaceX',
        description: 'This is a Tag heuer watch',
        count: 0
    }]; */
	var productsData = [];
    var cart = [];
    var printProducts = function() {
        //do something.
        const cards = [];
        productsData.forEach(function(product, index) {
            cards.push(
                '<div class="col s12 m6"><div class="card"><div class="card-content"><span class="card-title">' +
                product.name + '</span><p>' +
                product.description + '</p></div><div class="card-action addBtn' + 
                product.id + '"><a class="btn-floating btn-large waves-effect waves-light pink lighten-1  addBtn" data-product="' + product.id + '" data-count="' + product.count + '"><i class="material-icons">add</i></a><a class="pulse pink-text" style="float: right;font-size: 3rem;" href="javascript:void(0)">' +
                '<a class="btn-floating btn-large waves-effect waves-light pink lighten-1  subBtn" data-product="' + product.id + '" data-count="' + product.count + '"><i class="material-icons">sub</i></a><a class="pulse pink-text" style="float: right;font-size: 3rem;" href="javascript:void(0)">'+
                product.count + '</a></div></div></div>');
            $('#cards-wrapper').html(cards);
        });
    }
    return {
        getProducts: function() {
           // printProducts();
             $.ajax({
                 url: 'productlist',
                 type: 'GET',
                 success: function(res) {
                     var data = JSON.parse(res);
                     productsData = data.listofitem;
                     printProducts();
                     // do the for each as on dummy data and renderr
                 },
                 error: function(XMLHttpRequest, textStatus, errorThrown) {
                     response = "err--" + XMLHttpRequest.status + " -- " + XMLHttpRequest.statusText;
                 }
             });
        },

        addProducts: function() {
            $("body").on("click", '.addBtn', function(e) {
                e.preventDefault();
                console.log($(this).data('product'));
                console.log(e.target.value);
                var id = $(this).data('product');
                var count = $(this).data('count');
                var element = $(this);
                const selectedID = $(element).data('product');

                var selectedProduct = productsData.filter(function(obj, index) {
                    return obj.id == selectedID;
                });
                var indexOfElement = productsData.findIndex(function(ele) {
                    return ele.id == selectedID;
                });
                selectedProduct[0].count = count + 1;
                var data = {
                    'id': selectedProduct[0].id,
                    'count': selectedProduct[0].count
                };
                // ajax to increase the count -- keeps track over the backend
                $.ajax({
                    url: 'updatecart',
                    type: 'POST',
                    dataType: 'json',
                    contentType: "application/json",
                    data: JSON.stringify(data),
                    success: function(res) {
                    	var item = (res);
                    	console.log(data);
                        productsData[indexOfElement] = (item.updatedItem[0]);
                        printProducts();
                    },
                    error: function(XMLHttpRequest, textStatus, errorThrown) {
                        response = "err--" + XMLHttpRequest.status + " -- " + XMLHttpRequest.statusText;
                    }
                });
            });
        },
        
        subProducts: function() {
            $("body").on("click", '.subBtn', function(e) {
                e.preventDefault();
                console.log($(this).data('product'));
                console.log(e.target.value);
                var id = $(this).data('product');
                var count = $(this).data('count');
                var element = $(this);
                const selectedID = $(element).data('product');

                var selectedProduct = productsData.filter(function(obj, index) {
                    return obj.id == selectedID;
                });
                var indexOfElement = productsData.findIndex(function(ele) {
                    return ele.id == selectedID;
                });
                if(count>0){
                selectedProduct[0].count = count - 1;
                }
                var data = {
                    'id': selectedProduct[0].id,
                    'count': selectedProduct[0].count
                };
                // ajax to increase the count -- keeps track over the backend
                $.ajax({
                    url: 'updatecart',
                    type: 'POST',
                    dataType: 'json',
                    contentType: "application/json",
                    data: JSON.stringify(data),
                    success: function(res) {
                    	var item = (res);
                    	console.log(data);
                        productsData[indexOfElement] = (item.updatedItem[0]);
                        printProducts();
                    },
                    error: function(XMLHttpRequest, textStatus, errorThrown) {
                        response = "err--" + XMLHttpRequest.status + " -- " + XMLHttpRequest.statusText;
                    }
                });
            });
        },

        printSummary: function() {
            $('.cart').click(function(e) {
                e.preventDefault();
                $('#summary-wrapper').toggleClass('inactive');
                $('#cards-wrapper').toggleClass('inactive');

                const summaryCard = [];
                productsData.map(function(ele) {
                    if (ele.count > 0)
                        cart.push(ele);
                });
                $('#summary-wrapper').html('');
                $('#summary-wrapper').html('<div class="col s12 m6">'+
                		                    '<div class="card blue-grey darken-1">'+
                		                    '<div class="card-content white-text">'+
                		                    '<span class="card-title">Summary</span>'+
                		                    '<ul class="collection"><div style="width:100%; height: 20px; "><span id="summaylist">'+
                		                    '<span></li></ul></span><span id="totalvalue"><span></div></div > ');
                var total;
               summaryCard.push('<div style="width: 25%; float:left; margin: 0px;  background-color: grey;">Name</div>'+
                	  '<div style="width: 25%; float:left; margin: 0px; background-color: grey;">Quantity</div>'+
                	  '<div style="width: 25%; float:left; margin: 0px; background-color: grey;">Price</div>'+
                	  '<div style="width: 25%; float:left; margin: 0px; background-color: grey; ">Total</div>')
                cart.forEach(function(product, index) {
                   /* summaryCard.push('<li class="collection-item" style="color:black;"> <span >' +
                                   product.name + '</span><span style="margin-left:50px">' + 
                                   product.count + '</span>'+
                                    '</span><span style="margin-left:50px">' + 
                                   product.price + '</span> '+
                                   '</span><span style="margin-left:50px">' + 
                                   product.count*product.price + '</span>')*/
                	
                	
                	 summaryCard.push('<div style="width: 25%; float:left; margin: 0px; ">'+product.name +'</div>'+
                	  '<div style="width: 25%; float:left; margin: 0px;">'+product.count  +'</div>'+
                	  '<div style="width: 25%; float:left; margin: 0px;">'+ product.price +'</div>'+
                	  '<div style="width: 25%; float:left; margin: 0px; ">'+ product.count*product.price +'</div>')
                                   
                    total+=Number(product.count*product.price);
                });
               summaryCard.push('<div style="width: 25%; float:left; margin: 0px;  background-color: grey;">Total</div>'+
                 	  '<div style="width: 25%; float:left; margin: 0px; background-color: grey;"></div>'+
                 	  '<div style="width: 25%; float:left; margin: 0px; background-color: grey;"></div>'+
                 	  '<div style="width: 25%; float:right; margin: 0px; background-color: grey; ">'+total+'</div>')
                
                 $('#summaylist').html(summaryCard);
                 $('#totalvalue').html(total);
            })
        }
    }
    //returning the object.
})();

ProductsApp.getProducts();
ProductsApp.subProducts();
ProductsApp.addProducts();
ProductsApp.printSummary();