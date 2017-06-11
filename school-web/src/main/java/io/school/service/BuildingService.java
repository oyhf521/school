package io.school.service;

import io.school.entity.BuildingEntity;
import io.school.utils.JsonComboTree;

import java.util.List;
import java.util.Map;

/**
 * 楼栋
 * 
 * @author admin
 * @email 125957265@qq.com
 * @date 2017-06-02 23:24:56
 */
public interface BuildingService {
	
	BuildingEntity queryObject(Integer id);
	
	List<BuildingEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(BuildingEntity building);
	
	void update(BuildingEntity building);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);

	List<JsonComboTree> buildingListTree();
}
