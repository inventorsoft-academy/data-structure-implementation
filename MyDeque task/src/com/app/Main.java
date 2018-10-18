package com.app;

import java.util.Comparator;

public class Main { 

	public static void main(String[] args) {  
		
		Comparator<Integer> comp = new Comparator<Integer>() {
		      public int compare(Integer d1, Integer d2) {
		        return d1.compareTo(d2);
		      }
		};		    
		 
		int[] initData = {81, 25, 55, 37, 81, 75, 25, 81, 95, 37, 75, 100};
		MyDeque<Integer> myDeque = new MyDeque<>();
		
		for (int i = 0; i < initData.length; i++) {
			myDeque.addLast(initData[i]);
		}		 
		
		System.out.println("Source deque: ");		
		while (!myDeque.isEmpty()) {
			Integer value = myDeque.removeFirst();
			System.out.print(value + " ");
		}
		
		for (int i = 0; i < initData.length; i++) {
			myDeque.addLast(initData[i]);
		}
		
		myDeque.removeRange(2, 5);
		
		System.out.println("\n\nSource deque after removeRange(2, 5): ");
		while (!myDeque.isEmpty()) {
			Integer value = myDeque.removeFirst();
			System.out.print(value + " ");
		}
		
		for (int i = 0; i < initData.length; i++) {
			myDeque.addLast(initData[i]);
		}		
		
		myDeque.removeIf(12, 3, 2);
		
		System.out.println("\n\nSource deque after removeIf(12, 3, 2): ");
		while (!myDeque.isEmpty()) {
			Integer value = myDeque.removeFirst();
			System.out.print(value + " ");
		}
		
		for (int i = 0; i < initData.length; i++) {
			myDeque.addLast(initData[i]);
		}			 
		
		myDeque.distinct();
		
		System.out.println("\n\nSource deque after distinct(): ");
		while (!myDeque.isEmpty()) {
			Integer value = myDeque.removeFirst();
			System.out.print(value + " ");
		}
		
		for (int i = 0; i < initData.length; i++) {
			myDeque.addLast(initData[i]);
		}			 
		
		myDeque.sort(comp);
		
		System.out.println("\n\nSource deque after sort(): ");
		while (!myDeque.isEmpty()) {
			Integer value = myDeque.removeFirst();
			System.out.print(value + " ");
		}		 
		
	}

}
