package io.school.entity;

import java.io.Serializable;



/**
 * 楼栋
 * 
 * @author admin
 * @email 125957265@qq.com
 * @date 2017-06-02 23:24:56
 */
public class BuildingEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//ID
	private Integer id;
	//名称
	private String name;
	//编号
	private String code;
	//地址
	private String adress;
	//备注
	private String remark;

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
	 * 设置：名称
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：名称
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：编号
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * 获取：编号
	 */
	public String getCode() {
		return code;
	}
	/**
	 * 设置：地址
	 */
	public void setAdress(String adress) {
		this.adress = adress;
	}
	/**
	 * 获取：地址
	 */
	public String getAdress() {
		return adress;
	}
	/**
	 * 设置：备注
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	/**
	 * 获取：备注
	 */
	public String getRemark() {
		return remark;
	}
}
