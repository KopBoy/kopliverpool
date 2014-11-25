package com.kopliverpool.framework.hibernate.util;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.springframework.util.Assert;

import com.kopliverpool.framework.core.util.ModelUtil;

/** 
 *
 * Description: TODO 注释
 *
 * @author KOP
 * @version 1.0
 * <pre>
 * Modification History: 
 * Date         Author      Version     Description 
 * ------------------------------------------------------------------ 
 * 2014年11月13日	 KOP          1.0       1.0 Version 
 * </pre>
 */
public class HibernateUtil {
	
	protected static Log log = LogFactory.getLog(HibernateUtil.class);

	/**
	 * 添加相等型条件，并返回拼接好的sql条件语句
	 * @param paramMap
	 * @param bean
	 * @param propNames
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 */
	public static String putParamsEqual(Map<String,Object> paramMap, Object bean, String... propNames) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		if(null==paramMap || null==bean || null==propNames) return "";
		
		String logic = "and";
		StringBuffer rtnSb = new StringBuffer();
		for(String propName : propNames){
			Object value = ModelUtil.getPropertyValue(bean, propName);
			boolean flag = false;
			if(null!=value){
				if(value instanceof String){//字符串还需判断空白
					if(((String) value).trim().length()>0){
						flag = true;
					}
				}else{//非字符串仅判断非空
					flag = true;
				}
				if(flag){
					String propNameSec = replaceResrvChars(propName);//过滤敏感字符
					rtnSb.append(" ").append(logic).append(" ").append(propName).append("=:").append(propNameSec);
					paramMap.put(propNameSec, value);
				}
			}
		}
		
