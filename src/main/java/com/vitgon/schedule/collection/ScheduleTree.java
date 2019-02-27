package com.vitgon.schedule.collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.vitgon.schedule.dto.ScheduleDto;
import com.vitgon.schedule.util.ScheduleUtil;

public class ScheduleTree {
	
	Node rootNode;
	
	public ScheduleTree() {
		rootNode = new Node();
	}
	
	public ScheduleTree(Object value) {
		rootNode = new Node(value);
	}
	
	private class Node {
		Object value;
		List<Node> children;
		
		Node() {
		}
		Node(Object value) {
			this.value = value;
		}

		@Override
		public String toString() {
			return "Node [value=" + value + ", children=" + children + "]";
		}
	}

	@Override
	public String toString() {
		return "DayTree [rootNode=" + rootNode + "]";
	}
	
	/**
	 * Get node by path [dayName, lessonNum, weekType]
	 * If node doesn't exist OR any node from path doesn't exist, return null.
	 * If node exists then return Node.
	 * 
	 * @param dayName
	 * @param lessonNum
	 * @param weekType
	 * @return
	 */
	public ScheduleDto get(String dayName, int lessonNum, String weekType) {
		ScheduleDto schedulePOJO = null;
		List<Object> mutablePathList = new ArrayList<>(Arrays.asList(dayName, lessonNum, weekType));
		
		Node foundedNode = getElement(mutablePathList, rootNode.children);
		if (foundedNode == null) {
			return null;
		}
		schedulePOJO = (ScheduleDto) foundedNode.value;
		
		return schedulePOJO;
	}
	
	/**
	 * Recursively search for node with given path
	 * 
	 * @param path
	 * @param childrenNodes
	 * @return
	 */
	public Node getElement(List<Object> path, List<Node> childrenNodes) {
		
		if (path.size() == 0) {
			Node targetNode = childrenNodes.get(0);
			return targetNode;
		}
		
		Node foundedNode = null;
		
		for (Node node : childrenNodes) {
			if (node.value.equals(path.get(0))) {
				path.remove(0);
				foundedNode = getElement(path, node.children);
				break;
			}
		}
		return foundedNode;
	}
	
	
	/**
	 * Add new Node with provided path [dayName, lessonNum, weekType]
	 * 
	 * @param dayName
	 * @param lessonNum
	 * @param weekType
	 * @param schedulePOJO
	 */
	public void add(String dayName, int lessonNum, String weekType, ScheduleDto schedulePOJO) {
		validateArguments(lessonNum, weekType);
		List<Object> mutablePathList = new ArrayList<>(Arrays.asList(dayName, lessonNum, weekType));
		add(mutablePathList, schedulePOJO);
	}
	
	
	/**
	 * Validate if provided arguments are valid
	 * 
	 * @param lessonNum
	 * @param weekType
	 */
	public void validateArguments(int lessonNum, String weekType) {
		if (lessonNum < 1 || lessonNum > 20) {
			throw new IllegalArgumentException("Lesson number must be in range from 1 to 20");
		}
		
		if (!weekType.equals(ScheduleUtil.WEEK_UP) && !weekType.equals(ScheduleUtil.WEEK_DOWN)) {
			throw new IllegalArgumentException("Unknown week type. Use WEEK_ constant to pass week type!");
		}
	}
	
	/**
	 * Add Object using path
	 * 
	 * @param path
	 * @param object
	 */
	public void add(List<Object> path, Object object) {
		rootNode.children = addNode(rootNode.children, path, object);
	}
	
	/**
	 * Add node recursively to the current nodesList.
	 * nodesList is a parent node children
	 * 
	 * @param nodesList
	 * @param path
	 * @param value
	 * @return
	 */
	public List<Node> addNode(List<Node> nodesList, List<Object> path, Object value) {
		if (nodesList == null) {
			nodesList = new ArrayList<>();
		}
		
		boolean nodeExists = false;
		Node targetNode = null;
		
		// if we are on the penultimate node we need just to create node with [Object value]
		// and put it into the current node children list
		if (path.size() == 0) {
			return addLastNode(nodesList, value);
		}
		
		// get Node with such value if it exists
		for (Node node : nodesList) {
			if (node.value.equals(path.get(0))) {
				nodeExists = true;
				targetNode = node;
				break;
			}
		}
		
		// create node with value provided in the path
		// if Node with such value doesn't exist
		if (targetNode == null) {
			targetNode = new Node(path.get(0));
		}
		
		// remove value that we used to create node
		path.remove(0);
		// add new node with next path value to the list of children of current node
		targetNode.children = addNode(targetNode.children, path, value);
		
		// if current node doesn't exist then we need to 
		// add current node with updated children to parent node children list
		if (!nodeExists) {
			nodesList.add(targetNode);
		}
		
		return nodesList;
	}
	
	/**
	 * In such tree we need last Node to be the only one in the list.
	 * So if we add another [Object value] with the same List<Object> path
	 * then old [Object value] will be replaced with new [Object value]
	 * 
	 * @param nodesList
	 * @param value
	 * @return
	 */
	public List<Node> addLastNode(List<Node> nodesList, Object value) {
		// remove this line if you want parent node to have many children nodes
		nodesList = new ArrayList<>();
		
		Node nodeWithValue = new Node(value);
		nodesList.add(nodeWithValue);
		return nodesList;
	}
}
