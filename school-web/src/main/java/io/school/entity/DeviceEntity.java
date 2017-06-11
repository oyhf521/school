package io.school.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;



/**
 * 电表
 * 
 * @author admin
 * @email 125957265@qq.com
 * @date 2017-06-05 17:56:42
 */
public class DeviceEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//ID
	private Integer id;
	//楼栋ID
	private Integer buildingid;
	//房间ID
	private Integer roomid;
	//用电规则ID
	private Integer ruleid;
	//电表名称
	private String name;
	//电表号
	private String code;
	//新电表号
	private String newcode;
	//状态：“停电”或“通电”
	private String status;
	//电价
	private BigDecimal price;
	//是否允许欠费
	private String isover;
	//最近电量
	private BigDecimal lateuse;
	//最近记录时间
	private Date latetime;
	//参数名称
	private String paraname;
	//参数编码
	private String paracode;
	//单线双线:N单,Y双
	private String isdouble;
	//可用电量
	private BigDecimal freeuse;
	//备注
	private String remarks;
	private RoomEntity room;
	private BuildingEntity building;
	private DeviceruleEntity rule;

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
	 * 设置：楼栋ID
	 */
	public void setBuildingid(Integer buildingid) {
		this.buildingid = buildingid;
	}
	/**
	 * 获取：楼栋ID
	 */
	public Integer getBuildingid() {
		return buildingid;
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
	 * 设置：电表名称
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：电表名称
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：电表号
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * 获取：电表号
	 */
	public String getCode() {
		return code;
	}
	public String getNewcode() {
		return newcode;
	}
	public void setNewcode(String newcode) {
		this.newcode = newcode;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * 设置：电价
	 */
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	/**
	 * 获取：电价
	 */
	public BigDecimal getPrice() {
		return price;
	}
	/**
	 * 设置：是否允许欠费
	 */
	public void setIsover(String isover) {
		this.isover = isover;
	}
	/**
	 * 获取：是否允许欠费
	 */
	public String getIsover() {
		return isover;
	}
	/**
	 * 设置：最近电量
	 */
	public void setLateuse(BigDecimal lateuse) {
		this.lateuse = lateuse;
	}
	/**
	 * 获取：最近电量
	 */
	public BigDecimal getLateuse() {
		return lateuse;
	}
	/**
	 * 设置：最近记录时间
	 */
	public void setLatetime(Date latetime) {
		this.latetime = latetime;
	}
	/**
	 * 获取：最近记录时间
	 */
	public Date getLatetime() {
		return latetime;
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
	/**
	 * 设置：可用电量
	 */
	public void setFreeuse(BigDecimal freeuse) {
		this.freeuse = freeuse;
	}
	/**
	 * 获取：可用电量
	 */
	public BigDecimal getFreeuse() {
		return freeuse;
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
	public RoomEntity getRoom() {
		return room;
	}
	public void setRoom(RoomEntity room) {
		this.room = room;
	}
	public BuildingEntity getBuilding() {
		return building;
	}
	public void setBuilding(BuildingEntity building) {
		this.building = building;
	}
	public DeviceruleEntity getRule() {
		return rule;
	}
	public void setRule(DeviceruleEntity rule) {
		this.rule = rule;
	}
}
