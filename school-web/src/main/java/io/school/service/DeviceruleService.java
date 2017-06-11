package io.school.service;

import io.school.entity.DeviceruleEntity;

import java.util.List;
import java.util.Map;

/**
 * 用电规则
 * 
 * @author admin
 * @email 125957265@qq.com
 * @date 2017-06-05 14:51:09
 */
public interface DeviceruleService {
	
	DeviceruleEntity queryObject(Integer id);
	
	List<DeviceruleEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(DeviceruleEntity devicerule);
	
	void update(DeviceruleEntity devicerule);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
}
