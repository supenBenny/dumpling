package org.dumpling.dao.hibernate.usertype;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.type.StringType;
import org.hibernate.usertype.UserType;

public class MapType implements UserType {

	 /** 
     * 修改类型对应的SQL类型 
     * 使用VARCHAR 
     */  
	@Override
	public int[] sqlTypes() {
		 return new int[] { StringType.INSTANCE.sqlType()};
	}

	 /** 
     * 修改类型对应的java类型 
     * 我们这边使用LinkedHashMap类型 
     */  
	@SuppressWarnings("rawtypes")
	@Override
	public Class returnedClass() {
		return LinkedHashMap.class;
	}

	 /** 
     * 自定义数据类型比对方法 
     * 用作脏数据检查，X,Y为两个副本 
     */  
	@Override
	public boolean equals(Object x, Object y) throws HibernateException {
		// TODO Auto-generated method stub
		return false;
	}

	 /** 
     * 返回给定类型的hashCode 
     */ 
	@Override
	public int hashCode(Object x) throws HibernateException {
		// TODO Auto-generated method stub
		return 0;
	}

	/** 
     * 读取数据转换为自定义类型返回 
     * names包含了自定义类型的映射字段名称 
     */  
	@Override
	public Object nullSafeGet(ResultSet rs, String[] names,
			SessionImplementor session, Object owner)
			throws HibernateException, SQLException {
		 assert names.length == 1;
		//取得字段名称并查询  
        String value= (String) StringType.INSTANCE.get(rs,names[0], session);  
        if (value!=null) {  
            return parse(value);  
        }else {  
            return null;  
        }  
	//	return null;
//        String json = rs.getString(names[0]);  
//        if(json == null || json.trim().length() == 0) {  
//            return new JsonList();  
//        }  
//        return JSONArray.toList(JSONArray.fromObject(json), JsonList.class); 
	}

	 /** 
     * 数据保存时被调用 
     */  
	@Override
	public void nullSafeSet(PreparedStatement st, Object value, int index,
			SessionImplementor session) throws HibernateException, SQLException {
		// TODO Auto-generated method stub
//		 if(value == null) {  
//			 st.setNull(index, Hibernate.STRING.sqlType());  
//	        } else {  
//	        	st.setString(index, JSONArray.fromObject(value).toString());  
//	        }  
		
	}

	/** 
     * 自定义类型的完全复制方法,构造返回对象 
     *    1. 当nullSafeGet方法调用之后，我们获得了自定义数据对象，在向用户返回自定义数据之前 
     * deepCopy方法被调用，它将根据自定义数据对象构造一个完全拷贝，把拷贝返还给客户使用。 
     *    2.此时我们就得到了自定义数据对象的两个版本 
     *     原始版本由hibernate维护，用作脏数据检查依据，复制版本由用户使用，hibernate将在 
     * 脏数据检查过程中比较这两个版本的数据。 
     *  
     *  
     */  
	@Override
	public Object deepCopy(Object value) throws HibernateException {
		// TODO Auto-generated method stub
//		if(o == null) return null;  
//        JsonList jsonList = new JsonList();  
//        jsonList.addAll((List)o);  
//        return jsonList; 
		return null;
	}

	/** 
     * 表示本类型实例是否可变 
     */  
	@Override
	public boolean isMutable() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * 序列化
	 */
	@Override
	public Serializable disassemble(Object value) throws HibernateException {
		// return ((Serializable)value);  
		return null;
	}

	@Override
	public Object assemble(Serializable cached, Object owner)
			throws HibernateException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object replace(Object original, Object target, Object owner)
			throws HibernateException {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("rawtypes")
	private LinkedHashMap parse(String json){
		return null;
	}
}
