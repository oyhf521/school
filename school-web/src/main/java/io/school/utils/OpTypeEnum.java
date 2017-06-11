package io.school.utils;

import java.util.ArrayList;
import java.util.List;

import io.school.utils.LabelValue;

public enum OpTypeEnum {
	dr("1", "导入"), 
	xz("2", "新增"), 
	js("3", "结算"), 
	cz("4", "充值"), 
	hb("5", "换表"), 
	qz("6", "强制设定"); 
	
	// 成员变量
	private String value;
	private String label;
	
	private OpTypeEnum(String value, String label) {
		this.value = value;
		this.label = label;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	
	public static String getLabel(String value) {
		for (OpTypeEnum m : OpTypeEnum.values()) {
			if (m.getValue().equals(value)) {
				return m.label;
			}
		}
		return null;
	}

	public static List<LabelValue> asList() {
		List<LabelValue> list = new ArrayList<>();
		for (OpTypeEnum d : OpTypeEnum.values()) {
			list.add(new LabelValue(d.getLabel(), d.getValue()));
		}
		return list;
	}
	public static String getValue(String label) {
		for (OpTypeEnum m : OpTypeEnum.values()) {
			if (m.getLabel().equals(label)) {
				return m.value;
			}
		}
		return null;
	}
}
