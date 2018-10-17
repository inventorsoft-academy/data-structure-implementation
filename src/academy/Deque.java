package academy;

import java.util.*;

class Deque <Item> {
    private Item[] values;
    private int size;
    private int itemCount, firstPos, lastPos;
    private final int MIN_SIZE = 2;

    public Deque() {
        values = (Item[]) new Object[MIN_SIZE];
        size = MIN_SIZE;
        itemCount = 0;
        firstPos = 0;
        lastPos = 0;
    }

    public boolean isEmpty() {
        return size() == 0;
    }
    public int size() {
        return itemCount;
    }

    public void pushFront(Item data) {
        if (data == null) {
            throw new NullPointerException("You cannot add null to deque!");
        }
        if (isEmpty()) {
            pushBack(data);
        } else if(firstPos == 0) {
            resize(size);
            values[--firstPos] = data;
            itemCount++;
        } else {
            values[--firstPos] = data;
            itemCount++;
        }
    }

    public void pushBack(Item data) {
        if (isEmpty()) {
            lastPos++;
            values[lastPos] = data;
            firstPos = lastPos;
            itemCount++;
        } else if (lastPos == (size - 1)) {
            resize(size);
            values[lastPos++] = data;
            itemCount++;
        } else {
            values[lastPos++] = data;
            itemCount++;
        }
    }

    public Item popLast() {
        Item data;
        if(isEmpty())
            throw new NoSuchElementException("Deque's empty!");
        data = values[lastPos];
        values[lastPos] = null;
        lastPos--;
        itemCount--;
        return data;
    }

    public Item popFirst() {
        Item data;
        if(isEmpty())
            throw new NoSuchElementException("Deque's empty!");
        data = values[firstPos];
        values[firstPos] = null;
        firstPos++;
        itemCount++;
        return data;
    }

    public ArrayList<Item> asList() {
        ArrayList<Item> list = new ArrayList<Item>();
        for (int i = firstPos; i < lastPos; i++) {
            list.add(values[i]);
        }
        return list;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < values.length; i++) {
            str.append(values[i] + ", ");
        }
        return str.toString();
    }

    private void resize(int currentSize) {
        int newSize = currentSize * currentSize;
        int start = (currentSize * (currentSize - 1)) / 2 ;
        Item[] newArr = (Item[]) new Object[newSize];
        System.arraycopy(values, firstPos, newArr, start, itemCount);
        size = newSize;
        firstPos = start;
        lastPos = start + itemCount;
        values = newArr;
    }

    public static void main(String[] args) {
        Deque<Integer> deq = new Deque<>();

        deq.pushBack(6);
        deq.pushBack(7);
        deq.pushBack(7);
        deq.pushBack(7);
        deq.pushBack(7);
        deq.pushBack(8);

        System.out.println(deq.toString());
        Integer a = deq.popFirst();
        a = deq.popLast();
        System.out.println(deq.toString());

        ArrayList<Integer> arrayList = deq.asList();
        System.out.println(arrayList.toString());
    }

}


