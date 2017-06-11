package io.school.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import io.school.dao.DeviceparaDao;
import io.school.entity.DeviceparaEntity;
import io.school.service.DeviceparaService;



@Service("deviceparaService")
public class DeviceparaServiceImpl implements DeviceparaService {
	@Autowired
	private DeviceparaDao deviceparaDao;
	
	@Override
	public DeviceparaEntity queryObject(Integer id){
		return deviceparaDao.queryObject(id);
	}
	
	@Override
	public List<DeviceparaEntity> queryList(Map<String, Object> map){
		return deviceparaDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return deviceparaDao.queryTotal(map);
	}
	
	@Override
	public void save(DeviceparaEntity devicepara){
		deviceparaDao.save(devicepara);
	}
	
	@Override
	public void update(DeviceparaEntity devicepara){
		deviceparaDao.update(devicepara);
	}
	
	@Override
	public void delete(Integer id){
		deviceparaDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		deviceparaDao.deleteBatch(ids);
	}
	
}
