package com.kopliverpool.framework.hibernate.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.annotations.GenericGenerator;

import com.kopliverpool.framework.core.CoreConst;

/** 
 *
 * Description: 
 *
 * @author KOP
 * @version 1.0
 * <pre>
 * Modification History: 
 * Date         Author      Version     Description 
 * ------------------------------------------------------------------ 
 * 2014年11月4日	 KOP          1.0       1.0 Version 
 * </pre>
 */
@MappedSuperclass
public class BaseModel implements Serializable, Comparable<BaseModel>{

	/**
	 * UID
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(length=36,updatable=false)
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy="uuid")
	private String id;//id
	
	@Column(name = "DISPLAY_NAME", length=128)
	private String displayName;//显示名
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CREATE_TIME",updatable=false)
	private Date createTime;//创建时间
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="MODIFY_TIME")
	private Date modifyTime;//最后一次修改时间
	
	@Version
	@Column(name="VERSION")
	private Integer version = 0;//版本(用于乐观锁控制)

	@Column(name = "SORT_INDEX")
	private Long sortIndex = System.currentTimeMillis();//排序号
	
	@Column(name = "SYSTEM_STATE")
	private Integer systemState = CoreConst.STATUS_NORMAL;//系统状态

	@Override
	public boolean equals(Object obj) {
		if(this == obj){
			return true;
		}
		
		boolean rtn = false;
        if (obj != null && this.getClass().isAssignableFrom(obj.getClass())) {
            BaseModel objModel = (BaseModel) obj;
            rtn = new EqualsBuilder()
                    .append(this.getId(), objModel.getId())
                    .isEquals();
        }
        return rtn; 
	}
	
	@Override
	public int compareTo(BaseModel o) {
		return new CompareToBuilder().append(this.getSortIndex(), o.getSortIndex()).toComparison();
	}
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder(17,37)
			.append(getId()).toHashCode();
	}
	
	@Override
	public String toString() {
		return new ToStringBuilder(this).append(this.getId()).append(this.getDisplayName()).toString();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Long getSortIndex() {
		return sortIndex;
	}

	public void setSortIndex(Long sortIndex) {
		this.sortIndex = sortIndex;
	}

	public Integer getSystemState() {
		return systemState;
	}

	public void setSystemState(Integer systemState) {
		this.systemState = systemState;
	}
	
}
