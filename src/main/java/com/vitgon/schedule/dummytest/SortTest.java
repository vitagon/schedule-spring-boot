package com.vitgon.schedule.dummytest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * If element 1 should be after element 2, then return -1.
 * If element 1 should be before element 2, then return 1.
 * If elements have the same order priority, then return 0.
 * 
 * 
 * @author Vitaliy
 *
 */
public class SortTest {
	
	private final static Map<Integer, String> COLORS_MAP = getColorsMap();
	
	public enum Colors {
		RED(1), GREEN(2), BLUE(3);
		
		private int num;
		
		Colors(int num) {
			this.num = num;
		}

		public int getNum() {
			return num;
		}

		public void setNum(int num) {
			this.num = num;
		}
	}
	
	public static void main(String... args) {
		long start = System.currentTimeMillis();
		List<String> colors = new ArrayList<>();
		colors.add("blue");
		colors.add("green");
		colors.add("red");
		System.out.println(colors);
		
		Collections.sort(colors, new Comparator<String>() {
			
			@Override
			public int compare(String o1, String o2) {
				String color1 = o1.toUpperCase();
				String color2 = o2.toUpperCase();
				int colorNum1 = getNum(color1);
				int colorNum2 = getNum(color2);
				
				if (colorNum1 > colorNum2) {
					return 1;
				}
				
				if (colorNum1 < colorNum2) {
					return -1;
				}
				
				return 0;
			}
		});
		System.out.println(colors);
		long end = System.currentTimeMillis();
		double period = end - start;
		System.out.println(period/1000);
		System.out.printf("It took %f seconds!", period/1_000);
	}
	
	public static int getNum(String color) {
		Map<Integer, String> colorsMap = COLORS_MAP;
		
		return colorsMap.entrySet().stream()
			.filter(x -> x.getValue().equals(color))
			.map(Map.Entry::getKey)
			.findFirst().get();
	}
	
	public static Map<Integer, String> getColorsMap() {
		Map<Integer, String> colorsMap = new HashMap<>();
		
		Arrays.asList(Colors.values()).stream()
			.forEach(x -> {
				colorsMap.put(x.getNum(), x.name());
			});
		
		return colorsMap; 
	}
}
