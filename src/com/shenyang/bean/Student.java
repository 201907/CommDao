package com.shenyang.bean;

import java.sql.Date;
import java.sql.Timestamp;

public class Student {
	private Integer sid;
	private Integer sno;
	private String name;
	private Timestamp birthday;
	private String addr;
	public Student(Integer sid, Integer sno, String name, Timestamp birthday,
			String addr) {
		super();
		this.sid = sid;
		this.sno = sno;
		this.name = name;
		this.birthday = birthday;
		this.addr = addr;
	}
	public Student() {
		super();
	}
	public Integer getSid() {
		return sid;
	}
	public void setSid(Integer sid) {
		this.sid = sid;
	}
	public Integer getSno() {
		return sno;
	}
	public void setSno(Integer sno) {
		this.sno = sno;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Timestamp getBirthday() {
		return birthday;
	}
	public void setBirthday(Timestamp birthday) {
		this.birthday = birthday;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}

	
}
