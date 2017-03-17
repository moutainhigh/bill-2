package com.herongtech.console.service.common.dayend.common;

import java.util.ArrayList;
import java.util.List;

import com.herongtech.console.domain.common.bean.TaskPool;


public class CreateDigraph {

    private List<TaskNode> nodes = new ArrayList<TaskNode>();

    public CreateDigraph(List<TaskPool> data) {
        if (data == null) {
            data = new ArrayList<TaskPool>();
        }
        for (int i = 0; i < data.size(); i++) {
            TaskNode node = new TaskNode(data.get(i));
            nodes.add(node);
        }
    }

    /**
     * 
     * @param i  第i个节点 
     */
    public void create(int i) {
        if (i < nodes.size()) {
            TaskNode node = (TaskNode) nodes.get(i);
            searchParentByNode(nodes, node);
            searchChildByNode(nodes, node);
            this.create(i + 1);
        }
    }

    /**
     * 
     * 寻找当前节点的父节点
     * @param data 集合数据
     * @param node 当前节点
     * @return  父节点集合
     */
    public void searchParentByNode(List<TaskNode> data, TaskNode node) {
        String NodeCodes = node.getData().getParentTaskNo();/* 获得当前节点的父节点编号 */
        for (int i = 0; i < data.size() && !"0".equals(node.getData().getParentTaskNo()); i++) {
            TaskPool TaskPool = data.get(i).getData();
            /* 判断当前节点是否是要找的父节点 */
            if (NodeCodes.indexOf(TaskPool.getTaskNo()) != -1) {
                node.getParentNodes().add(data.get(i));
            }
        }
    }

    /**
     * 寻找当前节点的子节点
     * @param data  集合数据
     * @param node  当前节点
     * @return  子节点集合
     */
    public void searchChildByNode(List<TaskNode> data, TaskNode node) {
        String NodeCode = node.getData().getTaskNo();/* 获得当前节点的编号 */
        for (int i = 0; i < data.size(); i++) {
            TaskPool TaskPool = (data.get(i)).getData();
            /* 判断当前节点是否是要找的父节点 */
            if (TaskPool.getParentTaskNo().indexOf(NodeCode) != -1) {
                node.getChildNodes().add(data.get(i));
            }
        }
    }

    /**
     * 根据当前节点搜索头节点
     * @param list  集合数据
     * @param node  当前节点
     * @return 开始节点节点
     */
    public void searchStartNodes(List<TaskNode> list, TaskNode node) {
        if (list == null) {
            return;
        }
        if (node.getParentNodes().size() != 0) {
            for (int i = 0; i < node.getParentNodes().size(); i++) {
                this.searchStartNodes(list, node.getParentNodes().get(i));
            }
        } else {
            list.add(node);
        }
    }

    /**
     * 根据当前节点搜索尾节点
     * @param list  尾节点的集合
     * @param node  当前节点
     * @return
     */
    public void searchEndNodes(List<TaskNode> list, TaskNode node) {
        if (list == null) {
            return;
        }
        if (node.getChildNodes().size() != 0) {
            for (int i = 0; i < node.getChildNodes().size(); i++) {
                this.searchEndNodes(list, node.getChildNodes().get(i));
            }
        } else {
            list.add(node);
        }
    }

    /**
     * @return the nodes
     */
    public List<TaskNode> getNodes() {
        return nodes;
    }

    /**
     * 根据交易码从交易流程中获取交易信息
     * @param id   交易码
     * @return     交易信息
     */
    public TaskNode getTaskNodeById(String id) {
        TaskNode taskNode = null;
        if (id == null) {
            return null;
        }
        for (int i = 0; i < nodes.size(); i++) {
            taskNode = (TaskNode) nodes.get(i);
            if (id.equals(taskNode.getNodeNo())) {
                break;
            }
        }
        return taskNode;
    }

