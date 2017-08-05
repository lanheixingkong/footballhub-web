package org.shenlei.footballhub.utils;

import java.util.List;
import java.util.Map;

import com.google.common.collect.Maps;

public class MapUtils {

	public static Map<String, Object> filterFields(Map<String, Object> ori,
			List<String> fields) {
		
		Map<String, Object> ret = Maps.newHashMap();
		
		if(ori != null && fields != null){
			fields.forEach(field -> {
				ret.put(field, ori.get(field));
			});
		}
		
		return ret;
	}
	
	public static Map<String, Object> filterFields(Map<String, Object> ori,
			Class cls) {
		return filterFields(ori, ClassUtils.getConvertFields(cls));
	}
}
