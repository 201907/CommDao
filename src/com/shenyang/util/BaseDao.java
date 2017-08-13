package com.shenyang.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.Properties;

public class BaseDao {
	//配置信息
	private Properties DBconfig;
	public static class InnerBaseDao{
		public static BaseDao baseDao = new BaseDao();
	}
	/**
	 * 获得实例
	 * @return
	 */
	public static BaseDao getIntance(){
		return InnerBaseDao.baseDao;
	}
	/**
	 * 获得连接
	 * @return
	 * @throws SQLException
	 */
	public Connection getConn() throws SQLException{
		return DriverManager.getConnection(DBconfig.getProperty("url"),DBconfig.getProperty("userName"),DBconfig.getProperty("pwd"));
	}
	public BaseDao() {
		super();
		if(InnerBaseDao.baseDao!=null){
			throw new UnsupportedOperationException("只能创建一个实例");
		}
		try {
			DBconfig = new Properties();
			DBconfig.load(new FileInputStream("DBconfig.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	/**
	 * 关闭
	 * @param conn
	 * @param rs
	 * @param stmt
	 */
	public void close(Connection conn,ResultSet rs,Statement stmt){
		if(rs!=null){
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(stmt!=null){
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(conn!=null){
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
