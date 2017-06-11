package io.school.dao;

import io.school.entity.RuleitemEntity;

/**
 * 用电规则明细
 * 
 * @author admin
 * @email 125957265@qq.com
 * @date 2017-06-05 14:51:09
 */
public interface RuleitemDao extends BaseDao<RuleitemEntity> {

	void deleteRule(Integer id);
	
}
