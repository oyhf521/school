package io.school.entity;

import java.io.Serializable;



/**
 * 节假日
 * 
 * @author admin
 * @email 125957265@qq.com
 * @date 2017-06-02 23:24:55
 */
public class HolidayEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//ID
	private Integer id;
	//日期
	private String holiday;
	//日期
	private String endholiday;
	//星期
	private String week;
	//法定假日名称
	private String name;
	//是否节假日
	private String isholiday;

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
	 * 设置：日期
	 */
	public void setHoliday(String holiday) {
		this.holiday = holiday;
	}
	/**
	 * 获取：日期
	 */
	public String getHoliday() {
		return holiday;
	}
	/**
	 * 设置：星期
	 */
	public void setWeek(String week) {
		this.week = week;
	}
	/**
	 * 获取：星期
	 */
	public String getWeek() {
		return week;
	}
	/**
	 * 设置：法定假日名称
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：法定假日名称
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：是否节假日
	 */
	public void setIsholiday(String isholiday) {
		this.isholiday = isholiday;
	}
	/**
	 * 获取：是否节假日
	 */
	public String getIsholiday() {
		return isholiday;
	}
	public String getEndholiday() {
		return endholiday;
	}
	public void setEndholiday(String endholiday) {
		this.endholiday = endholiday;
	}
	
}
