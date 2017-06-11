package io.school.redis;

public class RedisConstants {
	public final static String	LOGIN_FAIL_NUM = "loginFailNum";	// 登录失败次数
	public final static Long	LOGIN_FAIL_CACHE_TIME = 1800L;	//登录失败缓存时间
	public final static int	LOGIN_FAIL_MAX = 3;	//失败多少次就得验证码
}
