package com.kopliverpool.framework.core.util;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.log4j.Logger;
import org.springframework.util.Assert;

import com.kopliverpool.framework.hibernate.model.BaseModel;

public class ModelUtil {

	private static Logger log = Logger.getLogger(ModelUtil.class);
	private ModelUtil(){}
	/**
	 * 得到模型集合的ID集
	 * @param col
	 * @return
	 */
	public static Set<String> getIdSet(Collection<? extends BaseModel> col){
		Set<String> idSet = new HashSet<String>();
		for(BaseModel model : col){
			idSet.add(model.getId());
		}
		return idSet;
	}
	/**
	 * 拷贝非空属性
	 * @param source
	 * @param dest
	 */
	public static void copyProps(Object source, Object target, String...ignorProps){
		org.springframework.beans.BeanUtils.copyProperties(source, target, ignorProps);
	}
	/**
	 * 模型列表排序
	 * @param list
	 * @param isAsc 是否为升序
	 * @return
	 */
	public static List<? extends BaseModel> sort(List<? extends BaseModel> list, boolean isAsc){
		Comparator<BaseModel> comp = null;
		if(isAsc){
			comp = new Comparator<BaseModel>(){
				public int compare(BaseModel o1, BaseModel o2) {
					return (int)(o1.getSortIndex()-o2.getSortIndex());
				}
			};
		}else{
			comp = new Comparator<BaseModel>(){
				public int compare(BaseModel o1, BaseModel o2) {
					return -1 * (int)(o1.getSortIndex()-o2.getSortIndex());
				}
			};
		}
		Collections.sort(list, comp);
		
		return list;
	}
	/**
	 * 使用bean中的属性创建Map
	 * @param bean
	 * @param propNames 需要放到map中的属性名称
	 * @return
	 * @throws Exception
	 */
	public static Map<String,Object> convBeanToMap(Object bean, String...propNames) {
		Assert.notNull(propNames, "请指定属性名称");
		Map<String,Object> rtn = new HashMap<String,Object>();
		if("*".equals(propNames[0])){
			PropertyDescriptor[] propDescs = PropertyUtils.getPropertyDescriptors(bean);
			for(PropertyDescriptor propDesc : propDescs){
				String propName = propDesc.getName();
				Object propValue = null;
				try {
					propValue = PropertyUtils.getProperty(bean, propName);
				} catch (Exception e) {
					log.debug("获取其它属性时出错："+e.getMessage());
				}
				rtn.put(propName, propValue);
			}
		}else{
			for(String propName: propNames){
				Object propValue = null;
				try{
					if(PropertyUtils.isReadable(bean, propName)){
						propValue = PropertyUtils.getProperty(bean, propName);
					}
				} catch (Exception e){
					log.debug("获取其它属性时出错："+e.getMessage());
				}
				rtn.put(propName, propValue);
			}
		}
		
		return rtn;
	}
	/**
	 * 取对象的属性值
	 * @param obj
	 * @param propName
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 */
	public static Object getPropertyValue(Object obj, String propName) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		return PropertyUtils.getProperty(obj, propName);
	}
	
	/**
	 * 
	 * Description: 移除空关系（即关联对象ID无效时移除该关系）
	 *
	 * @param 
	 * @return Object
	 * @throws 
	 * @Author KOP
	 */
	public static Object removeNullRelation(Object obj, String relationIdName) throws Exception{
		int idx = relationIdName.lastIndexOf(".");
		Assert.isTrue(idx>0, "关系id属性名无效："+relationIdName);
		
		String relationName = relationIdName.substring(0,idx);
		Object relationId = PropertyUtils.getProperty(obj, relationIdName);
		if(relationId instanceof String){
			String id = (String)relationId;
			if(null==id || id.trim().length()==0){
				PropertyUtils.setProperty(obj, relationName, null);
			}
		}else{
			if(null==relationId){
				PropertyUtils.setProperty(obj, relationName, null);
			}
		}
		return obj;
	}
	
	/**
	 * 
	 * Description: 将树结构转换为列表结构，叶子节点在前
	 *
	 * @param 
	 * @return List
	 * @throws 
	 * @Author KOP
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List treeToList(Object model, String subPropName, List list) throws Exception{
		Collection subs = (Collection)PropertyUtils.getProperty(model, subPropName);
		if(null!=subs && subs.size()>0){
			for(Object sub : subs){
				treeToList(sub, subPropName, list);
			}
		}
		list.add(model);
		
		return list;
	}
	
	/**
	 * 
	 * Description: 为String类型的属性做trim
	 *
	 * @param 
	 * @return void
	 * @throws 
	 * @Author KOP
	 */
	public static void trimFields(Object model, String... propNames) throws Exception{
		Assert.notEmpty(propNames, "请指定要去前后空格的属性名");
		for(String propName : propNames){
			Object val = PropertyUtils.getProperty(model, propName);
			if(val instanceof String){
				if(null!=val){
					String valStr = (String)val;
					valStr = valStr.trim();
					PropertyUtils.setProperty(model, propName, valStr);
				}
			}
		}
	}
	
}
