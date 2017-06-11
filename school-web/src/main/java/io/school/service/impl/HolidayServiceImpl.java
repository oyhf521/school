package io.school.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.school.dao.HolidayDao;
import io.school.entity.HolidayEntity;
import io.school.service.HolidayService;
import io.school.utils.DateUtils;

@Service("holidayService")
public class HolidayServiceImpl implements HolidayService {
	@Autowired
	private HolidayDao holidayDao;

	@Override
	public HolidayEntity queryObject(Integer id) {
		return holidayDao.queryObject(id);
	}

	@Override
	public HolidayEntity findObject(String holiday) {
		return holidayDao.findObject(holiday);
	}

	@Override
	public List<HolidayEntity> queryList(Map<String, Object> map) {
		return holidayDao.queryList(map);
	}

	@Override
	public int queryTotal(Map<String, Object> map) {
		return holidayDao.queryTotal(map);
	}

	@Override
	public void save(HolidayEntity holiday) {
		String endholiday = holiday.getEndholiday();
		if (StringUtils.isNotEmpty(endholiday)) {
			List<Date> holidayList = DateUtils.betweenDate(holiday.getHoliday(), endholiday);
			if (CollectionUtils.isNotEmpty(holidayList)) {
				for (Date d : holidayList) {
					String week = DateUtils.getDayOfWeekByDate(d);
					holiday.setWeek(week);
					holiday.setHoliday(DateUtils.formatDate(d));
					// 法定假日名称
					holiday.setName("工作日");
					// 是否节假日
					holiday.setIsholiday("N");
					if (week.contains("星期日") || week.contains("星期六")) {
						// 法定假日名称
						holiday.setName("休息日");
						// 是否节假日
						holiday.setIsholiday("Y");
					}
					HolidayEntity o = findObject(DateUtils.formatDate(d));
					if (null == o) {
						holidayDao.save(holiday);
					}
				}
			}
		} else {
			String week = DateUtils.getDayOfWeekByDate(DateUtils.parse(holiday.getHoliday()));
			holiday.setWeek(week);
			// 是否节假日
			holiday.setIsholiday("N");
			if ("Y".equals(holiday.getIsholiday())) {
				// 法定假日名称
				holiday.setName("休息日");
			} else { // 法定假日名称
				holiday.setName("工作日");
			}
			HolidayEntity o = findObject(holiday.getHoliday());
			if (null == o) {
				holidayDao.save(holiday);
			}

		}
	}

	@Override
	public void update(Integer[] ids, String isholiday) {
		if (null != ids)
			for (Integer id : ids) {
				HolidayEntity holiday = holidayDao.queryObject(id);
				holiday.setIsholiday(isholiday);
				holidayDao.update(holiday);
			}
	}
	@Override
	public void updateSet(HolidayEntity d) {

		HolidayEntity holiday = holidayDao.queryObject(d.getId());
		if (null != holiday) {
			holiday.setIsholiday(d.getIsholiday());
			if ("Y".equals(holiday.getIsholiday())) {
				holiday.setName("休息日");
			} else {
				holiday.setName("工作日");
			}
			holidayDao.update(holiday);
		} else {
			HolidayEntity holi = new HolidayEntity();
			String week = DateUtils.getDayOfWeekByDate(DateUtils.parse(d.getHoliday()));
			holi.setWeek(week);
			holi.setHoliday(d.getHoliday());
			holi.setIsholiday(d.getIsholiday());
			if ("Y".equals(holi.getIsholiday())) {
				holi.setName("休息日");
			} else {
				holi.setName("工作日");
			}
			holidayDao.save(holi);

		}

	}

	@Override
	public void update(HolidayEntity holiday) {
		holidayDao.update(holiday);

	}

	@Override
	public void delete(Integer id) {
		holidayDao.delete(id);
	}

	@Override
	public void deleteBatch(Integer[] ids) {
		holidayDao.deleteBatch(ids);
	}

	public List<Map<String, Object>> startList(String start) {
		List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
		try {
			List<HolidayEntity> holidayList = holidayDao.startList(start);
			if (CollectionUtils.isNotEmpty(holidayList)) {

				for (HolidayEntity bo : holidayList) {
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("id", bo.getId());
					map.put("start", bo.getHoliday());
					if ("Y".equals(bo.getIsholiday())) {
						map.put("title", "休息日");
						map.put("color", "#d9534f");
					} else {
						map.put("title", "工作日");
						map.put("color", "#5cb85c");
					}

					dataList.add(map);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();

		}
		return dataList;

	}
}
