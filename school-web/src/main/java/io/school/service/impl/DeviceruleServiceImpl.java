package io.school.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.school.dao.DeviceruleDao;
import io.school.dao.RuleitemDao;
import io.school.entity.DeviceruleEntity;
import io.school.entity.RuleitemEntity;
import io.school.service.DeviceruleService;
import io.school.utils.DateUtils;

@Service("deviceruleService")
public class DeviceruleServiceImpl implements DeviceruleService {
	@Autowired
	private DeviceruleDao deviceruleDao;
	@Autowired
	private RuleitemDao ruleitemDao;

	@Override
	public DeviceruleEntity queryObject(Integer id) {
		return deviceruleDao.queryObject(id);
	}

	@Override
	public List<DeviceruleEntity> queryList(Map<String, Object> map) {
		return deviceruleDao.queryList(map);
	}

	@Override
	public int queryTotal(Map<String, Object> map) {
		return deviceruleDao.queryTotal(map);
	}

	@Override
	public void save(DeviceruleEntity devicerule) {
		deviceruleDao.save(devicerule);

		for (int i = 0; i < 24; i++) {
			String time = DateUtils.addHour(devicerule.getBegintime(), i);
			System.out.println(time);
			RuleitemEntity ruleitem = new RuleitemEntity();
			// 用电规则ID
			ruleitem.setRuleid(devicerule.getId());
			// 日期类型:正常日，休息日
			ruleitem.setDatetype("0");
			// 时段序号
			ruleitem.setOrderno(i+1);
			// 时段
			ruleitem.setRuletime(time);
			// 上限功率
			ruleitem.setMaxkw(new BigDecimal(0));
			ruleitemDao.save(ruleitem);
		}
		for (int i = 24; i < 48; i++) {
			String time = DateUtils.addHour(devicerule.getBegintime(), i);
			System.out.println(time);
			RuleitemEntity ruleitem = new RuleitemEntity();
			// 用电规则ID
			ruleitem.setRuleid(devicerule.getId());
			// 日期类型:正常日，休息日
			ruleitem.setDatetype("1");
			// 时段序号
			ruleitem.setOrderno(i+1);
			// 时段
			ruleitem.setRuletime(time);
			// 上限功率
			ruleitem.setMaxkw(new BigDecimal(0));
			ruleitemDao.save(ruleitem);
		}
	
	}

	@Override
	public void update(DeviceruleEntity devicerule) {
		DeviceruleEntity rule = deviceruleDao.queryObject(devicerule.getId());
		if (!rule.getBegintime().equals(devicerule.getBegintime()))// 如果修改时间段重新生成
		{
			ruleitemDao.deleteRule(devicerule.getId());
			for (int i = 0; i < 24; i++) {
				String time = DateUtils.addHour(devicerule.getBegintime(), i);
				System.out.println(time);
				RuleitemEntity ruleitem = new RuleitemEntity();
				// 用电规则ID
				ruleitem.setRuleid(devicerule.getId());
				// 日期类型:正常日，休息日
				ruleitem.setDatetype("0");
				// 时段序号
				ruleitem.setOrderno(i+1);
				// 时段
				ruleitem.setRuletime(time);
				// 上限功率
				ruleitem.setMaxkw(new BigDecimal(0));
				ruleitemDao.save(ruleitem);
			}
			for (int i = 24; i < 48; i++) {
				String time = DateUtils.addHour(devicerule.getBegintime(), i);
				System.out.println(time);
				RuleitemEntity ruleitem = new RuleitemEntity();
				// 用电规则ID
				ruleitem.setRuleid(devicerule.getId());
				// 日期类型:正常日，休息日
				ruleitem.setDatetype("1");
				// 时段序号
				ruleitem.setOrderno(i+1);
				// 时段
				ruleitem.setRuletime(time);
				// 上限功率
				ruleitem.setMaxkw(new BigDecimal(0));
				ruleitemDao.save(ruleitem);
			}
		}
		deviceruleDao.update(devicerule);

	}

	@Override
	public void delete(Integer id) {
		deviceruleDao.delete(id);
	}

	@Override
	public void deleteBatch(Integer[] ids) {
		deviceruleDao.deleteBatch(ids);
	}

}
