package org.shenlei.footballhub.utils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.List;

import lombok.extern.slf4j.Slf4j;
import trueman.common.utils.conver.annotation.NotConver;

import com.google.common.collect.Lists;

@Slf4j
public class ClassUtils {

	public static List<String> getConvertFields(Class cls){
		List<String> list = Lists.newArrayList();
		try {
			Field[] fields = cls.getDeclaredFields();
			for (Field field : fields)
			if ((!Modifier.isStatic(field.getModifiers())) && !field.isAnnotationPresent(NotConver.class)){
				PropertyDescriptor property = new PropertyDescriptor(field.getName(), cls);
				String key = property.getName();
				if (!key.equals("class")){
					list.add(key);
				}
			}
		} catch (Exception e) {
			log.error("ClassUtils->getConvertFields Error ", e);
		}
		return list;
	}
}
