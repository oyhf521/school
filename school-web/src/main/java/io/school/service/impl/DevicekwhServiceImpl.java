package io.school.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import io.school.dao.DevicekwhDao;
import io.school.entity.DevicekwhEntity;
import io.school.service.DevicekwhService;



@Service("devicekwhService")
public class DevicekwhServiceImpl implements DevicekwhService {
	@Autowired
	private DevicekwhDao devicekwhDao;
	
	@Override
	public DevicekwhEntity queryObject(Integer id){
		return devicekwhDao.queryObject(id);
	}
	
	@Override
	public List<DevicekwhEntity> queryList(Map<String, Object> map){
		return devicekwhDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return devicekwhDao.queryTotal(map);
	}
	
	@Override
	public void save(DevicekwhEntity devicekwh){
		devicekwhDao.save(devicekwh);
	}
	
	@Override
	public void update(DevicekwhEntity devicekwh){
		devicekwhDao.update(devicekwh);
	}
	
	@Override
	public void delete(Integer id){
		devicekwhDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		devicekwhDao.deleteBatch(ids);
	}
	
}
