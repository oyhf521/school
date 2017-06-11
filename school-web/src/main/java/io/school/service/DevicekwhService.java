package io.school.service;

import io.school.entity.DevicekwhEntity;

import java.util.List;
import java.util.Map;

/**
 * 电表流水
 * 
 * @author admin
 * @email 125957265@qq.com
 * @date 2017-06-08 10:54:25
 */
public interface DevicekwhService {
	
	DevicekwhEntity queryObject(Integer id);
	
	List<DevicekwhEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(DevicekwhEntity devicekwh);
	
	void update(DevicekwhEntity devicekwh);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
}
