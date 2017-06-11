package io.school.entity;

import java.io.Serializable;
import java.math.BigDecimal;



/**
 * 用电规则明细
 * 
 * @author admin
 * @email 125957265@qq.com
 * @date 2017-06-05 14:51:09
 */
public class RuleitemEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//ID
	private Integer id;
	//用电规则ID
	private Integer ruleid;
	//日期类型:正常日，休息日
	private String datetype;
	//时段序号
	private Integer orderno;
	//时段
	private String ruletime;
	//上限功率
	private BigDecimal maxkw;

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
	 * 设置：用电规则ID
	 */
	public void setRuleid(Integer ruleid) {
		this.ruleid = ruleid;
	}
	/**
	 * 获取：用电规则ID
	 */
	public Integer getRuleid() {
		return ruleid;
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
	 * 设置：时段序号
	 */
	public void setOrderno(Integer orderno) {
		this.orderno = orderno;
	}
	/**
	 * 获取：时段序号
	 */
	public Integer getOrderno() {
		return orderno;
	}
	/**
	 * 设置：时段
	 */
	public void setRuletime(String ruletime) {
		this.ruletime = ruletime;
	}
	/**
	 * 获取：时段
	 */
	public String getRuletime() {
		return ruletime;
	}
	/**
	 * 设置：上限功率
	 */
	public void setMaxkw(BigDecimal maxkw) {
		this.maxkw = maxkw;
	}
	/**
	 * 获取：上限功率
	 */
	public BigDecimal getMaxkw() {
		return maxkw;
	}
}
