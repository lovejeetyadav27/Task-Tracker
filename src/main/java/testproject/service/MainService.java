package testproject.service;


import javax.servlet.http.HttpServletRequest;

public interface MainService {

	String getTaskDetails(int userid);


	Boolean checkCredentials(String username, String password, HttpServletRequest request);


	String savetask(String name, String fromdate, String todate, String description, int userid);

	String getTaggedUsers(int userid);


	String getTaskById(String id, int userid);


	String getTaggedjobs(int userid);

}
