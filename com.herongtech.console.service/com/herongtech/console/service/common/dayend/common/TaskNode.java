package com.herongtech.console.service.common.dayend.common;


import java.util.ArrayList;
import java.util.List;

import com.herongtech.console.domain.common.bean.TaskPool;



/**
 * 功能说明：交易节点对象
 *
 */
public class TaskNode {
	
	private TaskPool data = null;
	
	private List<TaskNode> parentNodes =  new ArrayList<TaskNode>();
	
	private List<TaskNode> childNodes = new ArrayList<TaskNode>();
	
	private List<TaskNode> brotherNodes =  new ArrayList<TaskNode>();
	
	private String NodeNo = null;
	
	public TaskNode(TaskPool data){
		this.data  = data;
		this.NodeNo = data.getTaskNo();
	}

	/**
	 * 获取子节点列表
	 * @return the childNodes 子节点列表
	 */
	public List<TaskNode> getChildNodes() {
		return childNodes;
	}

	/**
	 * 设置子节点列表
	 * @param childNodes 子节点列表
	 */
	public void setChildNodes(List<TaskNode> childNodes) {
		this.childNodes = childNodes;
	}

	/**
	 * 获取作业信息  
	 * @return the data 作业信息
	 */
	public TaskPool getData() {
		return data;
	}

	/**
	 * 获取父节点列表
	 * @return the parentNodes  父节点列表
	 */
	public List<TaskNode> getParentNodes() {
		return parentNodes;
	}

	/**
	 * 设置父节点列表
	 * @param parentNodes 父节点列表
	 */
	public void setParentNodes(List<TaskNode> parentNodes) {
		this.parentNodes = parentNodes;
	}
	/**
	 * 获取子节点数
	 * @return the childNum  子节点
	 */
	public int getChildNum() {
		return this.childNodes.size();
	}

	/**
	 * 获取父节点数
	 * @return the parentNum 父节点数
	 */
	public int getParentNum() {
		return this.parentNodes.size();
	}

	/**
	 * 获取作业编号 
	 * @return the NodeNo 作业编号
	 */
	public String getNodeNo() {
		return this.NodeNo;
	}
	/**
	 * 设置兄弟节点列表
	 * @return the brotherNodes 兄弟节点列表
	 */
	public List<TaskNode> getBrotherNodes() {
		return brotherNodes;
	}

	/**
	 * 获取兄弟节点列表
	 * @param brotherNodes 兄弟节点列表
	 */
	public void setBrotherNodes(List<TaskNode> brotherNodes) {
		this.brotherNodes = brotherNodes;
	}

	/**
	 * 获取兄弟节点数
	 * @return the brotherNum  兄弟节点数
	 */
	public int getBrotherNum() {
		return this.brotherNodes.size();
	}


}
