package com.briup.utils;

import com.briup.exception.ServiceException;
import com.briup.response.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class BeanCopyUtils {

	/**
	 * @param source      源对象
	 * @param targetClass 拷贝的目标类型
	 * @param <T>         泛型
	 * @return 目标类的对象
	 */
	public static <T> T copyBean(Object source, Class<T> targetClass) {
		T result = null;
		try {
			//创建目标类对象
			result = targetClass.newInstance();
			//使用Spring中的 BeanUtils工具类进行bean拷贝
			BeanUtils.copyProperties(source, result);
		} catch (Exception e) {
			log.error("拷贝失败！错误信息：{}", e.getMessage());
			throw new ServiceException(ResultCode.SYSTEM_INNER_ERROR);
		}
		return result;
	}

	/**
	 * list拷贝
	 *
	 * @param list        源数据所在的集合
	 * @param targetClass 拷贝的目标类型
	 * @param <O>         源数据的类型
	 * @param <V>         目标类型
	 * @return 拷贝后将数据存放于集合返回
	 */
	public static <O, V> List<V> copyBeanList(List<O> list, Class<V> targetClass) {
		return list.stream()
				.map(o -> copyBean(o, targetClass))
				.collect(Collectors.toList());
	}
}
