package com.app;

import java.util.Comparator;

public class MyDeque<T> {
	
	private int size = 50;

	@SuppressWarnings("unchecked")
	private T[] data = (T[]) new Object[size]; 
	
	private int currentIndex = 0;

	public void addLast(T value) {		
		
		if (isFull()) {					
			resize(size * 2);
		}
		
		data[currentIndex] = value;
		currentIndex++;
	}
	
	public void addFirst(T value) {		
		
		if (isFull()) {					
			resize(size * 2);
		}
		
		@SuppressWarnings("unchecked")
		T[] temp = (T[]) new Object[data.length];
		
		temp[0] = value;
		
		for (int i = 1; i < data.length; i++) {
			temp[i] = data[i - 1];
		}
		
		data = temp;		
		currentIndex++;
	}
	
	public T removeFirst() {
		
		T removed = data[0];
		
		@SuppressWarnings("unchecked")
		T[] temp = (T[]) new Object[data.length]; 
		
		for (int i = 1; i < data.length; i++) {
			temp[i - 1] = data[i];
		}	
		
		currentIndex--;		
		data = temp;
		
		return removed;
	}
	
	public T removeLast() {
		
		int lastElemIndex = currentIndex - 1;
		T removed = data[lastElemIndex];
		
		@SuppressWarnings("unchecked")
		T[] temp = (T[]) new Object[data.length]; 
		
		for (int i = 0; i < lastElemIndex; i++) {
			temp[i] = data[i];
		}
		
		data = temp;		
		currentIndex--;		
		
		return removed;
	}
	
	public void removeRange(int startIndex, int endIndex) {		
		
		@SuppressWarnings("unchecked")
		T[] temp = (T[]) new Object[data.length]; 
		
		int tempIndex = 0;
		int removedCount = 0;
		
		for (int i = 0; i < currentIndex; i++) {					
			
			if ( !(i >= startIndex && i <= endIndex) ) {
				
				temp[tempIndex] = data[i];
				tempIndex++;
				
			} else {
				removedCount++;
			}
		}
		
		data = temp;				 
		currentIndex = currentIndex - removedCount;			
	}	
	
	public boolean isEmpty() {
		return currentIndex == 0;
	}	
	
	public void sort(Comparator<? super T> comp) {
		
		int lastElemIndex = currentIndex - 1;
		
		for (int k = 0; k < lastElemIndex; k++) {
			for (int i = 0; i < lastElemIndex; i++) {
				
				if ( comp.compare(data[i], data[i + 1]) > 0  ) {					 
					
					T temp = data[i];
					 data[i] = data[i + 1];
					 data[i + 1] = temp;
				}
				
			}
		}  
	}
	
	public void distinct() {
		
		@SuppressWarnings("unchecked")
		T[] temp = (T[]) new Object[data.length]; 
		
		int tempIndex = 0;
		
		for (int i = 0; i < currentIndex; i++) {
			
			if ( !contains(data[i], temp, tempIndex)) {
				temp[tempIndex] = data[i];
				tempIndex++;
			}
		}
		
		currentIndex = tempIndex;
		data = temp;		
	}
	
	public void removeIf(int elemsCount, int startIndex, int countElemsRemove) {

		if (elemsCount != size()) {
			return;
		}

		@SuppressWarnings("unchecked")
		T[] temp = (T[]) new Object[data.length];

		int tempIndex = 0;
		int removedCount = 0;

		for (int i = 0; i < currentIndex; i++) {			

			if ( !(i >= startIndex && i < startIndex + countElemsRemove) ) {
				
				temp[tempIndex] = data[i];
				tempIndex++;
				
			} else {
				removedCount++;
			}
		}

		data = temp;
		currentIndex = currentIndex - removedCount;
	}
	
	public int size() {
		return currentIndex;
	}
	
	private boolean contains(T elem, T[] array, int endIndex) {
		
		for (int i = 0; i < endIndex; i++) {			
			
			if (elem.equals(array[i])) {
				return true;
			}
		}
		
		return false;
	}
	
	private void resize(int newSize) {
		
		@SuppressWarnings("unchecked")
		T[] temp = (T[]) new Object[newSize]; 
		
		for (int i = 0; i < data.length; i++) {
			temp[i] = data[i];
		}
		
		size = newSize;
		data = temp;
	}	 
	
	private boolean isFull() {
		
		int lastIndex = data.length - 1;
		
		if (currentIndex > lastIndex) {
			return true;
		}
		
		return false;
	}	
	
}
