package io.school.dao;

import io.school.entity.UserEntity;

/**
 * 用户
 * 
 * @author school
 * @email 
 * @date 2017-03-23 15:22:06
 */
public interface UserDao extends BaseDao<UserEntity> {

    UserEntity queryByMobile(String mobile);
}
