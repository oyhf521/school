package io.school.utils;

import java.io.Serializable;

@SuppressWarnings("serial")
public class LabelValue implements Serializable {
	private String id;
	private String label;
	private String value;
	private String code;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public LabelValue() {
	}

	public LabelValue(String label, String value) {
		this.label = label;
		this.value = value;
	}

	public LabelValue(String id, String label, String value) {
		this.id = id;
		this.label = label;
		this.value = value;
	}

	public String getLabel() {
		return this.label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
