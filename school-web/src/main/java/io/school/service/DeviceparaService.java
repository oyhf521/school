package io.school.service;

import io.school.entity.DeviceparaEntity;

import java.util.List;
import java.util.Map;

/**
 * 电表参数
 * 
 * @author admin
 * @email 125957265@qq.com
 * @date 2017-06-02 23:24:55
 */
public interface DeviceparaService {
	
	DeviceparaEntity queryObject(Integer id);
	
	List<DeviceparaEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(DeviceparaEntity devicepara);
	
	void update(DeviceparaEntity devicepara);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
}
