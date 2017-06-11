package io.school.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import io.school.entity.BuildingEntity;
import io.school.entity.DeviceEntity;
import io.school.entity.DeviceruleEntity;
import io.school.entity.RoomEntity;
import io.school.service.BuildingService;
import io.school.service.DeviceService;
import io.school.service.DeviceruleService;
import io.school.service.RoomService;
import io.school.utils.Devicepara;
import io.school.utils.PageUtils;
import io.school.utils.Query;
import io.school.utils.R;
import io.school.utils.RRException;
import io.school.utils.StringUtil;

/**
 * 电表
 * 
 * @author admin
 * @email 125957265@qq.com
 * @date 2017-06-05 17:56:42
 */
@RestController
@RequestMapping("device")
public class DeviceController {
	@Autowired
	private DeviceService deviceService;
	@Autowired
	private DeviceruleService deviceruleService;
	@Autowired
	private RoomService roomService;
	@Autowired
	private BuildingService buildingService;

	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("device:list")
	public R list(@RequestParam Map<String, Object> params) {
		// 查询列表数据
		Query query = new Query(params);

		List<DeviceEntity> deviceList = deviceService.queryList(query);
		int total = deviceService.queryTotal(query);

		PageUtils pageUtil = new PageUtils(deviceList, total, query.getLimit(), query.getPage());

		return R.ok().put("page", pageUtil);
	}

	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("device:info")
	public R info(@PathVariable("id") Integer id) {
		DeviceEntity device = deviceService.queryObject(id);

		return R.ok().put("device", device);
	}

	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("device:save")
	public R save(@RequestBody DeviceEntity device) {
		verifyForm(device);

		deviceService.save(device);

		return R.ok();
	}

	/**
	 * 进入修改
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("goUpdate")
	public ModelAndView goUpdate(HttpServletRequest request, HttpServletResponse response) {
		try {
			Integer id = StringUtil.getInt(request.getParameter("id"));
			DeviceEntity device = deviceService.queryObject(id);
			ModelAndView v = new ModelAndView("base/deviceUpdate.html");
			if (Devicepara.isSingle.equals(device.getIsdouble())) {
				device.setIsdouble("单线");
			} else {
				device.setIsdouble("双线");
			}
			if(StringUtil.isEmpty(device.getRemarks()))
			{device.setRemarks("");}
			v.addObject("device", device);
			return v;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("device:update")
	public R update(@RequestBody DeviceEntity device) {
		verifyForm(device);
		try {
			deviceService.update(device);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return R.ok();
	}
	/**
	 * 修改
	 */
	@RequestMapping("/setprice")
	public R setprice(HttpServletRequest request, HttpServletResponse response) {
		try {
			 
			 String  isover =request.getParameter("isover");
			 String  build =request.getParameter("build");
			 String  price =request.getParameter("price");
			 String  roomIds =request.getParameter("roomIds");
			 String  buildingIds =request.getParameter("buildingIds");
			 deviceService.updatePrice(price,isover,build,roomIds,buildingIds);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return R.ok();
	}
	/**
	 * 修改
	 */
	@RequestMapping("/setRule")
	public R setRule(HttpServletRequest request, HttpServletResponse response) {
		try {
			String  ruleId =request.getParameter("ruleId");
			String  build =request.getParameter("build");
			String  roomIds =request.getParameter("roomIds");
			String  buildingIds =request.getParameter("buildingIds");
			deviceService.updateRule(ruleId,build,roomIds,buildingIds);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return R.ok();
	}
	/**
	 * 修改
	 */
	@RequestMapping("/setRunStop")
	public R setRunStop(HttpServletRequest request, HttpServletResponse response) {
		try {
			String  ruleId =request.getParameter("ruleId");
			String  build =request.getParameter("build");
			String  roomIds =request.getParameter("roomIds");
			String  buildingIds =request.getParameter("buildingIds");
			deviceService.updateRunStop(ruleId,build,roomIds,buildingIds);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return R.ok();
	}
	/**
	 * 修改
	 */
	@RequestMapping("/setFree")
	public R setFree(HttpServletRequest request, HttpServletResponse response) {
		try {
			String  free =request.getParameter("free");
			String  remarks =request.getParameter("remarks");
			String  build =request.getParameter("build");
			String  roomIds =request.getParameter("roomIds");
			String  buildingIds =request.getParameter("buildingIds");
			deviceService.updateFree(free,remarks,build,roomIds,buildingIds);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return R.ok();
	}

	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("device:delete")
	public R delete(@RequestBody Integer[] ids) {
		deviceService.deleteBatch(ids);

		return R.ok();
	}

	@RequestMapping("/getSelect")
	public R getSelect() {
		try {
			List<DeviceruleEntity> ruleList = deviceruleService.queryList(null);
			List<BuildingEntity> buildingList = buildingService.queryList(null);
			List<RoomEntity> roomList = roomService.listRoomByBuild(null);
			return R.ok().put("ruleList", ruleList).put("buildingList", buildingList).put("roomList", roomList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	 
	/**
	 * 验证参数是否正确
	 */
	private void verifyForm(DeviceEntity device) {
		if (StringUtils.isBlank(device.getName())) {
			throw new RRException("菜单名称不能为空");
		}

	}
}
