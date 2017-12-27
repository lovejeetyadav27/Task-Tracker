


var ProductsApp = (function() {
   
	
	var renderList = [];
	var tasksList = [];
	var tasksaddedbyadminlist=[];
	var taggedItemsData = [];
	var totalusers =[];
	var username=[];
    var cart = [];
    
    var printTasks = function() {
        // do something.
        const cards = [];
        tasksaddedbyadminlist.forEach(function(task, index) {
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
    	
    	searchtaskidauto : function(){

    		
       	 $('#entertaskid').change(function(e) {
                e.preventDefault();
                console.log($('#entertaskid').val());
                if($('#entertaskid').val()){
               
                	 var data = {
                             'taskid': $('#entertaskid').val()
                       };
                	 $.ajax({
                         url: 'searchtask',
                         type: 'POST',
                         dataType: 'json',
                         contentType: "application/json",
                         data: JSON.stringify(data),
                         success: function(res) {
                         	var item = (res);
                         	$('#entertaskid').val("");
                            
                            tasksaddedbyadminlist=[];
                            
                            tasksaddedbyadminlist = res.listofitem;
                            if(tasksaddedbyadminlist==""){
                            	alert("NO TASK FOUND");
                            }else
                            printTasks();
                         },
                         error: function(XMLHttpRequest, textStatus, errorThrown) {
                             response = "err--" + XMLHttpRequest.status + " -- " + XMLHttpRequest.statusText;
                             alert("NO TASK FOUND");
                         }
                     });
                         
                 }
                })
    		
    		
    		
    	},
    
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
                     $('#tagtaskid').html(tagjobs);
                     
                     
                    
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
                     
                     tasksaddedbyadminlist = data.listofitem;
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
        			totalusers = data.totalusers;
        			
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
                 
                 var taggedList = [];
                 var useroptionlist=[];
                 
                 taggedItemsData.forEach(function(ele){
                	 console.log(ele.taskid);
                	 if(ele.taskid == id){
                		 taggedList.push(ele.name);
                	 }
                 });
                 
                 
                 $('#summary-wrapper').html('');
                 
                 renderList=[];
                 
                 tasksaddedbyadminlist.forEach(function(task, index) {
                	 
                	 if(task.id==id){
                		 renderList.push('<div class="col s12 m12"><div class="card" data-id="'+ task.id +'">'+
                         '<div class="card-content"><span class="card-title">' +
                         task.name + '</span><p>' +
                         task.description + '</p><p>From -&nbsp;' +
                         task.fromdate + '</p><p>To&nbsp;&nbsp;&nbsp;&nbsp;-&nbsp;' +
                         task.todate + '</p ></div>'+
                         '<div class="card-action addBtn' + 
                         task.id + '"><a class="btn-floating btn-large waves-effect waves-light pink lighten-1  addBtn" data-id="' + task.id + '" data-todate="' + task.todate +'" data-fromdate="' + task.fromdate +'"><i class="material-icons">add</i></a><a class="pulse pink-text" style="float: right;font-size: 3rem;" href="javascript:void(0)">' +
                         task.id + '</a></div></div></div>');
                 
                	 }
                	 $('#summary-wrapper').html(renderList);
                 
                 });
                 
                 var listofitems=[];
                 
                 totalusers.forEach(function(user,index){
                	 listofitems.push(  '<li name="'+ user.name+'" value="'+ user.name+'" data-id="'+ user.id+'">'+ user.name+' <button class="addtolist-btn"> ADD Item </button></li>');
                	 
                	 
                 });
                 
                 
                 
                 renderList.push('<div class="col s12 m12"><div class="card" >'+
                         '<div class="card-content"> '+
                		 '<ul>'+
                		listofitems + 
                 '</ul>'+
                 '</div>'+
                 '</div></div>'); 
                 
                 
                 
                 
                 
                 
                 taggedList.forEach(function(ele){
                	renderList.push('<div class="col s12 m12"><div class="card" >'+
                            '<div class="card-content"><span class="card-title">' +
                            ele + '</span></div>'+
                            '</div></div>'); 
                 });
                 
                 $('#summary-wrapper').html(renderList);
                 $('#userslist').html(listofitems);
        	});
        },
   
        
      
      //tag the user for task
        tagUser : function() {
        	console.log('kaisa');
        	$('body').on('click', '.addtolist-btn', function(e){
        		e.preventDefault();
        		console.log($(this).data('id'));
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
                            console.log("data success part of saving");
                            ProductsApp.getProducts();
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
    // returning the object.
})();

ProductsApp.searchtaskidauto();
ProductsApp.getProducts();
ProductsApp.getTaggedItems();
ProductsApp.printTaggedItems();
ProductsApp.getintaggedjobs();
ProductsApp.saveTask();
ProductsApp.tagUser();