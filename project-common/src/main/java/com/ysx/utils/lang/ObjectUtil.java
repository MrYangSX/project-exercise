package com.ysx.utils.lang;

import java.util.Collection;
import java.util.Map;

/**
 * 
 * 
 * 
 * @author yangShiXiong
 * @Data 2020年11月27日
 */
public class ObjectUtil {

	/**
	 * 对象是否为空（==null || "" || size()==0） 目前支持的对象有String, Collection, Object[]
	 * 
	 * @param obj
	 *            判断对象
	 * @return boolean
	 */
	@SuppressWarnings("rawtypes")
	public static boolean isEmpty(Object obj) {

		if (null == obj)
			return true;

		if (obj instanceof String)
			return "".equals((String) obj) || "null".equals(obj);

		if (obj instanceof Object[])
			return ((Object[]) obj).length == 0;

		if (obj instanceof Collection) {
			Collection c = (Collection) obj;
			boolean re = c.isEmpty();
			if (!re && c.size() == 1 && null == c.toArray()[0])
				re = true;
			return re;
		}

		if (obj instanceof Map)
			return ((Map) obj).size() == 0;

		return false;
	}
}
