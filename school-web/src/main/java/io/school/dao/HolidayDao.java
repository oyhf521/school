package io.school.dao;

import java.util.List;

import io.school.entity.HolidayEntity;

/**
 * 节假日
 * 
 * @author admin
 * @email 125957265@qq.com
 * @date 2017-06-02 23:24:55
 */
public interface HolidayDao extends BaseDao<HolidayEntity> {
	public HolidayEntity findObject(String holiday);

	public List<HolidayEntity> startList(String holiday);
}
