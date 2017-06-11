package io.school.controller;

import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.school.entity.RuleitemEntity;
import io.school.service.RuleitemService;
import io.school.utils.PageUtils;
import io.school.utils.Query;
import io.school.utils.R;


/**
 * 用电规则明细
 * 
 * @author admin
 * @email 125957265@qq.com
 * @date 2017-06-05 14:51:09
 */
@RestController
@RequestMapping("ruleitem")
public class RuleitemController {
	@Autowired
	private RuleitemService ruleitemService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
 //	@RequiresPermissions("ruleitem:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<RuleitemEntity> ruleitemList = ruleitemService.queryList(query);
		int total = ruleitemService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(ruleitemList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("ruleitem:info")
	public R info(@PathVariable("id") Integer id){
		RuleitemEntity ruleitem = ruleitemService.queryObject(id);
		
		return R.ok().put("ruleitem", ruleitem);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("ruleitem:save")
	public R save(@RequestBody RuleitemEntity ruleitem){
		ruleitemService.save(ruleitem);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	public R update(@RequestBody RuleitemEntity ruleitem){
		ruleitemService.update(ruleitem);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/updateMaxkw")
	public R updateMaxkw(@RequestBody RuleitemEntity ruleitem){
		RuleitemEntity ruleitemNew = ruleitemService.queryObject(ruleitem.getId());
		ruleitemNew.setMaxkw(ruleitem.getMaxkw());
		ruleitemService.update(ruleitem);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("ruleitem:delete")
	public R delete(@RequestBody Integer[] ids){
		ruleitemService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
