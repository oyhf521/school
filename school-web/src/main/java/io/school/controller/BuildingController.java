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

import io.school.entity.BuildingEntity;
import io.school.service.BuildingService;
import io.school.utils.JsonComboTree;
import io.school.utils.PageUtils;
import io.school.utils.Query;
import io.school.utils.R;


/**
 * 楼栋
 * 
 * @author admin
 * @email 125957265@qq.com
 * @date 2017-06-02 23:24:56
 */
@RestController
@RequestMapping("building")
public class BuildingController {
	@Autowired
	private BuildingService buildingService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("building:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<BuildingEntity> buildingList = buildingService.queryList(query);
		int total = buildingService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(buildingList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("building:info")
	public R info(@PathVariable("id") Integer id){
		BuildingEntity building = buildingService.queryObject(id);
		
		return R.ok().put("building", building);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("building:save")
	public R save(@RequestBody BuildingEntity building){
		buildingService.save(building);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("building:update")
	public R update(@RequestBody BuildingEntity building){
		buildingService.update(building);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("building:delete")
	public R delete(@RequestBody Integer[] ids){
		buildingService.deleteBatch(ids);
		
		return R.ok();
	}

 
	@RequestMapping("/select")
	public R select(){
		try {
			//查询列表数据
			List<BuildingEntity> buildingList = buildingService.queryList(null);
			return R.ok().put("buildingList", buildingList);
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
	@RequestMapping("buildingListTree")
	@ResponseBody
	public List<JsonComboTree> buildingListTree() throws Exception
	{
		List<JsonComboTree> buildingList = buildingService.buildingListTree();
		return buildingList;
	}
}
