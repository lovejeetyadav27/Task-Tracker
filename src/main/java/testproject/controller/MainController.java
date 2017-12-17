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
			 status=false;
		 }
		 
		 if(request.getSession().getAttribute("userid")==null)
		 status = mainService.checkCredentials(username,password,request);
		 
		 if(status){
				
				ModelAndView mv=new ModelAndView("index");
			    //mv.addObject("list",status);
			    return mv;
				}
				
			else
				error="Something went Wrong. Please try again latter";
				return new ModelAndView("login","errorMessage",error);
		 
		
	    }
	 	
	    @RequestMapping(value = "/tasklist", method = RequestMethod.GET)
	    public String sayHelloAgain( HttpServletRequest httpServletRequest) {
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
			return ErrorMessages.getErrormsg("1", "Success");
	    	
	    	
	       
	    }
	    
	    //for Searching the task ID
	    @RequestMapping(value ="/searchid", method = RequestMethod.POST)
	    public String searchtask(@RequestBody HashMap<String,String> requestPayload, HttpServletRequest httpServletRequest) {
	    	String resp="";
	    	String id = (String) requestPayload.get("id");
	    	  	
	    	int userid=-1;
	    	if(httpServletRequest.getSession().getAttribute("userid")!=null)
	    	userid =(int) httpServletRequest.getSession().getAttribute("userid");
	    	
	    	if(userid==-1){
	    		
	    		return ErrorMessages.getErrormsg("0", "timeout");
	    		
	    	}
	    	resp = mainService.getTaskById(id,userid);
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
			return ErrorMessages.getErrormsg("1", "Success");
	   
	    }
	    
	    
	    
	    	
}
