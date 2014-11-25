package com.kopliverpool.framework.core.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.kopliverpool.framework.hibernate.model.BaseModel;

/** 
 *
 * Description: 基本用户Model
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
@Table(name = "CORE_USER")
public class UserModel extends BaseModel{

	/**
	 * UID
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "USERNAME", length = 36, unique = true)
	private String username;
	
	@Column(name = "PASSWORD", length = 36, nullable = false)
	private String password;
	
//	@ManyToOne(targetEntity = RoleModel.class, fetch = FetchType.LAZY)
	@Column(name = "Authorities", length = 500, nullable = false)
	private String authorities;
	
	@Column(name = "AccountNonExpired", nullable = false)
	private boolean accountNonExpired = true;
	
	@Column(name = "AccountNonLocked", nullable = false)
	private boolean accountNonLocked = true;
	
	@Column(name = "CredentialsNonExpired", nullable = false)
	private boolean credentialsNonExpired = true;
	
	@Column(name = "Enabled", nullable = false)
	private boolean enabled = true;
	
	public UserModel(){}
	
	public UserModel(String username, String password, String authorities){
		this.username = username;
		this.password = password;
		this.authorities = authorities;
		this.accountNonExpired = true;
		this.accountNonLocked = true;
		this.credentialsNonExpired = true;
		this.enabled = true;
	}
	
	@Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString()).append(": ");
        sb.append("Username: ").append(this.username).append("; ");
        sb.append("Password: [PROTECTED]; ");
        sb.append("Enabled: ").append(this.enabled).append("; ");
        sb.append("AccountNonExpired: ").append(this.accountNonExpired).append("; ");
        sb.append("credentialsNonExpired: ").append(this.credentialsNonExpired).append("; ");
        sb.append("AccountNonLocked: ").append(this.accountNonLocked).append("; ");

        if (authorities != null && authorities.length() > 0) {
            sb.append("Granted Authorities: ");
            sb.append(authorities);
        } else {
            sb.append("Not granted any authorities");
        }

        return sb.toString();
    }

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAuthorities() {
		return authorities;
	}

	public void setAuthorities(String authorities) {
		this.authorities = authorities;
	}

	public boolean isAccountNonExpired() {
		return accountNonExpired;
	}

	public void setAccountNonExpired(boolean accountNonExpired) {
		this.accountNonExpired = accountNonExpired;
	}

	public boolean isAccountNonLocked() {
		return accountNonLocked;
	}

	public void setAccountNonLocked(boolean accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}

	public boolean isCredentialsNonExpired() {
		return credentialsNonExpired;
	}

	public void setCredentialsNonExpired(boolean credentialsNonExpired) {
		this.credentialsNonExpired = credentialsNonExpired;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
}
