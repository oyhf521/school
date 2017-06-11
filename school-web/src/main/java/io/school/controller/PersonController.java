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

import io.school.entity.BuildingEntity;
import io.school.entity.PersonEntity;
import io.school.entity.RoomEntity;
import io.school.service.BuildingService;
import io.school.service.PersonService;
import io.school.service.RoomService;
import io.school.utils.PageUtils;
import io.school.utils.Query;
import io.school.utils.R;

/**
 * 住户（学生）
 * 
 * @author admin
 * @email 125957265@qq.com
 * @date 2017-06-02 23:24:56
 */
@RestController
@RequestMapping("person")
public class PersonController {
	@Autowired
	private PersonService personService;
	@Autowired
	private RoomService roomService;
	@Autowired
	private BuildingService buildingService;

	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("person:list")
	public R list(@RequestParam Map<String, Object> params) {
		// 查询列表数据
		Query query = new Query(params);

		List<PersonEntity> personList = personService.queryList(query);
		int total = personService.queryTotal(query);

		PageUtils pageUtil = new PageUtils(personList, total, query.getLimit(), query.getPage());

		return R.ok().put("page", pageUtil);
	}

	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("person:info")
	public R info(@PathVariable("id") Integer id) {
		PersonEntity person = personService.queryObject(id);

		return R.ok().put("person", person);
	}

	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("person:save")
	public R save(@RequestBody PersonEntity person) {
		personService.save(person);

		return R.ok();
	}

	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("person:update")
	public R update(@RequestBody PersonEntity person) {
		personService.update(person);

		return R.ok();
	}

	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("person:delete")
	public R delete(@RequestBody Integer[] ids) {
		personService.deleteBatch(ids);

		return R.ok();
	}

	/**
	 * 设置为生效
	 */
	@RequestMapping("/setStatusOk")
	@RequiresPermissions("person:setStatusOk")
	public R setStatusOk(@RequestBody Integer[] ids) {
		try {
			personService.setStatus(ids, "Y");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return R.ok();
	}

	/**
	 * 设置为生效
	 */
	@RequestMapping("/setStatusNo")
	@RequiresPermissions("person:setStatusNo")
	public R setStatusNo(@RequestBody Integer[] ids) {
		try {
			personService.setStatus(ids, "N");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return R.ok();
	}

	@RequestMapping("/getSelect")
	public R getSelect() {
		try {
			List<BuildingEntity> buildingList = buildingService.queryList(null);
			List<RoomEntity> roomList = roomService.listRoomByBuild(null);
			return R.ok().put("buildingList", buildingList).put("roomList", roomList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
