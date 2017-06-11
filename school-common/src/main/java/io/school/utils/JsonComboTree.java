package io.school.utils;

import java.util.List;

/**
 * 类描述：构建Json数据源的数据格式，属性有id,test,children,这里名字不能够更改否则不能够读取出来
 *
 * @version 1.0
 */

public class JsonComboTree {
	// code
	public String code;
	// ID
	public String id;
	// 分类
	public String text;
	// 是否叶子节点
	public Boolean leaf;
	// 是否展开
	public Boolean expanded = false;
	// 子类
	public List<JsonComboTree> children;
	// 父ID
	public String parentId;
	// 类型
	public String type;
	// 图标
	public String iconCls;
	// 是否选中
	public Boolean checked = false;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public List<JsonComboTree> getChildren() {
		return children;
	}

	public void setChildren(List<JsonComboTree> children) {
		this.children = children;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public Boolean getLeaf() {
		return leaf;
	}

	public void setLeaf(Boolean leaf) {
		this.leaf = leaf;
	}

	public Boolean getExpanded() {
		return expanded;
	}

	public void setExpanded(Boolean expanded) {
		this.expanded = expanded;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	public Boolean getChecked() {
		return checked;
	}

	public void setChecked(Boolean checked) {
		this.checked = checked;
	}

}
