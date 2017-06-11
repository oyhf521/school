package io.school.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.school.dao.DeviceDao;
import io.school.dao.DevicekwhDao;
import io.school.entity.DeviceEntity;
import io.school.entity.DevicekwhEntity;
import io.school.service.DeviceService;
import io.school.utils.CollectionUtils;
import io.school.utils.Devicepara;
import io.school.utils.OpTypeEnum;
import io.school.utils.ShiroUtils;
import io.school.utils.StringUtil;

@Service("deviceService")
public class DeviceServiceImpl implements DeviceService {
	@Autowired
	private DeviceDao deviceDao;
	@Autowired
	private DevicekwhDao devicekwhDao;

	@Override
	public DeviceEntity queryObject(Integer id) {
		return deviceDao.queryObject(id);
	}

	@Override
	public List<DeviceEntity> queryList(Map<String, Object> map) {
		return deviceDao.queryList(map);
	}

	@Override
	public int queryTotal(Map<String, Object> map) {
		return deviceDao.queryTotal(map);
	}
	/**
	 * 2）新增
	 */
	@Override
	public void save(DeviceEntity device) {
		try {
			String isdouble = device.getIsdouble();
			// 参数名称
			String paraname = "";
			// 参数编码
			String paracode = "";
			HashMap<String, String> map = null;
			if ("1".equals(isdouble)) {
				map = Devicepara.singleMap;
			} else if ("2".equals(isdouble)) {
				map = Devicepara.doubleMap;
			}
			Iterator<Map.Entry<String, String>> it = map.entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry<String, String> entry = it.next();
				paracode += entry.getKey() + ",";
				paraname += entry.getValue() + ",";
			}
			device.setStatus(Devicepara.run);
			device.setParaname(paraname);
			device.setParacode(paracode);
			device.setLatetime(new Date());
			deviceDao.save(device);

			DevicekwhEntity kwh = new DevicekwhEntity();
			// 房间ID
			kwh.setRoomid(device.getRoomid());
			// 电表ID
			kwh.setDeviceid(device.getId());
			// 电表名称
			kwh.setDevicename(device.getName());
			// 电表号
			kwh.setDevicecode(device.getCode());
			// 操作类型
			kwh.setOptype(OpTypeEnum.xz.getValue());
			// 操作前（可用电量）
			kwh.setBefkwh(device.getLateuse());
			// 增减数
			kwh.setChangekwh(new BigDecimal(0));
			// 操作后（可用电量）
			kwh.setAftkwh(device.getFreeuse());
			// 操作人
			kwh.setOpuse(ShiroUtils.getUserEntity().getUsername());
			// 操作时间
			kwh.setOptime(new Date());
			// 备注
			kwh.setRemarks(null);

			devicekwhDao.save(kwh);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 3）修改
	 */
	@Override
	public void update(DeviceEntity device) {
		String isdouble = device.getIsdouble();
		// 参数名称
		String paraname = "";
		// 参数编码
		String paracode = "";
		HashMap<String, String> map = null;
		if ("1".equals(isdouble)) {
			map = Devicepara.singleMap;
		} else if ("2".equals(isdouble)) {
			map = Devicepara.doubleMap;
		}
		Iterator<Map.Entry<String, String>> it = map.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, String> entry = it.next();
			paracode += entry.getKey() + ",";
			paraname += entry.getValue() + ",";
		}
		device.setParaname(paraname);
		device.setParacode(paracode);
		deviceDao.update(device);
	}
	/**
	 *换表
	 */
	@Override
	public void updateChange(DeviceEntity device) {
		String newcode = device.getNewcode();
		
		
		
		device.setCode(newcode);
		deviceDao.update(device);
	}

	@Override
	public void delete(Integer id) {
		deviceDao.delete(id);
	}

