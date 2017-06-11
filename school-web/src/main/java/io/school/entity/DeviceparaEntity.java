package io.school.entity;

import java.io.Serializable;



/**
 * 电表参数
 * 
 * @author admin
 * @email 125957265@qq.com
 * @date 2017-06-02 23:24:55
 */
public class DeviceparaEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//ID
	private Integer id;
	//参数名称
	private String paraname;
	//参数编码
	private String paracode;
	//单线双线:N单,Y双
	private String isdouble;

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
	 * 设置：参数名称
	 */
	public void setParaname(String paraname) {
		this.paraname = paraname;
	}
	/**
	 * 获取：参数名称
	 */
	public String getParaname() {
		return paraname;
	}
	/**
	 * 设置：参数编码
	 */
	public void setParacode(String paracode) {
		this.paracode = paracode;
	}
	/**
	 * 获取：参数编码
	 */
	public String getParacode() {
		return paracode;
	}
	/**
	 * 设置：单线双线:N单,Y双
	 */
	public void setIsdouble(String isdouble) {
		this.isdouble = isdouble;
	}
	/**
	 * 获取：单线双线:N单,Y双
	 */
	public String getIsdouble() {
		return isdouble;
	}
}
