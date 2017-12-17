package testproject.dto.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tag_task")
public class tagTask implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6639759202631573163L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "user_login_id")
	private Integer userLoginId;
	
	@Column(name = "master_login_id")
	private Integer masterLoginId;
	
	@Column(name = "task_created_id")
	private Integer taskcreatedId;
	
	@Column(name = "tagged_date")
	private Date taggedDate =new Date();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Integer getUserLoginId() {
		return userLoginId;
	}

	public void setUserLoginId(Integer userLoginId) {
		this.userLoginId = userLoginId;
	}

	public Integer getMasterLoginId() {
		return masterLoginId;
	}

	public void setMasterLoginId(Integer masterLoginId) {
		this.masterLoginId = masterLoginId;
	}

	public Integer getTaskcreatedId() {
		return taskcreatedId;
	}

	public void setTaskcreatedId(Integer taskcreatedId) {
		this.taskcreatedId = taskcreatedId;
	}

	public Date getTaggedDate() {
		return taggedDate;
	}

	public void setTaggedDate(Date taggedDate) {
		this.taggedDate = taggedDate;
	}
	
	

}
