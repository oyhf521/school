package io.school.service;

import io.school.entity.RoomEntity;
import io.school.utils.JsonComboTree;

import java.util.List;
import java.util.Map;

/**
 * 房间
 * 
 * @author admin
 * @email 125957265@qq.com
 * @date 2017-06-02 23:50:07
 */
public interface RoomService {
	
	RoomEntity queryObject(Integer id);
	
	List<RoomEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(RoomEntity room);
	
	void update(RoomEntity room);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
	
	List<RoomEntity> listRoomByBuild(Integer buildingId);

	List<JsonComboTree> roomListTree();
}
