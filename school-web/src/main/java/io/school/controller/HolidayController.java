package io.school.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import io.school.entity.HolidayEntity;
import io.school.service.HolidayService;
import io.school.utils.PageUtils;
import io.school.utils.Query;
import io.school.utils.R;

/**
 * 节假日
 * 
 * @author admin
 * @email 125957265@qq.com
 * @date 2017-06-02 23:24:55
 */
@RestController
@RequestMapping("holiday")
public class HolidayController {
	@Autowired
	private HolidayService holidayService;

	/**
	 * 进入排班表
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("goHolidaySet")
	public ModelAndView goHolidaySet(HttpServletRequest request, HttpServletResponse response) {
		try {
			String date = request.getParameter("date");
			HolidayEntity holiday = holidayService.findObject(date);
			ModelAndView v = new ModelAndView("base/holidaySet.html");
			if(null!=holiday){
			v.addObject("holiday", holiday);
			}else{
				 holiday =new  HolidayEntity();
				 holiday.setHoliday(date);
				 holiday.setIsholiday("");
				 holiday.setId(0);
				v.addObject("holiday", holiday);
			}
			return v;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	/**
	 * 保存
	 */
	@RequestMapping("/set")
	@RequiresPermissions("holiday:save")
	public R set(@RequestBody HolidayEntity holiday) {
		try {
			holidayService.updateSet(holiday);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return R.ok();
	}

	/**
	 * 进入排班表
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("goHoliday")
	public ModelAndView goHoliday(HttpServletRequest request, HttpServletResponse response) {
		try {
			String id = request.getParameter("id");
			HolidayEntity holiday = holidayService.queryObject(new Integer(id));
			ModelAndView v = new ModelAndView("base/holidayUpdate.html");
			v.addObject("defaultDate", holiday.getHoliday());
			return v;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	@ResponseBody
	@RequestMapping("getDataList")
	public List<Map<String, Object>> getDataList(HttpServletRequest request, HttpServletResponse response) {
		String start = request.getParameter("defaultDate");
		List<Map<String, Object>> dataList = holidayService.startList(start);
		return dataList;
	}

	/**
	 * base/holiday.html 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("holiday:list")
	public R list(@RequestParam Map<String, Object> params) {
		// 查询列表数据
		Query query = new Query(params);

		List<HolidayEntity> holidayList = holidayService.queryList(query);
		int total = holidayService.queryTotal(query);

		PageUtils pageUtil = new PageUtils(holidayList, total, query.getLimit(), query.getPage());

		return R.ok().put("page", pageUtil);
	}

	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("holiday:info")
	public R info(@PathVariable("id") Integer id) {
		HolidayEntity holiday = holidayService.queryObject(id);

		return R.ok().put("holiday", holiday);
	}

	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("holiday:save")
	public R save(@RequestBody HolidayEntity holiday) {
		try {
			holidayService.save(holiday);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return R.ok();
	}

	/**
	 * 修改
	 */
	@RequestMapping("/updateYes")
	@RequiresPermissions("holiday:update")
	public R updateYes(@RequestBody Integer[] ids) {
		try {
			holidayService.update(ids, "Y");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return R.ok();
	}

	/**
	 * 修改
	 */
	@RequestMapping("/updateNo")
	@RequiresPermissions("holiday:update")
	public R updateNo(@RequestBody Integer[] ids) {
		try {
			holidayService.update(ids, "N");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return R.ok();
	}

	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("holiday:delete")
	public R delete(@RequestBody Integer[] ids) {
		holidayService.deleteBatch(ids);

		return R.ok();
	}

}
