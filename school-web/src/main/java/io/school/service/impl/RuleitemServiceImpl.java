package io.school.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import io.school.dao.RuleitemDao;
import io.school.entity.RuleitemEntity;
import io.school.service.RuleitemService;



@Service("ruleitemService")
public class RuleitemServiceImpl implements RuleitemService {
	@Autowired
	private RuleitemDao ruleitemDao;
	
	@Override
	public RuleitemEntity queryObject(Integer id){
		return ruleitemDao.queryObject(id);
	}
	
	@Override
	public List<RuleitemEntity> queryList(Map<String, Object> map){
		return ruleitemDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return ruleitemDao.queryTotal(map);
	}
	
	@Override
	public void save(RuleitemEntity ruleitem){
		ruleitemDao.save(ruleitem);
	}
	
	@Override
	public void update(RuleitemEntity ruleitem){
		ruleitemDao.update(ruleitem);
	}
	
	@Override
	public void delete(Integer id){
		ruleitemDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		ruleitemDao.deleteBatch(ids);
	}
	
}
