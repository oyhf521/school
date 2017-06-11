package io.school.dao;

import io.school.entity.TokenEntity;

/**
 * 用户Token
 * 
 * @author school
 * @email 
 * @date 2017-03-23 15:22:07
 */
public interface TokenDao extends BaseDao<TokenEntity> {
    
    TokenEntity queryByUserId(Long userId);

    TokenEntity queryByToken(String token);
	
}
