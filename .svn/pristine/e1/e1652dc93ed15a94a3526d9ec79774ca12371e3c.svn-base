package com.herongtech.console.service.common.machinestatus;

import java.util.ArrayList;
import java.util.List;


public class StatusNode {

    private StatusDict data;
    private StatusNode parentNode;
    private List<StatusNode> childNodes = new ArrayList<StatusNode>();
    private List<StatusNode> brotherNodes =  new ArrayList<StatusNode>();
    private int nodeNo;
    private Rect rect;
    
    public StatusNode(StatusDict data){
        this.data  = data;
    }


    
    public StatusDict getData() {
        return data;
    }


    
    public StatusNode getParentNode() {
        return parentNode;
    }



    
    public void setParentNode(StatusNode parentNode) {
        this.parentNode = parentNode;
    }



    public List<StatusNode> getChildNodes() {
        return childNodes;
    }

    public void setChildNodes(List<StatusNode> childNodes) {
        this.childNodes = childNodes;
    }
    
    public List<StatusNode> getBrotherNodes() {
        return brotherNodes;
    }
    
    public void setBrotherNodes(List<StatusNode> brotherNodes) {
        this.brotherNodes = brotherNodes;
    }
    
    /**
     * 获取子节点数
     * @return the childNum  子节点
     */
    public int getChildNum() {
        return this.childNodes.size();
    }
    
    
    /**
     * 获取兄弟节点数
     * @return the brotherNum  兄弟节点数
     */
    public int getBrotherNum() {
        return this.brotherNodes.size();
    }

    
    public int getNodeNo() {
        return nodeNo;
    }



    
    public void setNodeNo(int nodeNo) {
        this.nodeNo = nodeNo;
    }



    public Rect getRect() {
        return rect;
    }

    
    public void setRect(Rect rect) {
        this.rect = rect;
    }
    
}
class Rect{
    int left;
    int top;
    int width;
    int hight;
    public Rect(int left, int top, int width, int hight) {
        this.left = left;
        this.top = top;
        this.width = width;
        this.hight = hight;
    }
    
}
