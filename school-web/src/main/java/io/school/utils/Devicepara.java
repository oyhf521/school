/**
 * 
 */
package io.school.utils;

import java.util.HashMap;

/**
 * @author ouyanghf
 *
 */

public class Devicepara {

	public static HashMap<String, String> singleMap = new HashMap<String, String>();
	public static HashMap<String, String> doubleMap = new HashMap<String, String>();

	public static String isSingle = "1"; // 单线
	public static String isDouble = "2"; // 双线

	static {
		singleMap.put("kW", "功率");
		singleMap.put("kWh", "电量");
		doubleMap.put("mainKW", "主线功率");
		doubleMap.put("mainKWh", "主线电量");
		doubleMap.put("viceKW", "副线功率");
		doubleMap.put("viceKWh", "副线电量");
	}
	public static String run = "01"; //通电
	public static String stop = "02"; // 停电
}
