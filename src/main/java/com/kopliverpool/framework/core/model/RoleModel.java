package com.kopliverpool.framework.core.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.kopliverpool.framework.hibernate.model.BaseModel;


/** 
 * Description: 用户角色Model
 *
 * @author KOP
 * @version 1.0
 * <pre>
 * Modification History: 
 * Date         Author      Version     Description 
 * ------------------------------------------------------------------ 
 * 2014年11月12日	 KOP          1.0       1.0 Version 
 * </pre>
 */
@Entity
@Table(name = "CORE_ROLE")
public class RoleModel extends BaseModel{

	/**
	 * UID
	 */
	private static final long serialVersionUID = 1L;
	
	@Column(name = "ROLE_NAME", length = 36, unique = true)
	private String roleName;
	
	@Column(name = "ROLE_CODE", length = 36, unique = true)
	private String roleCode;

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

}
