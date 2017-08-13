package com.shenyang.commDao.daoImpl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.shenyang.commDao.dao.CommDao;
import com.shenyang.util.BaseDao;
import com.shenyang.util.ReflectUtils;
import com.shenyang.util.StringUtils;

/**
 * 通用Dao接口的实现类
 * @author Administrator
 *
 * @param <T>
 */
public class commDaoImpl<T> implements CommDao<T>{
	private Class<T> cls;
	private BaseDao baseDao = BaseDao.getIntance();
	public commDaoImpl() {
		ParameterizedType type = (ParameterizedType)this.getClass().getGenericSuperclass();
		cls = (Class<T>)type.getActualTypeArguments()[0];
	}
	/**
	 * 删除指定的数据列表
	 */
	@SuppressWarnings("null")
	public boolean delectArray(T[] t) {
		Connection conn=null;
		try {
			conn = baseDao.getConn();
			for(T temp : t){
				if(!delete(temp, conn)){
					conn.rollback();
					return false;
				}
			}
		} catch (SQLException e) {
			try {
				conn.rollback();
				return false;
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		return true;
	}
	/**
	 * 删除指定的数据，通过传递conn来实现事务控制
	 */
	public boolean delete(T t,Connection conn) {
		Method[] methods = cls.getMethods();
		PreparedStatement ps = null;
		StringBuilder sbSql = new StringBuilder();
		sbSql.append("delete from ");
		sbSql.append(cls.getSimpleName()+" where ");
		List values = new ArrayList();
		try {
			for(Method m : methods){
				if(!m.getName().startsWith("get")||m.getName()=="getClass"){
					continue;
				}
				Object value;
				value = ReflectUtils.invokeGetMethod(t, m);
				if(value==null||value==""){
					continue;
				}
				values.add(value);
				sbSql.append(StringUtils.getMethodName2ColumnName(m.getName())+"=? and ");
			}
			String sql =sbSql.toString();
			sql = sql.substring(0,sql.lastIndexOf("and"));
			conn = baseDao.getConn();
			ps = conn.prepareStatement(sql);
			for(int i=1;i<=values.size();i++){
				ps.setObject(i, values.get(i-1));
			}
			ps.execute();
			return true;
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			baseDao.close(conn, null, ps);
		}
		return false;
	}
	/**
	 * 删除指定的数据
	 */
	public boolean delete(T t) {
		Method[] methods = cls.getMethods();
		Connection conn = null;
		PreparedStatement ps = null;
		StringBuilder sbSql = new StringBuilder();
		sbSql.append("delete from ");
		sbSql.append(cls.getSimpleName()+" where ");
		List values = new ArrayList();
		try {
			for(Method m : methods){
				if(!m.getName().startsWith("get")||m.getName()=="getClass"){
					continue;
				}
				Object value;
				value = ReflectUtils.invokeGetMethod(t, m);
				if(value==null||value==""){
					continue;
				}
				values.add(value);
				sbSql.append(StringUtils.getMethodName2ColumnName(m.getName())+"=? and ");
			}
			String sql =sbSql.toString();
			sql = sql.substring(0,sql.lastIndexOf("and"));
			conn = baseDao.getConn();
			ps = conn.prepareStatement(sql);
			for(int i=1;i<=values.size();i++){
				ps.setObject(i, values.get(i-1));
			}
			ps.execute();
			return true;
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			baseDao.close(conn, null, ps);
		}
		return false;
	}
	/**
	 * 添加指定的数据
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 */
	public boolean insert(T t)  {
		Method[]methods = cls.getMethods();
		PreparedStatement ps = null;
		Connection conn = null;
		List values = new ArrayList();
		StringBuilder sbSql1 = new StringBuilder();
		StringBuilder sbSql2 = new StringBuilder();
		sbSql1.append("insert into "+cls.getSimpleName()+" (");
		try {
			for(Method m : methods){
				if(!m.getName().startsWith("get")||m.getName()=="getClass"){
					continue;
				}
				sbSql1.append(StringUtils.getMethodName2ColumnName(m.getName())+",");
			}
			String sql1 =  sbSql1.substring(0,sbSql1.lastIndexOf(","))+") values (";
			for(Method m : methods){
				if(!m.getName().startsWith("get")||m.getName()=="getClass"){
					continue;
				}
				Object value = ReflectUtils.invokeGetMethod(t, m);
				values.add(value);
				sbSql2.append("?,");
			}
			String sql2 = sbSql2.substring(0, sbSql2.lastIndexOf("?,")+1)+")";
			String sql = sql1+sql2;
			conn = baseDao.getConn();
			ps = conn.prepareStatement(sql);
			for(int i=1;i<=values.size();i++){
				ps.setObject(i, values.get(i-1));
			}
			ps.execute();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}finally{
			baseDao.close(conn, null, ps);
		}
		return false;
	}
	/**
	 * 添加指定的数据列表
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 */
	public boolean insertArray(T[] t){
		Connection conn = null;
		PreparedStatement ps =null;
		try {
			conn = baseDao.getConn();
			conn.setAutoCommit(false);
			ps = null;
			Method[]methods = cls.getMethods();
			StringBuilder sbSql1 = new StringBuilder();
			StringBuilder sbSql2 = new StringBuilder();
			sbSql1.append("insert into "+cls.getSimpleName()+" (");
			for(Method m : methods){
				if(!m.getName().startsWith("get")||m.getName()=="getClass"){
					continue;
				}
				sbSql1.append(StringUtils.getMethodName2ColumnName(m.getName())+",");
			}
			String sql1 =  sbSql1.substring(0,sbSql1.lastIndexOf(","))+") values (";
			for(Method m : methods){
				if(!m.getName().startsWith("get")||m.getName()=="getClass"){
					continue;
				}
				sbSql2.append("?,");
			}
			String sql2 = sbSql2.substring(0, sbSql2.lastIndexOf("?,")+1)+")";
			String sql = sql1+sql2;
			ps = conn.prepareStatement(sql);
			for(T temp: t){
				int count = 1;
				for(int i=1;i<=methods.length;i++){
					if(!methods[i-1].getName().startsWith("get")||methods[i-1].getName()=="getClass"){
						continue;
					}
					Object value = ReflectUtils.invokeGetMethod(temp, methods[i-1]);
					ps.setObject(count, value);
					count++;
				}
				ps.addBatch();
			}
			ps.executeBatch();
			conn.commit();
			
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			return false;
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			return false;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			return false;
		} catch (InvocationTargetException e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			return false;
		} finally{
			try {
				conn.setAutoCommit(true);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			baseDao.close(conn, null, ps);
		}
		return true;
	}
	/**
	 * 查询所有的数据
	 */
	public ArrayList<T> queryAll() {
		List<T> list = null;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		//拼接SQL
		StringBuilder sbSql = new StringBuilder();
		sbSql.append("select * from ").append(cls.getSimpleName());
		try {
			conn = baseDao.getConn();
			ps = conn.prepareStatement(sbSql.toString().trim());
			rs = ps.executeQuery();
			while(rs.next()){
				if(list==null)list = new ArrayList<T>();
				T temp = cls.newInstance();
				for(Method method:cls.getMethods()){
					if(!method.getName().startsWith("set")){
						continue;
					}		
					Object value = rs.getObject(StringUtils.setMethodName2ColumnName(method.getName()));
					ReflectUtils.invokeSetMethod(temp, method, value);
				}
				list.add(temp);
			}
			return (ArrayList<T>) list;
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}finally{
			baseDao.close(conn, rs, ps);
		}
		
		return null;
	}

}
