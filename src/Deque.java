import java.util.*;

class Deque <E> {
    private static int CAPACITY = 16;

    private E[] dataArr;
    private int size;

    public Deque(){
        dataArr = (E[]) new Object[CAPACITY];
    }
    public Deque(int initialCapacity){
        if(initialCapacity <= 0){
            throw new IllegalArgumentException("Invalid Capacity");
        }
        dataArr = (E[]) new Object[initialCapacity];
    }
    public int size(){
        return size;
    }
    public boolean isEmpty(){
        return size == 0;
    }
    public Deque<E> addFront(E element){
        if(size() == dataArr.length) growCapacity();
        System.arraycopy(dataArr, 0, dataArr,1, size++);
        dataArr[0] = element;
        return this;
    }
    public Deque<E> addLast(E element){
        if(size() == dataArr.length){
            growCapacity();
        }
        dataArr[size++] = element;
        return this;
    }
    public E getFirst(){
        return dataArr[0];
    }
    public E getLast(){
        return dataArr[size-1];
    }
    public E removeFirst(){
        if(isEmpty()){
            System.out.println("Deque is empty");
        }
        E removed = dataArr[0];
        System.arraycopy(dataArr, 1, dataArr,0, size--);
        return removed;
    }
    public E removeLast(){
        if(isEmpty()){
            System.out.println("Deque is empty");
        }
        E removed = dataArr[size];
        System.arraycopy(dataArr, 0, dataArr,0, size-1);
        return removed;
    }
    public Deque<E> multipleRemoving(int indexFrom, int indexTo){
        if(isEmpty()){
            throw new IndexOutOfBoundsException("Deque is empty");
        }
        if(indexFrom > indexTo || indexFrom < 0 || indexTo > size){
            throw new IndexOutOfBoundsException("Wrong indexes");
        }
        System.arraycopy(dataArr, indexTo+1, dataArr, indexFrom, size - (indexTo+1));
        size = size - indexTo - indexFrom +1;
        return this;
    }
    public Deque<E> removeIf(int elementsCount, int indexForStartRemoving, int countForRemoving){
        if(size == elementsCount){
            return multipleRemoving(indexForStartRemoving, indexForStartRemoving + countForRemoving -1);
        }
        else return this;
    }

    public Deque<E> sort(){
        if(isEmpty()||size == 1){
            return this;
        }
        Object[] newDataArr = new Object[size];
        System.arraycopy(dataArr, 0, newDataArr, 0, size);
        Arrays.sort(newDataArr);
        System.arraycopy(newDataArr, 0, dataArr, 0, size);
        return this;
    }
    public Deque<E> distinct(){
        if(isEmpty()||size == 1){
            return this;
        }
        E[] newDataArr = (E[]) new Object[dataArr.length];
        int curIndex = 0;
        for (E i: dataArr) {
            if (!contains(newDataArr, i)) {
                newDataArr[curIndex++] = i;
            }
        }
        size = curIndex;
        dataArr = newDataArr;
        return this;
    }
    public  boolean contains(E[] dataArr, E num) {
        for (E i : dataArr) {
            if (i == num) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("[");
        for(int i=0; i < size -1; i++ )
            s.append(dataArr[i]).append(",");
        if( !isEmpty() ) s.append(dataArr[size-1]);
        s.append("]");
        return s.toString();
    }
    private void growCapacity(){
        int oldCapacity = dataArr.length;
        int newCapacity = (oldCapacity * 3) / 2 + 1;
        E[] newDataArr =(E[])  new Object[newCapacity];
        System.arraycopy(dataArr, 0, newDataArr, 0, oldCapacity);
        dataArr = newDataArr;
    }
}