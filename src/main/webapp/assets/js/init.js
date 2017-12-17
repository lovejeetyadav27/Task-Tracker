


var ProductsApp = (function() {
   
	
	//var productsData = [];
	var tasksList = [];
	var taggedItemsData = [];
	var username=[];
    var cart = [];
    
    var printTasks = function() {
        //do something.
        const cards = [];
        tasksList.forEach(function(task, index) {
            cards.push(
                '<div class="col s12 m12"><div class="card" data-id="'+ task.id +'">'+
                '<div class="card-content"><span class="card-title">' +
                task.name + '</span><p>' +
                task.description + '</p><p>From -&nbsp;' +
                task.fromdate + '</p><p>To&nbsp;&nbsp;&nbsp;&nbsp;-&nbsp;' +
                task.todate + '</p></div>'+
                '<div class="card-action addBtn' + 
                task.id + '"><a class="btn-floating btn-large waves-effect waves-light pink lighten-1  addBtn" data-id="' + task.id + '" data-todate="' + task.todate +'" data-fromdate="' + task.fromdate +'"><i class="material-icons">add</i></a><a class="pulse pink-text" style="float: right;font-size: 3rem;" href="javascript:void(0)">' +
                task.id + '</a></div></div></div>');
            $('#cards-wrapper').html(cards);
        });
    }
    return {
    	
    
    	getintaggedjobs : function() {
    		
    		
    		 $.ajax({
                 url: 'taggedinjobs',
                 type: 'GET',
                 success: function(res) {
                     var data = JSON.parse(res);
                     
                     tasksList = data.taggedjobs;
                     const tagjobs=[];
                     tasksList.forEach(function(jobs,index){
                    	 tagjobs.push('<div class="card card-inverse card-danger text-center">'+
                       		'  <div class="card-block">'+
                  		   ' <blockquote class="card-blockquote">'+
                  		     ' <p>'+jobs.name+' </p>'+
                  		     ' <footer>Task Id :<cite title="Source Title">'+jobs.id+'</cite></footer>'+
                  		    '</blockquote>'+
                  		  '</div>'+
                  		'</div>');
                    	 
                    	 
                     })
                     $('#tahtask').html(tagjobs);
                     
                     
                    
                     // do the for each as on dummy data and renderr
                 },
                 error: function(XMLHttpRequest, textStatus, errorThrown) {
                     response = "err--" + XMLHttpRequest.status + " -- " + XMLHttpRequest.statusText;
                 }
             });
    		
    	},
    
        getProducts: function() {
           // printProducts();
             $.ajax({
                 url: 'tasklist',
                 type: 'GET',
                 success: function(res) {
                     var data = JSON.parse(res);
                     
                     if(res.status == 0){
                      	window.location.href = '/testProject';
                      }
                     
                     tasksList = data.listofitem;
                     printTasks();
                     
                    
                     // do the for each as on dummy data and renderr
                 },
                 error: function(XMLHttpRequest, textStatus, errorThrown) {
                     response = "err--" + XMLHttpRequest.status + " -- " + XMLHttpRequest.statusText;
                 }
             });
        },
        
        getTaggedItems: function(){
        	$.ajax({
        		url: 'taggeditems',
        		type: 'GET',
        		success: function(res){
        			var data = JSON.parse(res);
        			taggedItemsData = data.taggedItems;
        			username = data.username;
        		},
        		error: function(XMLHttpRequest, textStatus, errorThrown) {
                    response = "err--" + XMLHttpRequest.status + " -- " + XMLHttpRequest.statusText;
                }
        	})
        },
        
        // on click handler for cards - to print tagged items
        printTaggedItems(){
        	$('body').on('click', '.addBtn', function(e){
        		 e.preventDefault();
        		 $('#summary-wrapper').toggleClass('inactive');
                 $('#cards-wrapper').toggleClass('inactive');
                 
                 console.log($(this).data('id'));
                 const id = $(this).data('id');
                 var renderList = [];
                 var taggedList = [];
                 taggedItemsData.forEach(function(ele){
                	 console.log(ele.taskid);
                	 if(ele.taskid == id){
                		 taggedList.push(ele.name);
                	 }
                 });
                 
                 
                 $('#summary-wrapper').html('');
                 
                 $.each(username, function(val, text) {
                	 $('#summary-wrapper').append(
                	        $('<option></option>').val(val).html(text)
                	    );
                	});
                 
                 taggedList.forEach(function(ele){
                	renderList.push(
                			'<div class="card card-inverse card-info mb-3 text-center"><div class="card-block"><blockquote class="card-blockquote"><p>'+ ele.name + '</p></blockquote></div></div>'); 
                 });
                 
                 $('#summary-wrapper').html(renderList);
        	});
        },
   
        
      
      
        
        saveTask : function(){
        	 $('.savetask').click(function(e) {
                 e.preventDefault();
                 if($('#name').val() && $('#fromdate').val() && $('#todate').val() && $('#description').val()){
                	 
                	 var data = {
                             'name': $('#name').val(),
                             'fromdate': $('#fromdate').val(),
                             'todate' : $('#todate').val(),
                             'description' : $('#description').val()
                       };
                	 $.ajax({
                         url: 'tasksave',
                         type: 'POST',
                         dataType: 'json',
                         contentType: "application/json",
                         async:false,
                         data: JSON.stringify(data),
                         success: function(res) {
                         	var item = (res);
                         	$('#name').val("");
                            $('#fromdate').val("");
                            $('#todate').val("") ;
                            $('#description').val("");
                            if(res.status == 0){
                            	window.location.href = '/testProject';
                            }
                         },
                         error: function(XMLHttpRequest, textStatus, errorThrown) {
                             response = "err--" + XMLHttpRequest.status + " -- " + XMLHttpRequest.statusText;
                         }
                     }) 
                     
                     ;
                         
                 }else{
                	 $.alert({
                		    title: 'Alert!',
                		    
                		    content: 'Please fill all asterisk(*) mark fields!',
                		});
                 }
                 })
        	
        }
    }
    //returning the object.
})();

ProductsApp.getProducts();
ProductsApp.getTaggedItems();


ProductsApp.printTaggedItems();

ProductsApp.saveTask();