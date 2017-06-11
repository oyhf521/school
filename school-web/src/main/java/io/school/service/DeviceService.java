package io.school.service;

import io.school.entity.DeviceEntity;

import java.util.List;
import java.util.Map;

/**
 * 电表
 * 
 * @author admin
 * @email 125957265@qq.com
 * @date 2017-06-05 17:56:42
 */
public interface DeviceService {
	
	DeviceEntity queryObject(Integer id);
	
	List<DeviceEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(DeviceEntity device);
	
	void update(DeviceEntity device);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);

	void updatePrice(String price, String isover, String build, String roomIds, String buildingIds);

	void updateRule(String ruleId, String build, String roomIds, String buildingIds);

	void updateFree(String free, String remarks, String build, String roomIds, String buildingIds);

	void updateRunStop(String run, String build, String roomIds, String buildingIds);
}
