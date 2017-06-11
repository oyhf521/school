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

import io.school.entity.DevicekwhEntity;
import io.school.service.DevicekwhService;
import io.school.utils.PageUtils;
import io.school.utils.Query;
import io.school.utils.R;


/**
 * 电表流水
 * 
 * @author admin
 * @email 125957265@qq.com
 * @date 2017-06-08 10:54:25
 */
@RestController
@RequestMapping("devicekwh")
public class DevicekwhController {
	@Autowired
	private DevicekwhService devicekwhService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("devicekwh:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<DevicekwhEntity> devicekwhList = devicekwhService.queryList(query);
		int total = devicekwhService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(devicekwhList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("devicekwh:info")
	public R info(@PathVariable("id") Integer id){
		DevicekwhEntity devicekwh = devicekwhService.queryObject(id);
		
		return R.ok().put("devicekwh", devicekwh);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("devicekwh:save")
	public R save(@RequestBody DevicekwhEntity devicekwh){
		devicekwhService.save(devicekwh);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("devicekwh:update")
	public R update(@RequestBody DevicekwhEntity devicekwh){
		devicekwhService.update(devicekwh);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("devicekwh:delete")
	public R delete(@RequestBody Integer[] ids){
		devicekwhService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
