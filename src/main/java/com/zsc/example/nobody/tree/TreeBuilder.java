package com.zsc.example.nobody.tree;


import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;



public class TreeBuilder {  
	  
    @SuppressWarnings("unchecked")  
    private List<Node> buildListToTree(List<Node> dirs) {  
        List<Node> roots = findRoots(dirs);  
        //集合相减
        List<Node> notRoots = (List<Node>) CollectionUtils.subtract(dirs, roots);  
        for (Node root : roots) {  
            root.setChildren(findChildren(root, notRoots));  
        }  
        return roots;  
    }  
  
    public List<Node> findRoots(List<Node> allNodes) {  
        List<Node> results = new ArrayList<Node>();  
        for (Node node : allNodes) {  
            boolean isRoot = true;  
            for (Node comparedOne : allNodes) {  
                if (node.getParentId() == comparedOne.getId()) {  
                    isRoot = false;  
                    break;  
                }  
            }  
            if (isRoot) {  
                node.setLevel(0);  
                results.add(node);  
                node.setRootId(node.getId());  
            }  
        }  
        return results;  
    }  
  
    @SuppressWarnings("unchecked")  
    private List<Node> findChildren(Node root, List<Node> allNodes) {  
        List<Node> children = new ArrayList<Node>();  
  
        for (Node comparedOne : allNodes) {  
            if (comparedOne.getParentId() == root.getId()) {  
                comparedOne.setParent(root);  
                comparedOne.setLevel(root.getLevel() + 1);  
                children.add(comparedOne);  
            }  
        }  
        List<Node> notChildren = (List<Node>) CollectionUtils.subtract(allNodes, children);  
        for (Node child : children) {  
            List<Node> tmpChildren = findChildren(child, notChildren);  
            if (tmpChildren == null || tmpChildren.size() < 1) {  
                child.setLeaf(true);  
            } else {  
                child.setLeaf(false);  
            }  
            child.setChildren(tmpChildren);  
        }  
        return children;  
    }  
      
    private List<Node> getLeafChildren(List<Node> resultList, List<Node> children){  
        for(Node node : children){  
            if(node.isLeaf()){  
                resultList.add(node);  
            }else{  
                getLeafChildren(resultList, node.getChildren());  
            }  
        }  
        return resultList;  
    }  
  
    public static void main(String[] args) {  
        TreeBuilder tb = new TreeBuilder();  
        List<Node> allNodes = new ArrayList<Node>();  
        allNodes.add(new Node(1, 0, "节点1"));  
        allNodes.add(new Node(2, 0, "节点2"));  
        allNodes.add(new Node(3, 0, "节点3"));  
        allNodes.add(new Node(4, 1, "节点4"));  
        allNodes.add(new Node(5, 1, "节点5"));  
        allNodes.add(new Node(6, 1, "节点6"));  
        allNodes.add(new Node(7, 4, "节点7"));  
        allNodes.add(new Node(8, 4, "节点8"));  
        allNodes.add(new Node(9, 5, "节点9"));  
        allNodes.add(new Node(10, 5, "节点10"));  
        allNodes.add(new Node(11, 7, "节点11"));  
        allNodes.add(new Node(12, 7, "节点12"));  
        // 显示所有节点  
        List<Node> roots = tb.buildListToTree(allNodes);  
        for (Node n : roots) {  
            System.out.println(n);  
        }  
        System.out.println("------------------");  
        // 查找所有子节点  
       /* List<Node> children = tb.findChildren(new Node(1, 0, "节点1"), allNodes);  
        for (Node n : children) {  
            System.out.println(n);  
        }  
        // 查找所有叶子节点  
        System.out.println("------------------");  
        List<Node> resultList = tb.getLeafChildren(new ArrayList<Node>(), children);  
        for (Node n : resultList) {  
            System.out.println(n);  
        }  */
          
    }  
}  