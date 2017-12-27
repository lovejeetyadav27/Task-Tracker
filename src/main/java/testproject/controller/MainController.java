package testproject.controller;


import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import testproject.projectutls.ErrorMessages;
import testproject.service.MainService;


@RestController
@RequestMapping("/")
public class MainController {
	
	@Autowired
	MainService mainService;
	
	 @RequestMapping(value="/home", method = RequestMethod.POST)
	    public ModelAndView sayHello(ModelMap model,HttpServletRequest request) {
		 
		 Boolean status = true;
	
		 
	     String error="please provide UserName/Password";
		 String username = request.getParameter("username");
		 String password = request.getParameter("password");
		 
		 if(username.equals("")||password.equals("")){
			 
			 if(request.getSession().getAttribute("userid")==null)
				 status=false;
			
		 }else{
			 status = mainService.checkCredentials(username,password,request);
		 }
		 
		 if(status){
				
				ModelAndView mv=new ModelAndView("index");
			    //mv.addObject("list",status);
			    return mv;
				}
				
			else
				error="Something went Wrong. Please try again latter";
				return new ModelAndView("login","errorMessage",error);
		 
		
	    }
	 
	 
	 // to get tasks added by admin using admin id
	 	
	    @RequestMapping(value = "/tasklist", method = RequestMethod.GET)
	    public String TasksList( HttpServletRequest httpServletRequest) {
	    	String resp = null;
	    	
	        int userid=-1;
	    	if(httpServletRequest.getSession().getAttribute("userid")!=null)
	    	userid =(int) httpServletRequest.getSession().getAttribute("userid");
	    	
	    	if(userid==-1){
	    		
	    		return ErrorMessages.getErrormsg("0", "timeout");
	    		
	    	}
	    	resp=mainService.getTaskDetails(userid);  	
	        return resp;
	        
	    }
	    
	    //for Searching the task ID
	    @RequestMapping(value ="/searchtask", method = RequestMethod.POST)
	    public String searchtask(@RequestBody HashMap<String,String> requestPayload, HttpServletRequest httpServletRequest) {
	    	String resp="";
	    	String id =  requestPayload.get("taskid");
	    	  	
	    	int userid=-1;
	    	if(httpServletRequest.getSession().getAttribute("userid")!=null)
	    	userid =(int) httpServletRequest.getSession().getAttribute("userid");
	    	
	    	if(userid==-1){
	    		
	    		return ErrorMessages.getErrormsg("0", "timeout");
	    		
	    	}
	    	resp = mainService.getTaskById(id,userid);
			return resp;
	   
	    }
	    
	    
	    
	    
	    // To get all users Tagged by the Admin
	    
	    @RequestMapping(value = "/taggeditems", method = RequestMethod.GET)
	    public String ajaxUrl( HttpServletRequest httpServletRequest) {
	    	String resp="";
	    	int userid=-1;
	    	if(httpServletRequest.getSession().getAttribute("userid")!=null)
	    	userid =(int) httpServletRequest.getSession().getAttribute("userid");
	    	
	    	if(userid==-1){
	    		
	    		return ErrorMessages.getErrormsg("0", "timeout");
	    		
	    	}
	    	resp=mainService.getTaggedUsers(userid);
	    	
	        return resp;
	    }
	    
	    
	    //Save The Task
	    @RequestMapping(value ="/tasksave", method = RequestMethod.POST)
	    public String savetask(@RequestBody HashMap<String,String> requestPayload, HttpServletRequest httpServletRequest) {
	    	String resp="";
	    	String name = (String) requestPayload.get("name");
	    	String fromdate = (String) requestPayload.get("fromdate");
	    	String todate = (String) requestPayload.get("todate");
	    	String description = (String) requestPayload.get("description");  	
	    	int userid=-1;
	    	if(httpServletRequest.getSession().getAttribute("userid")!=null)
	    	userid =(int) httpServletRequest.getSession().getAttribute("userid");
	    	
	    	if(userid==-1){
	    		
	    		return ErrorMessages.getErrormsg("0", "timeout");
	    		
	    	}
	    	resp = mainService.savetask(name,fromdate,todate,description,userid);
	    	if(resp.equals("-1")){
	    		return ErrorMessages.getErrormsg("0","Not saved");
	    	}
			return ErrorMessages.getErrormsg("1", "Success");
	    	
	    	
	       
	    }
	    
	   
	    
	    
	    
	    @RequestMapping(value ="/taggedinjobs", method = RequestMethod.GET)
	    public String taggedinjobs(HttpServletRequest httpServletRequest) {
	    	String resp="";
	    	  	
	    	int userid=-1;
	    	if(httpServletRequest.getSession().getAttribute("userid")!=null)
	    	userid =(int) httpServletRequest.getSession().getAttribute("userid");
	    	
	    	if(userid==-1){
	    		return ErrorMessages.getErrormsg("0", "timeout");
	    		
	    	}
	    	resp = mainService.getTaggedjobs(userid);
			return resp;
	   
	    }
	    
	    
	    
	    	
}
