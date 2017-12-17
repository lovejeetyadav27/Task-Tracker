package testproject.service.impl;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import testproject.dto.dao.MainDao;
import testproject.dto.entity.Login;
import testproject.dto.entity.TaskCreated;
import testproject.dto.entity.tagTask;
import testproject.service.MainService;


@Service
public class MainServiceImpl implements MainService{

	
	@Autowired
	MainDao mainDao;
	
	@Override
	public Boolean checkCredentials(String username, String password,HttpServletRequest request) {
		
		int loginId = mainDao.getUserIdIfExist(username,password);
		
		request.getSession().setAttribute("userid", loginId);
		
		System.out.println("UserId is "+loginId);
		
		if(loginId==0){
			return false;
		}
		
		return true;
	}
	
	@Override
	public String getTaskDetails(int userid) {
		JSONObject json= new JSONObject();
		
		List<TaskCreated> listOfTask = mainDao.getTasks(userid);
		List<HashMap<String,Object>> listofobect = new LinkedList<HashMap<String,Object>>();
		for(TaskCreated loo : listOfTask){
		    HashMap<String,Object> object = new HashMap<String,Object>();
		    object.put("id", loo.getId());
		    object.put("name", loo.getTaskName());
		    object.put("fromdate", loo.getStartDate());
		    object.put("todate", loo.getEndDate());
		    object.put("description", loo.getDescription());
		    
		    listofobect.add(object);
			
			
		}
		json.put("listofitem", listofobect);
		json.put("status", 1);
		
		return json.toString();
	}

	@Override
	public String getTaggedUsers(int userid) {
		
		String data;
		JSONObject json= new JSONObject();
		
		List<tagTask> listOfTAGTask = mainDao.getTaggedUserById(userid);
		
		List<HashMap<String,Object>> listofobect = new LinkedList<HashMap<String,Object>>();
		for(tagTask loo : listOfTAGTask){
		    HashMap<String,Object> object = new HashMap<String,Object>();
		    object.put("taskid", loo.getTaskcreatedId());
		    data = mainDao.getuserNameById(loo.getUserLoginId());
		    
		    object.put("name", data);
		    
		    listofobect.add(object);
			
		}
		
		List<Login> totalusers = mainDao.getUsersNotWithuserId(userid);
		List<HashMap<String,Object>> listofusers = new LinkedList<HashMap<String,Object>>();
		for(Login login : totalusers){
			 HashMap<String,Object> object = new HashMap<String,Object>();
			    object.put("taskid", login.getId());
			    
			    object.put("name", login.getUserName());
			    
			    listofusers.add(object);
		}
		
		
		
		json.put("taggedItems", listofobect);
		json.put("taggedjobs", listofusers);
		json.put("status", 1);
		
		return json.toString();
		

	}

	@Override
	public String savetask(String name, String fromdate, String todate, String description,int userid) {

		TaskCreated taskcreated = new TaskCreated();
		taskcreated.setTaskName(name);
		taskcreated.setDescription(description);
		taskcreated.setEndDate(todate);
		taskcreated.setStartDate(fromdate);
		taskcreated.setLoginId(userid);
		
		
		int status = mainDao.savetask(taskcreated);
 		
		return null;
	}

	@Override
	public String getTaskById(String id, int userid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getTaggedjobs(int userid) {
		
		String data;
		JSONObject json= new JSONObject();
		
		List<tagTask> listOfTAGTask = mainDao.getTaggedJobs(userid);
		
		data = mainDao.getuserNameById(userid);
		
		List<HashMap<String,Object>> listofobect = new LinkedList<HashMap<String,Object>>();
		for(tagTask loo : listOfTAGTask){
		    HashMap<String,Object> object = new HashMap<String,Object>();
		    object.put("id", loo.getTaskcreatedId());
		    
		    
		    object.put("name", data);
		    
		    listofobect.add(object);
			
		}
		json.put("taggedjobs", listofobect);
		json.put("status", 1);
		
		return json.toString();
		

	}



	

}
