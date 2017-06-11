package io.school.entity;

import java.io.Serializable;



/**
 * 住户（学生）
 * 
 * @author admin
 * @email 125957265@qq.com
 * @date 2017-06-02 23:24:56
 */
public class PersonEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//ID
	private Integer id;
	//房间ID
	private Integer roomid;
	//楼栋ID
	private Integer buildingid;
	//学生名称
	private String name;
	//学号
	private String code;
	//手机号码
	private String mobile;
	//身份证号
	private String idnumber;
	//状态
	private String status;
	//备注
	private String remarks;
	private RoomEntity room;
	private BuildingEntity building;

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
	 * 设置：学生名称
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：学生名称
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：学号
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * 获取：学号
	 */
	public String getCode() {
		return code;
	}
	/**
	 * 设置：手机号码
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	/**
	 * 获取：手机号码
	 */
	public String getMobile() {
		return mobile;
	}
	/**
	 * 设置：身份证号
	 */
	public void setIdnumber(String idnumber) {
		this.idnumber = idnumber;
	}
	/**
	 * 获取：身份证号
	 */
	public String getIdnumber() {
		return idnumber;
	}
	/**
	 * 设置：状态
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * 获取：状态
	 */
	public String getStatus() {
		return status;
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
	 
}
