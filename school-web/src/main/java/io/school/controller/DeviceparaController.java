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

import io.school.entity.DeviceparaEntity;
import io.school.service.DeviceparaService;
import io.school.utils.PageUtils;
import io.school.utils.Query;
import io.school.utils.R;


/**
 * 电表参数
 * 
 * @author admin
 * @email 125957265@qq.com
 * @date 2017-06-02 23:24:55
 */
@RestController
@RequestMapping("devicepara")
public class DeviceparaController {
	@Autowired
	private DeviceparaService deviceparaService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("devicepara:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<DeviceparaEntity> deviceparaList = deviceparaService.queryList(query);
		int total = deviceparaService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(deviceparaList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("devicepara:info")
	public R info(@PathVariable("id") Integer id){
		DeviceparaEntity devicepara = deviceparaService.queryObject(id);
		
		return R.ok().put("devicepara", devicepara);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("devicepara:save")
	public R save(@RequestBody DeviceparaEntity devicepara){
		deviceparaService.save(devicepara);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("devicepara:update")
	public R update(@RequestBody DeviceparaEntity devicepara){
		deviceparaService.update(devicepara);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("devicepara:delete")
	public R delete(@RequestBody Integer[] ids){
		deviceparaService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
