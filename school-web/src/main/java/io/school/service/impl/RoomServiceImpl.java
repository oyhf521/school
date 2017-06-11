package io.school.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.school.dao.BuildingDao;
import io.school.dao.RoomDao;
import io.school.entity.BuildingEntity;
import io.school.entity.RoomEntity;
import io.school.service.RoomService;
import io.school.utils.JsonComboTree;

@Service("roomService")
public class RoomServiceImpl implements RoomService {
	@Autowired
	private BuildingDao buildingDao;
	@Autowired
	private RoomDao roomDao;

	@Override
	public RoomEntity queryObject(Integer id) {
		return roomDao.queryObject(id);
	}

	@Override
	public List<RoomEntity> queryList(Map<String, Object> map) {
		return roomDao.queryList(map);
	}

	@Override
	public int queryTotal(Map<String, Object> map) {
		return roomDao.queryTotal(map);
	}

	@Override
	public void save(RoomEntity room) {
		roomDao.save(room);
	}

	@Override
	public void update(RoomEntity room) {
		roomDao.update(room);
	}

	@Override
	public void delete(Integer id) {
		roomDao.delete(id);
	}

	@Override
	public void deleteBatch(Integer[] ids) {
		roomDao.deleteBatch(ids);
	}

	@Override
	public List<RoomEntity> listRoomByBuild(Integer buildingid) {
		Map<String, Object> map = new HashMap<>();
		map.put("buildingid", buildingid);
		return roomDao.queryList(map);
	}

	public List<JsonComboTree> roomListTree() {
		List<BuildingEntity> building = buildingDao.queryList(null);
		List<JsonComboTree> list = new ArrayList<JsonComboTree>();
		for (BuildingEntity b : building) {
			List<JsonComboTree> children = new ArrayList<JsonComboTree>();
			JsonComboTree j = new JsonComboTree();
			j.setId(b.getId() + "_build");
			j.setText(b.getName());
			j.setCode(b.getCode());
			List<RoomEntity> rList = listRoomByBuild(b.getId());
			for (RoomEntity r : rList) {
				JsonComboTree jr = new JsonComboTree();
				jr.setId(r.getId() + "_room");
				jr.setText(r.getName());
				jr.setCode(r.getCode());
				jr.setType("Y");
				children.add(jr);
			}
			j.setChildren(children);
			list.add(j);
		}

		return list;

	}
}
