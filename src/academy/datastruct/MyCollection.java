package academy.datastruct;

import java.util.Comparator;

public class MyCollection<E> {
	
    private static final int DEFAULT_CAPACITY = 10;
    private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;

    private Object[] elementData;
    private int size;

    public MyCollection() {
    	elementData = new Object[DEFAULT_CAPACITY];
    }
    public MyCollection(int initialCapacity) {
        if (initialCapacity >= 0 && initialCapacity <= MAX_ARRAY_SIZE) {
            elementData = new Object[initialCapacity];
        } else throw new IllegalArgumentException("Illegal Capacity: " + initialCapacity);
    }
    public MyCollection(MyCollection<? extends E> c) {
        size = c.size();
    	elementData = new Object[size];
        System.arraycopy(c.toArray(), 0, elementData, 0, size); 
    }

    public int size() {
        return size;
    }
    public int capacity() {
        return elementData.length;
    }
    public boolean isEmpty() {
        return size == 0;
    }
    
    public E getFirst() {
    	if ( isEmpty() ) throw new IndexOutOfBoundsException("MyCollection is empty");
    	return elementData(0); 
    }
    public E getLast() {
    	if ( isEmpty() ) throw new IndexOutOfBoundsException("MyCollection is empty");
    	return elementData(size-1); 
    }
    public MyCollection<E> setFirst(E e) {
    	if ( isEmpty() ) {
        	if (elementData.length==0) grow();
    		size = 1;
    	}
    	elementData[0] = e;
        return this;
    }
    public MyCollection<E> setLast(E e) {
    	if ( isEmpty() ) {
        	if (elementData.length==0) grow();
    		size = 1;
    	}
    	elementData[size-1] = e;
        return this;
    }
    
    public MyCollection<E> addFirst(E e) {
    	if (size==elementData.length) grow();
        System.arraycopy(elementData, 0, elementData, 1, size++); 
        elementData[0] = e;
        return this;
    }
    public MyCollection<E> addLast(E e) {
    	if (size==elementData.length) grow();
        elementData[size++] = e;
        return this;
    }
    public E removeFirst() {
    	if ( isEmpty() ) throw new IndexOutOfBoundsException("MyCollection is empty");
    	E e = elementData(0);
        System.arraycopy(elementData, 1, elementData, 0, --size); 
    	return e; 
    }
    public E removeLast() {
    	if ( isEmpty() ) throw new IndexOutOfBoundsException("MyCollection is empty");
    	return elementData(--size);
    }

    public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }
    public int indexOf(Object o) {
        if (o == null) {
            for (int i = 0; i < size; i++) if (elementData[i]==null) return i;
        } else {
            for (int i = 0; i < size; i++) if (o.equals(elementData[i])) return i;
        }
        return -1;
    }

    @Override
    public String toString() {
    	StringBuilder s = new StringBuilder("MyCollection [");
    	for(int i=0; i < (size-1); i++ )
        	s.append(elementData[i]).append(",");
    	if( !isEmpty() ) s.append(elementData[size-1]);
    	s.append("]");
    	s.append("  size=").append(size()).append("  capacity=").append(capacity());
    	return s.toString();
    }
    public Object[] toArray() {
        Object[] newElementData = new Object[size];
        System.arraycopy(elementData, 0, newElementData, 0, size); 
    	return newElementData;
    }
    public MyCollection<E> trimToSize() {
        if (size < elementData.length) {
            Object[] newElementData = new Object[size];
            System.arraycopy(elementData, 0, newElementData, 0, size); 
            elementData = newElementData;
        }
        return this;
    }

    public MyCollection<E> removeMultipleEntries(int fromIndex, int toIndex) {
    	if ( isEmpty() ) throw new IndexOutOfBoundsException("MyCollection is empty");
    	if ( fromIndex > toIndex || fromIndex < 0 || toIndex >= size ) 
    		throw new IndexOutOfBoundsException("MyCollection removeMultipleEntries wrong index");
        System.arraycopy(elementData, toIndex+1, elementData, fromIndex, size-(toIndex+1));
        size -= toIndex - fromIndex + 1 ;
        return this;
    }

    public MyCollection<E> removeIf(int conditionElementsCount, int indexForStartRemoving, int countForRemoving) {
    	if ( size == conditionElementsCount ) 
    		return removeMultipleEntries(indexForStartRemoving, indexForStartRemoving+countForRemoving-1);
    	else return this;
    }
    
    @SuppressWarnings("unchecked")
	public MyCollection<E> sort() {
    	if ( isEmpty() || size==1 ) return this;
    	if ( ! (elementData(0) instanceof Comparable) ) 
    		throw new UnsupportedOperationException("MyCollection try SORT with Comparator");
    	return sort( (Comparator<E>) Comparator.naturalOrder() );
    }
    public MyCollection<E> sort(Comparator<? super E> c) {
    	if ( isEmpty() || size==1 ) return this;
        qsort(0, size-1, c);
        return this;
    }
    private void qsort(int lowerIndex, int higherIndex, Comparator<? super E> c) {
        int i = lowerIndex;
        int j = higherIndex;
        E e = elementData(lowerIndex+(higherIndex-lowerIndex)/2);
        while (i <= j) {
            while ( c.compare(elementData(i), e) < 0 ) i++;
            while ( c.compare(elementData(j), e) > 0 ) j--;
            if (i <= j) {
            	Object temp = elementData[i];
            	elementData[i] = elementData[j];
            	elementData[j] = temp;
                i++;
                j--;
            }
        }
        if (lowerIndex < j) qsort(lowerIndex, j, c);
        if (i < higherIndex) qsort(i, higherIndex, c);
    }
    
    public MyCollection<E> distinct() {
    	if ( isEmpty() || size==1 ) return this;
        Object[] newElementData = new Object[capacity()];
        int newSize = 0;
        boolean isCopy;
        for(int i=0; i<size; i++) {
        	isCopy = false;
        	for(int j=0; j<newSize; j++)
        		if(elementData[i].equals(newElementData[j])) {
        			isCopy = true;
        			break;
        		}
        	if(!isCopy) newElementData[newSize++]=elementData[i];
        }
        elementData = newElementData;
        size = newSize;
    	return this;
    }
    
    @SuppressWarnings("unchecked")
    private E elementData(int index) {
        return (E) elementData[index];
    }
    private void grow() {
    	if (elementData.length >= MAX_ARRAY_SIZE) 
    		throw new UnsupportedOperationException("MyCollection has already reached maximum capacity");
        int oldCapacity = elementData.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1) + 1;
        if (newCapacity > MAX_ARRAY_SIZE) newCapacity = MAX_ARRAY_SIZE;
        Object[] newElementData = new Object[newCapacity];
        System.arraycopy(elementData, 0, newElementData, 0, oldCapacity); 
        elementData = newElementData;
    }
    
}
