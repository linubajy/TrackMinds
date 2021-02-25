package com.example.Campus.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="Mind")
public class Mind
{
	@Id
	@Column(name="id")
	private int id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="tname")
	private String tname;
	
	@Column(name="role")
	private String role;
	
	@Column(name="phone")
	private String phone;

	
	
	//private Track track;
	
	public Mind() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Mind(int id, String name, String tname, String role, String phone) {
		super();
		this.id = id;
		this.name = name;
		this.tname = tname;
		this.role = role;
		this.phone = phone;
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTname() {
		return tname;
	}

	public void setTname(String tname) {
		this.tname = tname;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	
	
	
	
	
	
}
