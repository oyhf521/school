package io.school.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;



/**
 * 电表流水
 * 
 * @author admin
 * @email 125957265@qq.com
 * @date 2017-06-08 10:54:25
 */
public class DevicekwhEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//ID
	private Integer id;
	//房间ID
	private Integer roomid;
	//电表ID
	private Integer deviceid;
	//电表名称
	private String devicename;
	//电表号
	private String devicecode;
	//操作类型
	private String optype;
	//操作前（可用电量）
	private BigDecimal befkwh;
	//增减数
	private BigDecimal changekwh;
	//操作后（可用电量）
	private BigDecimal aftkwh;
	//操作人
	private String opuse;
	//操作时间
	private Date optime;
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
	 * 设置：房间ID
	 */
	public void setRoomid(Integer roomid) {
		this.roomid = roomid;
	}
	/**
	 * 获取：房间ID
	 */
	public Integer getRoomid() {
		return roomid;
	}
	/**
	 * 设置：电表ID
	 */
	public void setDeviceid(Integer deviceid) {
		this.deviceid = deviceid;
	}
	/**
	 * 获取：电表ID
	 */
	public Integer getDeviceid() {
		return deviceid;
	}
	/**
	 * 设置：电表名称
	 */
	public void setDevicename(String devicename) {
		this.devicename = devicename;
	}
	/**
	 * 获取：电表名称
	 */
	public String getDevicename() {
		return devicename;
	}
	/**
	 * 设置：电表号
	 */
	public void setDevicecode(String devicecode) {
		this.devicecode = devicecode;
	}
	/**
	 * 获取：电表号
	 */
	public String getDevicecode() {
		return devicecode;
	}
	/**
	 * 设置：操作类型
	 */
	public void setOptype(String optype) {
		this.optype = optype;
	}
	/**
	 * 获取：操作类型
	 */
	public String getOptype() {
		return optype;
	}
	/**
	 * 设置：操作前（可用电量）
	 */
	public void setBefkwh(BigDecimal befkwh) {
		this.befkwh = befkwh;
	}
	/**
	 * 获取：操作前（可用电量）
	 */
	public BigDecimal getBefkwh() {
		return befkwh;
	}
	/**
	 * 设置：增减数
	 */
	public void setChangekwh(BigDecimal changekwh) {
		this.changekwh = changekwh;
	}
	/**
	 * 获取：增减数
	 */
	public BigDecimal getChangekwh() {
		return changekwh;
	}
	/**
	 * 设置：操作后（可用电量）
	 */
	public void setAftkwh(BigDecimal aftkwh) {
		this.aftkwh = aftkwh;
	}
	/**
	 * 获取：操作后（可用电量）
	 */
	public BigDecimal getAftkwh() {
		return aftkwh;
	}
	/**
	 * 设置：操作人
	 */
	public void setOpuse(String opuse) {
		this.opuse = opuse;
	}
	/**
	 * 获取：操作人
	 */
	public String getOpuse() {
		return opuse;
	}
	/**
	 * 设置：操作时间
	 */
	public void setOptime(Date optime) {
		this.optime = optime;
	}
	/**
	 * 获取：操作时间
	 */
	public Date getOptime() {
		return optime;
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
