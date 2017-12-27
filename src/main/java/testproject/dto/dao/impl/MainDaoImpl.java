package testproject.dto.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import testproject.dto.dao.MainDao;
import testproject.dto.entity.Login;
import testproject.dto.entity.TaskCreated;
import testproject.dto.entity.tagTask;
import testproject.threadlocal.HibernateUtils;

@Repository
public class MainDaoImpl implements MainDao {

	@Autowired
	@Qualifier("first")
	SessionFactory sessionFactory;

	Session session = null;
	
	@Autowired
	HibernateUtils hibernateUtils;
	
	@Override
	public int getUserIdIfExist(String username, String password) {
		
		
		Transaction transaction=null;
		      
			try {
				session = hibernateUtils.OpenSession();
				 transaction = session.beginTransaction();
				
				Criteria c = session.createCriteria(Login.class)
			            .add(Restrictions.eq("userName", username))
			            .add(Restrictions.eq("password", password))
			            ;
				
				Login status=(Login) c.uniqueResult();
				
				if(status==null)
				{
					status=new Login();
					status.setPassword(password);
					status.setUserName(username);
					int id=(int) session.save(status);
					status.setId(id);
				}
				transaction.commit();
				
				return status.getId();

			} catch (Exception ex) {
				try {
					
					transaction.rollback();
				} catch (RuntimeException re) {
					System.out.println("Couldn't roll back transaction" + re);
				}
				return 0;

			} finally {
				if (session != null) {
//					hibernateUtils.CloseConnection();
					System.out.println("session not closed. finally is running.");
			}
		}
	}
	

	@SuppressWarnings("unchecked")
	@Override
	public List<TaskCreated> getTasks(int userid) {

		Transaction transaction=null;
		List<TaskCreated> status = null;
		try {
			session = hibernateUtils.OpenSession();
		transaction = session.beginTransaction();
		Criteria c = session.createCriteria(TaskCreated.class)
		   .add(Restrictions.eq("loginId", userid)) 
		;

		status = c.list();
		transaction.commit();
		
		
		return status;
		}catch (Exception ex) {
			ex.printStackTrace();
			try {
				transaction.rollback();
			} catch (RuntimeException re) {
				System.out.println("Couldn�t roll back transaction" + re);
			}
			return status;

		} finally {
			if (session != null) {
//				hibernateUtils.CloseConnection();
				System.out.println("session is closed.");
		}
	}
		
	}
	
	
	
	//To search a single task with id for a user
	
	
	@Override
	public List<TaskCreated> getTaskById(String id, int userid) {

		Transaction transaction=null;
		List<TaskCreated> status = null;
		try {
			session = hibernateUtils.OpenSession();
		transaction = session.beginTransaction();
		Criteria c = session.createCriteria(TaskCreated.class)
		   .add(Restrictions.eq("loginId", userid)) 
		   .add(Restrictions.eq("id", Integer.parseInt(id))) 
		;

		status = c.list();
		transaction.commit();
		
		
		return status;
		}catch (Exception ex) {
			ex.printStackTrace();
			try {
				transaction.rollback();
			} catch (RuntimeException re) {
				System.out.println("Couldn�t roll back transaction" + re);
			}
			return status;

		} finally {
			if (session != null) {
				hibernateUtils.CloseConnection();
				System.out.println("session is closed.");
		}
	}
		
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<tagTask> getTaggedUserById(int userid) {
		
		List<tagTask> status=null;
		Transaction transaction=null;
		try{
			
			session = hibernateUtils.OpenSession();
		transaction = session.beginTransaction();
		Criteria c = session.createCriteria(tagTask.class)
		   .add(Restrictions.eq("masterLoginId", userid)) 
		;

		status= c.list();
		if (status.size() != 0) {
			int resp = status.get(0).getId();
			System.out.println(resp);	
		}
		
		transaction.commit();
		
		return status;
		}catch (Exception ex) {
			ex.printStackTrace();
			try {
				transaction.rollback();
			} catch (RuntimeException re) {
				System.out.println("Couldn�t roll back transaction" + re);
			}
			return status;

		} finally {
			if (session != null) {
//				hibernateUtils.CloseConnection();
				System.out.println("session is closed.");
		}
	}
	}



	@SuppressWarnings("unchecked")
	@Override
	public String getuserNameById(Integer userLoginId) {
		List<Login> status=null;
		Transaction transaction=null;
		try{
			session = hibernateUtils.OpenSession();
		transaction = session.beginTransaction();
		Criteria c = session.createCriteria(Login.class)
		   .add(Restrictions.eq("id", userLoginId)) 
		;

		status = c.list();
		if (status.size() != 0) {
			int resp = status.get(0).getId();
			System.out.println(resp);
			transaction.commit();
			return status.get(0).getUserName();

		}
		
			transaction.commit();
		
		return "";
		}catch (Exception ex) {
			ex.printStackTrace();
			try {
				transaction.rollback();
			} catch (RuntimeException re) {
				System.out.println("Couldn�t roll back transaction" + re);
			}
			return "";

		} finally {
			if (session != null) {
//				hibernateUtils.CloseConnection();
				System.out.println("session is closed.");
		}
	}
	
		
	}



	@Override
	public int savetask(TaskCreated taskcreated) {
		
		Transaction transaction=null;
		try {
			session = hibernateUtils.OpenSession();
			transaction = session.beginTransaction();
				
			int id=(int) session.save(taskcreated);
				
			
				transaction.commit();
				
			
			return id;

		} catch (Exception ex) {
			try {
				
				transaction.rollback();
			} catch (RuntimeException re) {
				System.out.println("Couldn't roll back transaction" + re);
			}
			return -1;

		} finally {
			if (session != null) {
//				hibernateUtils.CloseConnection();
				System.out.println("session is closed.");
		}
	}
}

	

	@SuppressWarnings("unchecked")
	@Override
	public List<tagTask> getTaggedJobs(int userid) {
		
		Transaction transaction=null;
		List<tagTask> status=null;
		try{
			
			session = hibernateUtils.OpenSession();
		transaction = session.beginTransaction();
		Criteria c = session.createCriteria(tagTask.class)
		   .add(Restrictions.eq("userLoginId", userid)) 
		;

		status= c.list();
		
		transaction.commit();
		
		return status;
		}catch (Exception ex) {
			ex.printStackTrace();
			try {
				transaction.rollback();
			} catch (RuntimeException re) {
				System.out.println("Couldn�t roll back transaction" + re);
			}
			return status;

		} finally {
			if (session != null) {
//				hibernateUtils.CloseConnection();
				System.out.println("session is closed.");
		}
	}
	}



	@Override
	public List<Login> getUsersNotWithuserId(int userid) {
		
		Transaction transaction=null;
		List<Login> status=null;
		try{
			
			session = hibernateUtils.OpenSession();
		transaction = session.beginTransaction();
		Criteria c = session.createCriteria(Login.class)
		   .add(Restrictions.ne("id", userid)) 
		; 

		status= c.list();
		/*if (status.size() != 0) {
			int resp = status.get(0).getId();
			System.out.println(resp);
		}*/
		
		transaction.commit();
		return status;
		}catch (Exception ex) {
			ex.printStackTrace();
			try {
				transaction.rollback();
			} catch (RuntimeException re) {
				System.out.println("Couldn�t roll back transaction" + re);
			}
			return status;

		} finally {
			if (session != null) {
//				hibernateUtils.CloseConnection();
				System.out.println("session is closed.");
		}
	}
	}


	

}
