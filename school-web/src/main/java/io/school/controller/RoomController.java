package io.school.controller;

import java.util.ArrayList;
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

import io.school.entity.RoomEntity;
import io.school.service.RoomService;
import io.school.utils.JsonComboTree;
import io.school.utils.LabelValue;
import io.school.utils.PageUtils;
import io.school.utils.Query;
import io.school.utils.R;


/**
 * 房间
 * 
 * @author admin
 * @email 125957265@qq.com
 * @date 2017-06-02 23:50:07
 */
@RestController
@RequestMapping("room")
public class RoomController {
	@Autowired
	private RoomService roomService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("room:list")
	public R list(@RequestParam Map<String, Object> params){
		try {
			//查询列表数据
			Query query = new Query(params);

			List<RoomEntity> roomList = roomService.queryList(query);
			int total = roomService.queryTotal(query);
			
			PageUtils pageUtil = new PageUtils(roomList, total, query.getLimit(), query.getPage());
			
			return R.ok().put("page", pageUtil);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("room:info")
	public R info(@PathVariable("id") Integer id){
		RoomEntity room = roomService.queryObject(id);
		
		return R.ok().put("room", room);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("room:save")
	public R save(@RequestBody RoomEntity room){
		roomService.save(room);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("room:update")
	public R update(@RequestBody RoomEntity room){
		roomService.update(room);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("room:delete")
	public R delete(@RequestBody Integer[] ids){
		roomService.deleteBatch(ids);
		
		return R.ok();
	}
	 
	/**
	 * 选择菜单(添加、修改菜单)
	 */
	@RequestMapping("/select")
	public R select(Integer buildingId){
		try {
			//查询列表数据
			List<RoomEntity> roomList = roomService.listRoomByBuild(buildingId);
			return R.ok().put("roomList", roomList);
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
	@RequestMapping("roomListTree")
	@ResponseBody
	public List<JsonComboTree> roomListTree() throws Exception
	{
		List<JsonComboTree> roomList = roomService.roomListTree();
		return roomList;
	}
}
