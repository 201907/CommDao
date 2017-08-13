package com.shenyang.commDao.daoImpl;

import java.sql.SQLException;
import java.sql.Timestamp;

import org.junit.Test;

import com.shenyang.bean.Student;
import com.shenyang.util.BaseDao;


public class StudentDaoImplTest {
	private StudentDaoImpl daoImpl = new StudentDaoImpl();
//	@Test
//	public void test(){
//		daoImpl.queryAll();
//		BaseDao baseDao = BaseDao.getIntance();
//		try {
//			baseDao.closeAllConn();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
	@Test
	public void testInsert(){
		Student stu2 = new Student();
		stu2.setSno(2);
		Student stu1 = new Student();
		stu1.setSno(1);
		Student[]stus = new Student[2];
		stus[0]=stu1;
		stus[1]=stu2;
		System.out.println(daoImpl.delectArray(stus));
	}
}
