package io.school.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.school.dao.BuildingDao;
import io.school.entity.BuildingEntity;
import io.school.entity.RoomEntity;
import io.school.service.BuildingService;
import io.school.utils.JsonComboTree;



@Service("buildingService")
public class BuildingServiceImpl implements BuildingService {
	@Autowired
	private BuildingDao buildingDao;
	
	@Override
	public BuildingEntity queryObject(Integer id){
		return buildingDao.queryObject(id);
	}
	
	@Override
	public List<BuildingEntity> queryList(Map<String, Object> map){
		return buildingDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return buildingDao.queryTotal(map);
	}
	
	@Override
	public void save(BuildingEntity building){
		buildingDao.save(building);
	}
	
	@Override
	public void update(BuildingEntity building){
		buildingDao.update(building);
	}
	
	@Override
	public void delete(Integer id){
		buildingDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		buildingDao.deleteBatch(ids);
	}

	@Override
	public List<JsonComboTree> buildingListTree() {
		List<BuildingEntity> building = buildingDao.queryList(null);
		List<JsonComboTree> list = new ArrayList<JsonComboTree>();
		for (BuildingEntity b : building) {
			JsonComboTree j = new JsonComboTree();
			j.setId(b.getId() + "");
			j.setText(b.getName());
			j.setCode(b.getCode());
			list.add(j);
		}
		return list;
	}
	
}