		return rtnSb.toString();
	}
	
	/**
	 * 
	 * Description: 添加相等型条件，并返回拼接好的sql条件语句
	 *
	 * @param 
	 * @return String
	 * @throws 
	 * @Author KOP
	 * Create Date: 2012-5-3 下午2:23:22
	 */
	public static String putParamsEqual(Map<String,Object> paramMap, String[] propNames, Object[] propValues) {
		if(null==paramMap || null==propValues || null==propNames) return "";
		if(propNames.length!=propValues.length) throw new RuntimeException("参数名、值数据不一致");
		
		String logic = "and";
		StringBuffer rtnSb = new StringBuffer();
		for(int i=0; i<propNames.length; i++){
			String propName = propNames[i];
			Object value = propValues[i];
			boolean flag = false;
			if(null!=value){
				if(value instanceof String){//字符串还需判断空白
					if(((String) value).trim().length()>0){
						flag = true;
					}
				}else{//非字符串仅判断非空
					flag = true;
				}
				if(flag){
					String propNameSec = replaceResrvChars(propName);//过滤敏感字符
					rtnSb.append(" ").append(logic).append(" ").append(propName).append("=:").append(propNameSec);
					paramMap.put(propNameSec, value);
				}
			}
		}
		
		return rtnSb.toString();
	}
	/**
	 * 针对某一多选参数添加条件，并返回sql子串
	 * @param paramMap
	 * @param logic
	 * @param propName
	 * @param values
	 * @return
	 */
	public static String putParamIn(Map<String,Object> paramMap, String propName, String values){
		log.debug("value="+values);
		if(null==values || values.trim().length()==0) return "";
		
		values = values.replaceAll("，", ",");
		Object[] valAry = values.split(",");
		return putParamIn(paramMap, propName, valAry);
	}
	/**
	 * 针对某一多选参数添加条件，并返回sql子串，结果按原参数的位置排序
	 * @param paramMap
	 * @param logic
	 * @param propName
	 * @param values
	 * @return
	 */
	public static String putParamIn(Map<String,Object> paramMap, String propName, Object... values){
		log.debug("value="+values);
		if(null==values || values.length==0) return "";
		StringBuffer rtnSb = new StringBuffer();
		rtnSb.append(" and ").append(propName).append(" in(");
		String propNameSec = replaceResrvChars(propName);//过滤敏感字符
		for(int i=0; i<values.length; i++){
			String key = propNameSec+i;
			Object val = values[i];
			rtnSb.append(":").append(key);
			
			if(i<values.length-1) rtnSb.append(",");
			
			paramMap.put(key, val);
		}
		rtnSb.append(") ");
		
		return rtnSb.toString();
	}
	/**
	 * 添加between型条件
	 * @param paramMap
	 * @param propName
	 * @param amount1
	 * @param amount2
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 */
	public static String putParamBetween(Map<String,Object> paramMap, String propName, Object amount1, Object amount2) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		if(null==amount1 || null==amount2){
			return "";
		}else{
			if(amount1 instanceof String && ((String) amount1).trim().length()==0){
				return "";
			}
			if(amount2 instanceof String && ((String) amount2).trim().length()==0){
				return "";
			}
		}
		StringBuffer rtnSb = new StringBuffer();
		String propNameSec = replaceResrvChars(propName);//过滤敏感字符
		rtnSb.append(" and (").append(propName)
			.append(" between :").append(propNameSec).append("1")
			.append(" and ").append(":").append(propNameSec).append("2 )");
		paramMap.put(propNameSec+"1", amount1);
		paramMap.put(propNameSec+"2", amount2);
		
		return rtnSb.toString();
	}
	/**
	 * 添加like型条件
	 * @param paramMap
	 * @param bean
	 * @param propNames
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 */
	public static String putParamsLike(Map<String,Object> paramMap, Object bean, String... propNames) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		StringBuffer rtnSb = new StringBuffer();
		for(String propName : propNames){
			Object value = ModelUtil.getPropertyValue(bean, propName);
			if(null!=value){
				if(value instanceof String){//仅考虑字符串类型
					if(((String) value).trim().length()>0){
						String newPropName = replaceResrvChars(propName);
						rtnSb.append(" and ").append(propName).append(" like :").append(newPropName);
						paramMap.put(newPropName, makeLikeValue(value.toString()));
					}
				}
			}
		}
		
		return rtnSb.toString();
	}
	/**
	 * 
	 * Description: 
	 *
	 * @param 
	 * @return String
	 * @throws 
	 * @Author KOP
	 * Create Date: 2012-5-16 下午7:16:53
	 */
	public static String putParamsLike(Map<String,Object> paramMap, String[] propNames, String[] propValues) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		StringBuffer rtnSb = new StringBuffer();
		for(int i=0; i<propNames.length; i++){
			String propName = propNames[i];
			String value = propValues[i];
			if(null!=value){
				if(value instanceof String){//仅考虑字符串类型
					if(((String) value).trim().length()>0){
						String newPropName = replaceResrvChars(propName);
						rtnSb.append(" and ").append(propName).append(" like :").append(newPropName);
						paramMap.put(newPropName, makeLikeValue(value));
					}
				}
			}
		}
		
		return rtnSb.toString();
	}
	
	/**
	 * 加入大于条件，可置是否等于
	 * @param paramMap 参数map
	 * @param name 名称
	 * @param value 值
	 * @param isEquals 是否等于
	 * @return 大于等于查询hql串
	 */
	public static String putParamsGt(Map<String,Object> paramMap, String propName, Object value, boolean isEquals){
		if(null==value) {
			return "";
		}
		
		StringBuffer rtnSb = new StringBuffer();
		String suffix = "_Gt";
		rtnSb.append(" and ").append(propName).append(" >");
		if(isEquals) {
			rtnSb.append("=");
		}
		String propNameSec = replaceResrvChars(propName);//过滤敏感字符
		rtnSb.append(" :").append(propNameSec).append(suffix);
		
		paramMap.put(propNameSec+suffix, value);
		
		return rtnSb.toString();
	}
	/**
	 * 加入小于条件，可置是否等于
	 * @param paramMap 参数map
	 * @param name 名称
	 * @param value 值
	 * @param isEquals 是否等于
	 * @return 小于等于查询hql串
	 */
	public static String putParamsLt(Map<String,Object> paramMap, String propName, Object value, boolean isEquals){
		if(null==value) {
			return "";
		}
		
		StringBuffer rtnSb = new StringBuffer();
		String suffix = "_Lt";
		rtnSb.append(" and ").append(propName).append(" <");
		if(isEquals) {
			rtnSb.append("=");
		}
		String propNameSec = replaceResrvChars(propName);//过滤敏感字符
		rtnSb.append(" :").append(propNameSec).append(suffix);
		
		paramMap.put(propNameSec+suffix, value);
		
		return rtnSb.toString();
	}
	/**
	 * 创建valueMap对象
	 * @return
	 */
	public static Map<String,Object> createParamMap(String[] names, Object[] values){
		Assert.notNull(names,"名称数组不能为空");
		Assert.notNull(values,"值数组不能为空");
		Assert.isTrue(names.length == values.length, "keys与values的数量不相等");
		Map<String,Object> map = new HashMap<String,Object>();
		for(int i=0; i<names.length; i++){
			String key = names[i];
			Object value = values[i];
			map.put(key, value);
		}
		return map;
	}
	/**
	 * 
	 * Description: 创建空参数map
	 * @param
	 * @return Map<String>
	 * @throws
	 * @Author KOP
	 * Create Date: 2014年5月22日 下午7:35:41
	 */
	public static Map<String,Object> createParamMap(){
		return new HashMap<String,Object>();
	}
	/**
	 * 
	 * Description: 为查询设置参数
	 *
	 * @param 
	 * @return void
	 * @throws 
	 * @Author KOP
	 * Create Date: 2012-9-21 下午10:09:24
	 */
	@SuppressWarnings("rawtypes")
	public static void setParams4Query(final Map<String, Object> params, Query query) {
		for(String key : params.keySet()){
			Object value = params.get(key);
			if(value instanceof Object[]){
				query.setParameterList(key, (Object[])value);
			}else if(value instanceof Collection){
				query.setParameterList(key, (Collection)value);
			}else{
				query.setParameter(key, value);
			}
		}
	}
	
	/**
	 * 
	 * Description: 生成排序语句
	 *
	 * @param 
	 * @return String
	 * @throws 
	 * @Author KOP
	 * Create Date: 2013-12-25 下午1:24:38
	 */
	public static String orderBy(String sortField, boolean asc, String defaultOrderBy){
		StringBuffer hql = new StringBuffer();
		if(null==sortField){
        	hql.append(defaultOrderBy);
        }else{
        	hql.append(" order by ").append(sortField);
            if(asc){
            	hql.append(" asc");
            }else{
            	hql.append(" desc");
            }
        }
		return hql.toString();
	}
	
	/*========================= helpers ============================*/
	/**
	 * 
	 * Description: 字段名替换保留字符
	 *
	 * @param 
	 * @return String
	 * @throws 
	 * @Author KOP
	 * Create Date: 2013-10-29 上午9:57:00
	 */
	private static String replaceResrvChars(String key){
		if(key.indexOf(".")>0){
			return key.replaceAll("[.]", "_");
		}else{
			return key;
		}
	}
	/**
	 * 
	 * Description: 查询参数名替换保留字符
	 *
	 * @param 
	 * @return Map<String,Object>
	 * @throws 
	 * @Author KOP
	 * Create Date: 2013-10-29 上午10:01:08
	 */
	@SuppressWarnings("unused")
	private static void replaceResrvChars(Map<String, Object> paramMap, String key){
		if(key.indexOf(".")>0){
			Object value = paramMap.get(key);
			String newKey = replaceResrvChars(key);
			paramMap.put(newKey, value);
		}
	}
	/**
	 * 
	 * Description: 生成like条件值
	 *
	 * @param 
	 * @return String
	 * @throws 
	 * @Author KOP
	 * Create Date: 2013-11-13 下午12:11:20
	 */
	private static String makeLikeValue(String rawValue){
		return "%"+rawValue+"%";//默认双向模糊
	}
	
}
