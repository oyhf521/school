package io.school.entity;

import java.io.Serializable;



/**
 * 房间
 * 
 * @author admin
 * @email 125957265@qq.com
 * @date 2017-06-02 23:50:07
 */
public class RoomEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//ID
	private Integer id;
	//楼栋ID
	private Integer buildingid;
	//编号
	private String code;
	//名称
	private String name;
	//备注
	private String remark;
	//楼栋
	private BuildingEntity building;
	//电表
	private DeviceEntity device;

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
	public BuildingEntity getBuilding() {
		return building;
	}
	public void setBuilding(BuildingEntity building) {
		this.building = building;
	}
	public DeviceEntity getDevice() {
		return device;
	}
	public void setDevice(DeviceEntity device) {
		this.device = device;
	}
	
}