    /**
     * 根据头节点奖图转化成对应的html标签文本信息
     * @param startNodes  头节点
     * @param htmlBuf  存放html标签的缓存区单元
     */
    public void dataTranferMap(List<TaskNode> startNodes, StringBuffer htmlBuf) {

        List<TaskNode> childNodes = new ArrayList<TaskNode>();
        if (startNodes.size() != 0) {
            for (int i = 0; i < startNodes.size(); i++) {
                if (i == 0) {
                    htmlBuf.append("<tr align='center'><td>\r\n");
                }
                TaskNode node = (TaskNode) startNodes.get(i);
                if (node.getBrotherNum() == 0) {
                    TaskPool tkPool = (TaskPool) node.getData();
                    if ( "1".equals(tkPool.getTaskType())) {
                        htmlBuf.append("<tr align='center'><td><span>" + tkPool.getTaskName() + "</span></td></tr>\r\n");

                    } else {
                        htmlBuf.append("<span>" + tkPool.getTaskName() + "</span>");
                    }
                } else {
                    if ((node.getBrotherNodes().get(0)).equals(node)) {
                        htmlBuf.append("<span></span>\r\n");
                    } else {
                        htmlBuf.append("<span></span>\r\n");
                    }
                }
                for (int j = 0; j < node.getChildNum(); j++) {
                    TaskNode childNode = (TaskNode) node.getChildNodes().get(j);
                    if (!childNodes.contains(childNode)) {
                        childNodes.add(childNode);/* 添加兄弟节点 */
                    }
                }
                if (i == startNodes.size() - 1) {
                    htmlBuf.append("</td></tr>\r\n");
                }
            }

            if (childNodes.size() > 0) {

                for (int j = 0; j < startNodes.size(); j++) {
                    if (j == 0) {
                        htmlBuf.append("<tr align='center'><td>\r\n");
                    }
                    TaskNode node = (TaskNode) startNodes.get(j);
                    /* 清除当前节点存储的兄弟节点的信息 */
                    for (int i = 0; i < node.getBrotherNum() && node.getChildNum() > 0; i++) {
                        node.getBrotherNodes().remove(i);
                    }
                    /* 当前节点有孩子节点 */
                    if (node.getChildNum() > 0) {
                        TaskNode childNode = (TaskNode) node.getChildNodes().get(0);
                        if (childNodes.size() == 1 && startNodes.size() > 1) {
                            /* 判断孩子结点是否是单个节点的子节点 */
                            if (childNode.getParentNum() > 1) {
                                htmlBuf.append("<span id='uparrow" + childNode.getParentNum() + "'></span>\r\n");
                                j += childNode.getParentNum() - 1;
                            } else if (childNode.getParentNum() == 1) {
                                htmlBuf.append("<span id='downarrow" + node.getChildNum() + "'></span>\r\n");
                            }
                        } else if (childNodes.size() > 1 && startNodes.size() > 1) {
                            if (childNode.getParentNum() > 1) {
                                /* 孩子结点有多个父节点的处理 */
                                List<TaskNode> parents = childNode.getParentNodes();
                                for (int k = 0; k < parents.size(); k++) {
                                    if (!startNodes.contains(parents.get(k))) {
                                        node.getBrotherNodes().add(parents.get(k));
                                    }
                                }
                                if (node.getBrotherNum() > 0) {
                                    /* 当前节点的父节点没有全部找到 */
                                    childNodes.remove(childNode);
                                    childNodes.add(j, node);
                                    htmlBuf.append("<span ></span>\r\n");
                                } else {
                                    /* 当前节点的父节点全部找到 */
                                    htmlBuf.append("<span id='uparrow" + childNode.getParentNum() + "'></span>\r\n");
                                    j += childNode.getParentNum() - 1;
                                }
                            } else {
                                /* 孩子结点只有一个父节点的处理 */
                                htmlBuf.append("<span id='downarrow" + node.getChildNum() + "'></span>\r\n");
                            }
                        } else if (startNodes.size() == 1) {
                            htmlBuf.append("<span id='downarrow" + node.getChildNum() + "'></span>\r\n");
                        }
                    } else {
                        htmlBuf.append("<span></span>\r\n");
                        node.getBrotherNodes().add(node);
                        childNodes.add(j, node);
                    }
                    if (j == startNodes.size() - 1) {
                        htmlBuf.append("</td></tr>\r\n");
                    }
                }
                dataTranferMap(childNodes, htmlBuf);
            }
        }
        return;
    }
}
