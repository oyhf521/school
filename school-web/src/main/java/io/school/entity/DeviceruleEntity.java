package io.school.entity;

import java.io.Serializable;



/**
 * 用电规则
 * 
 * @author admin
 * @email 125957265@qq.com
 * @date 2017-06-05 14:51:09
 */
public class DeviceruleEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//ID
	private Integer id;
	//规则名称
	private String name;
	//日期类型:正常日，休息日
	private String datetype;
	//开始时间
	private String begintime;
	//备注
	private String remarks;

	/**
	 * 设置：ID
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：ID
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 设置：规则名称
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：规则名称
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：日期类型:正常日，休息日
	 */
	public void setDatetype(String datetype) {
		this.datetype = datetype;
	}
	/**
	 * 获取：日期类型:正常日，休息日
	 */
	public String getDatetype() {
		return datetype;
	}
	/**
	 * 设置：开始时间
	 */
	public void setBegintime(String begintime) {
		this.begintime = begintime;
	}
	/**
	 * 获取：开始时间
	 */
	public String getBegintime() {
		return begintime;
	}
	/**
	 * 设置：备注
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	/**
	 * 获取：备注
	 */
	public String getRemarks() {
		return remarks;
	}
}