	@Override
	public void deleteBatch(Integer[] ids) {
		deviceDao.deleteBatch(ids);
	}
	/**
	 * 7）电价设置
	 */
	@Override
	public void updatePrice(String price, String isover, String build, String roomIds, String buildingIds) {
		Map<String, Object> map = new HashMap<String, Object>();
		if ("Y".equals(build))// 按照楼栋
		{
			map.put("buildingIds", buildingIds);

		} else {
			roomIds = roomIds.replaceAll("_room", "");

			map.put("roomIds", roomIds);
		}
		List<DeviceEntity> list = deviceDao.queryList(map);
		if (CollectionUtils.isNotEmpty(list)) {
			for (DeviceEntity device : list) {
				device.setPrice(new BigDecimal(price));
				device.setIsover(isover);
				deviceDao.update(device);
			}

		}
	}
	/**
	 * 8）用电规则设置
	 */
	@Override
	public void updateRule(String ruleId, String build, String roomIds, String buildingIds) {

		Map<String, Object> map = new HashMap<String, Object>();
		if ("Y".equals(build))// 按照楼栋
		{
			map.put("buildingIds", buildingIds);

		} else {
			roomIds = roomIds.replaceAll("_room", "");

			map.put("roomIds", roomIds);
		}
		List<DeviceEntity> list = deviceDao.queryList(map);
		if (CollectionUtils.isNotEmpty(list)) {
			for (DeviceEntity device : list) {
				device.setRuleid(StringUtil.getInt(ruleId));
				deviceDao.update(device);
			}

		}

	}
	/**
	 * 9）强制设定(可用电量)
	 */
	@Override
	public void updateFree(String free, String remarks, String build, String roomIds, String buildingIds) {

		Map<String, Object> map = new HashMap<String, Object>();
		if ("Y".equals(build))// 按照楼栋
		{
			map.put("buildingIds", buildingIds);

		} else {
			roomIds = roomIds.replaceAll("_room", "");

			map.put("roomIds", roomIds);
		}
		List<DeviceEntity> list = deviceDao.queryList(map);
		if (CollectionUtils.isNotEmpty(list)) {
			for (DeviceEntity device : list) {
				BigDecimal freeuse = device.getFreeuse();

				DevicekwhEntity kwh = new DevicekwhEntity();
				// 房间ID
				kwh.setRoomid(device.getRoomid());
				// 电表ID
				kwh.setDeviceid(device.getId());
				// 电表名称
				kwh.setDevicename(device.getName());
				// 电表号
				kwh.setDevicecode(device.getCode());
				// 操作类型
				kwh.setOptype(OpTypeEnum.qz.getValue());
				// 操作前（可用电量）
				kwh.setBefkwh(freeuse);
				// 增减数
				kwh.setChangekwh(new BigDecimal(free).subtract(freeuse));
				// 操作后（可用电量）
				kwh.setAftkwh(new BigDecimal(free));
				// 操作人
				kwh.setOpuse(ShiroUtils.getUserEntity().getUsername());
				// 操作时间
				kwh.setOptime(new Date());
				// 备注
				kwh.setRemarks(remarks);

				devicekwhDao.save(kwh);
				device.setFreeuse(new BigDecimal(free));
				device.setRemarks(remarks);
				deviceDao.update(device);
			}

		}

	}
	/**
	 * 10）手动停/通电
	 */
	@Override
	public void updateRunStop(String status, String build, String roomIds, String buildingIds) {

		Map<String, Object> map = new HashMap<String, Object>();
		if ("Y".equals(build))// 按照楼栋
		{
			map.put("buildingIds", buildingIds);

		} else {
			roomIds = roomIds.replaceAll("_room", "");
			map.put("roomIds", roomIds);
		}
		List<DeviceEntity> list = deviceDao.queryList(map);
		if (CollectionUtils.isNotEmpty(list)) {
			for (DeviceEntity device : list) {
				 device.setStatus(status);
				deviceDao.update(device);
			}

		}

	}

}
