package testproject.dto.dao;

import java.util.List;

import testproject.dto.entity.Login;
import testproject.dto.entity.TaskCreated;
import testproject.dto.entity.tagTask;


public interface MainDao {

	

	

	int getUserIdIfExist(String username, String password);

	int savetask(TaskCreated taskcreated);

	List<TaskCreated> getTasks(int userid);

	List<tagTask> getTaggedUserById(int userid);

	String getuserNameById(Integer userLoginId);

	List<tagTask> getTaggedJobs(int userid);

	List<Login> getUsersNotWithuserId(int userid);

	List<TaskCreated> getTaskById(String id, int userid);

}
