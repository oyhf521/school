package io.school.dao;

import java.util.List;

import io.school.entity.RoomEntity;

/**
 * 房间
 * 
 * @author admin
 * @email 125957265@qq.com
 * @date 2017-06-02 23:50:07
 */
public interface RoomDao extends BaseDao<RoomEntity> {
	List<RoomEntity> listRoomByBuild(Integer buildingid);
}
