package io.school.controller;

import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.school.entity.DeviceruleEntity;
import io.school.service.DeviceruleService;
import io.school.utils.JsonComboTree;
import io.school.utils.PageUtils;
import io.school.utils.Query;
import io.school.utils.R;


/**
 * 用电规则
 * 
 * @author admin
 * @email 125957265@qq.com
 * @date 2017-06-05 14:51:09
 */
@RestController
@RequestMapping("devicerule")
public class DeviceruleController {
	@Autowired
	private DeviceruleService deviceruleService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("devicerule:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<DeviceruleEntity> deviceruleList = deviceruleService.queryList(query);
		int total = deviceruleService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(deviceruleList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("devicerule:info")
	public R info(@PathVariable("id") Integer id){
		DeviceruleEntity devicerule = deviceruleService.queryObject(id);
		
		return R.ok().put("devicerule", devicerule);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("devicerule:save")
	public R save(@RequestBody DeviceruleEntity devicerule){
		deviceruleService.save(devicerule);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("devicerule:update")
	public R update(@RequestBody DeviceruleEntity devicerule){
		deviceruleService.update(devicerule);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("devicerule:delete")
	public R delete(@RequestBody Integer[] ids){
		deviceruleService.deleteBatch(ids);
		
		return R.ok();
	}

	@RequestMapping("/select")
	public R select(){
		try {
			//查询列表数据
			List<DeviceruleEntity> ruleList = deviceruleService.queryList(null);
			 
			return R.ok().put("ruleList", ruleList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 下拉列表
	 * 
	 * @return  List<LabelValue>
	 * @throws Exception
	 */
	@RequestMapping("ruleList")
	@ResponseBody
	public List<DeviceruleEntity> ruleList() throws Exception
	{
		//查询列表数据
		List<DeviceruleEntity> ruleList = deviceruleService.queryList(null);
		 
		return ruleList;
	}
}
