package io.school.service;

import io.school.entity.RuleitemEntity;

import java.util.List;
import java.util.Map;

/**
 * 用电规则明细
 * 
 * @author admin
 * @email 125957265@qq.com
 * @date 2017-06-05 14:51:09
 */
public interface RuleitemService {
	
	RuleitemEntity queryObject(Integer id);
	
	List<RuleitemEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(RuleitemEntity ruleitem);
	
	void update(RuleitemEntity ruleitem);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
}
