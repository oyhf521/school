package io.school.service;

import io.school.entity.HolidayEntity;

import java.util.List;
import java.util.Map;

/**
 * 节假日
 * 
 * @author admin
 * @email 125957265@qq.com
 * @date 2017-06-02 23:24:55
 */
public interface HolidayService {

	HolidayEntity queryObject(Integer id);

	List<HolidayEntity> queryList(Map<String, Object> map);

	int queryTotal(Map<String, Object> map);

	void save(HolidayEntity holiday);

	void update(Integer[] ids,String isholiday);
	
	void updateSet(HolidayEntity holiday);
	
	void update(HolidayEntity holiday);

	void delete(Integer id);

	void deleteBatch(Integer[] ids);

	HolidayEntity findObject(String holiday);
	
	List<Map<String, Object>> startList(String holiday);
}
