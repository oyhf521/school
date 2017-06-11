package io.school.service;

import io.school.entity.PersonEntity;

import java.util.List;
import java.util.Map;

/**
 * 住户（学生）
 * 
 * @author admin
 * @email 125957265@qq.com
 * @date 2017-06-02 23:24:56
 */
public interface PersonService {
	
	PersonEntity queryObject(Integer id);
	
	List<PersonEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(PersonEntity person);
	
	void update(PersonEntity person);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);

	void setStatus(Integer[] ids, String string);
}
