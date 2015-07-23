package org.dumpling.lang;

import java.io.Closeable;
import java.io.IOException;
import java.lang.reflect.Array;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 常用方法
 * @author Administrator
 *
 */
public class L {
	
	public final static String EMPTY = Strings.EMPTY;
	
	/**
	 * 判断字符串是否有值，非空串
	 * @param cs
	 * @return
	 */
	public static boolean hasText(CharSequence cs) {
		return Strings.isNotBlank(cs);
	}
	/**
	 * Return <code>true</code> if the supplied Collection is <code>null</code>
	 * or empty. Otherwise, return <code>false</code>.
	 * @param collection the Collection to check
	 * @return whether the given Collection is empty
	 */
	@SuppressWarnings("rawtypes")
	public static boolean isEmpty(Collection collection) {
		return (collection == null || collection.isEmpty());
	}
	
	@SuppressWarnings("rawtypes")
	public static boolean notEmpty(Collection collection) {
		return !(isEmpty(collection));
	}
	/**
	 * Return <code>true</code> if the supplied Map is <code>null</code>
	 * or empty. Otherwise, return <code>false</code>.
	 * @param map the Map to check
	 * @return whether the given Map is empty
	 */
	@SuppressWarnings("rawtypes")
	public static boolean isEmpty(Map map) {
		return (map == null || map.isEmpty());
	}
	
	@SuppressWarnings("rawtypes")
	public static boolean notEmpty(Map map) {
		return !isEmpty(map);
	}
	
	/**
	 * 判断数组是否为空
	 * return <code>true</code> if ary == null || ary.length == 0
	 * @param ary
	 * @return
	 */
	public static <T> boolean isEmpty(T[] ary) {
		return null == ary || ary.length == 0;
	}
	
	/**
	 * 判断数组不为空
	 * return <code>true</code> if ary.length > 0
	 * @param ary
	 * @return
	 */
	public static <T> boolean notEmpty(T[] ary) {
		return !isEmpty(ary);
	}
	
	/**
	 * 判断数对象是否为空
	 * return <code>true</code> if obj == null
	 * @param ary
	 * @return
	 */
	public static  boolean isNULL(Object obj) {
		return obj == null;
	}
	
	/**
	 * 判断数对象是否不为空
	 * return <code>true</code> if obj == null
	 * @param ary
	 * @return
	 */
	public static  boolean notNULL(Object obj) {
		return !(isNULL(obj));
	}

	/**
	 * 判断一个对象是否为空。它支持如下对象类型：
	 * <ul>
	 * <li>null : 一定为空
	 * <li>数组
	 * <li>集合
	 * <li>Map
	 * <li>其他对象 : 一定不为空
	 * </ul>
	 * 
	 * @param obj
	 *            任意对象
	 * @return 是否为空
	 */
	public static boolean isEmpty(Object obj) {
		if (obj == null)
			return true;
		if (obj.getClass().isArray())
			return Array.getLength(obj) == 0;
		if (obj instanceof Collection<?>)
			return ((Collection<?>) obj).isEmpty();
		if (obj instanceof Map<?, ?>)
			return ((Map<?, ?>) obj).isEmpty();
		return false;
	}

	
	/**
	 * @see org.apache.commons.lang3.StringUtils#isEmpty(CharSequence)
	 * @param cs
	 * @return
	 */
	public static boolean isEmpty(CharSequence cs) {
		return Strings.isEmpty(cs);
	}
	
	/**
	 * Return "" if str==null ,Otherwise return str 
	 * @param str
	 * @return
	 */
	public static String nullToEmpty(String str) {
		if(str == null) {
			return Strings.EMPTY;
		}
		return str;
	}
	
	@SuppressWarnings("unchecked")
	public static <T>List<T> nullToEmpty(List<T> list) {
		if(list == null) {
			return Collections.EMPTY_LIST;
		}
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public static <T>Set<T> nullToEmpty(Set<T> set) {
		if(set == null) {
			return Collections.EMPTY_SET;
		}
		return set;
	}
	
	@SuppressWarnings("unchecked")
	public static <K,V>Map<K,V> nullToEmpty(Map<K,V> map) {
		if(map == null) {
			return Collections.EMPTY_MAP;
		}
		return map;
	}
	
	/**
	 * if(t == null) { return def } else return t; 
	 * @param t
	 * @param def
	 * @return
	 */
	@SuppressWarnings("unused")
	private static <T> T nullToDefault(T t,T def){
		if(null == t) {
			return def;
		}
		return t;
	}
	
	/**
	 * 为空指针对象设置默认值
	 * if(t == null) { return def } else return t; 
	 * @param t
	 * @param def
	 * @return
	 */
	public static <T> T def(T t,T def){
		if(null == t) {
			return def;
		}
		return t;
	}
	
	/**
	 * 关闭一个可关闭对象，可以接受 null。如果成功关闭，返回 true，发生异常 返回 false
	 * @param cb
	 *            可关闭对象
	 * @return 是否成功关闭
	 */
	public static boolean close(Closeable cb) {
		if (null != cb) {
			try {
				cb.close();
			} catch (IOException e) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 替代默认println,方便生产环境去除System.out.println
	 * @param msg
	 */
	public static void P(Object msg) {
		boolean isDebug = true;
		if (isDebug) {
			if (msg == null) {
				System.out.println("null");
			} else if (msg.getClass().isArray()) {
				System.out.println(Arrays.toString((Object[]) msg));

			} else {
				System.out.println(msg.toString());
			}
			// if (msg instanceof Collection<?>){
			// System.out.println(Strings.join(((Collection<?>)msg).iterator(),
			// ','));
			// }
			// if (msg instanceof Map<?, ?>){
			// System.out.println(msg.toString());
			// }
		}
	}
	
	/**
	 * 替代默认println,方便生产环境去除System.out.println
	 * @param msg  "hi,{0}"
	 * @param args xx
	 */
	public static void P(String msg,Object... args) {
		if(args == null || args.length == 0) {
			P(msg);
		}else {
			P(MessageFormat.format(msg, args));
		}
	}
	
	/**
	 * 较方便的创建一个数组，比如：
	 * 
	 * <pre>
	 * Pet[] pets = L.array(pet1, pet2, pet3);
	 * </pre>
	 * 
	 * @param eles
	 *            可变参数
	 * @return 数组对象
	 */
	@SafeVarargs
	public static <T> T[] array(T... eles) {
		return eles;
	}
	
	/**
	 * 较方便的创建一个list，比如：
	 * 
	 * <pre>
	 * List<Pet> pets = L.list(pet1, pet2, pet3);
	 * </pre>
	 * 
	 * @param eles
	 *            可变参数
	 * @return ArrayList
	 */
	@SafeVarargs
	public static <T> List<T> list(T... eles) {
		if(eles == null || eles.length == 0){
			return new ArrayList<T>();
		}
		return Arrays.asList(eles);
	}
	
	/**
     * 创建一个<code>HashMap</code>
     * @param args
     * @return
     */
    public static <K,V> Map<K, V> map(Object... args) {
    	return CollectionUtil.createHashMap(args);
    }
}
