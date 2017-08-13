package com.shenyang.commDao.dao;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

/**
 * 通用Dao接口
 * @author Administrator
 *
 * @param <T>
 */
public interface CommDao<T> {
	boolean insert(T t);
	boolean insertArray(T[] t);
	boolean delete(T t);
	boolean delectArray(T[] t);
	ArrayList<T> queryAll();
}
