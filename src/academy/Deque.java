package academy;

import java.util.*;

class Deque <Item extends Comparable<? super Item>> {
    private Item[] values;
    private int size;
    public int itemCount, firstPos, lastPos;
    private final int MIN_SIZE = 2;

    public Deque() {
        values = (Item[]) new Comparable[MIN_SIZE];
        size = MIN_SIZE;
        itemCount = 0;
        firstPos = 0;
        lastPos = 0;
    }

    public boolean isEmpty() { return size() == 0; }

    public int size() {
        return itemCount;
    }

    public void clear() {
        values = (Item[]) new Comparable[MIN_SIZE];
        size = MIN_SIZE;
        itemCount = 0;
        firstPos = 0;
        lastPos = 0;
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

    public Item popBack() {
        Item data;
        if(isEmpty())
            throw new NoSuchElementException("Deque's empty!");
        lastPos--;
        data = values[lastPos];
        values[lastPos] = null;
        itemCount--;
        return data;
    }

    public Item popFront() {
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

    public void sort() {
        ArrayList<Item> arr = asList();
        Collections.sort(arr);
        for(int i = 0, j = firstPos; i < arr.size(); i++) {
            values[j++] = arr.get(i);
        }
    }

    public void remove(int begin, int end) {
        ArrayList<Item> arr = asList();     //We can use vanilla arrays as well, but then we should write our own Array.addAll() method.
        if (end >= lastPos - firstPos)
            throw new IndexOutOfBoundsException("End index is too large");
        else if (begin < 0)
            throw new IndexOutOfBoundsException("Begin index is too small");
        else  {
            for(int i = 0; i < values.length; i++) {
                values[i] = null;
            }
            //newArr = arr[firstPos; begin) + arr(end; lastPos]
            ArrayList<Item>  newArr = new ArrayList<Item>();
            newArr.addAll(arr.subList(0, begin));
            //System.out.println(newArr.toString());
            newArr.addAll(arr.subList(end + 1, lastPos - firstPos));
            //System.out.println(newArr.toString());
            lastPos = firstPos + newArr.size();
            for(int i = 0, j = firstPos; i < newArr.size(); i++) {
                values[j++] = newArr.get(i);
            }
            itemCount -= (end - begin + 1); //+1 because of having value of index, that is remove(0,4) deletes only 4 items when we need 5
        }
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
        Item[] newArr = (Item[]) new Comparable[newSize];
        System.arraycopy(values, firstPos, newArr, start, itemCount);
        size = newSize;
        firstPos = start;
        lastPos = start + itemCount;
        values = newArr;
    }


    public static void main(String[] args) {
        Deque<Integer> deq = new Deque<>();
        System.out.println("--TEST OF 'REMOVE' METHOD--");
        deq.pushBack(6);
        deq.pushBack(7);
        deq.pushBack(8);
        deq.pushBack(9);
        deq.pushFront(3);
        System.out.println(deq.toString());

        deq.remove(0, 4);
        System.out.println(deq.toString());
        System.out.println(deq.itemCount);

        deq.pushBack(6);
        deq.pushBack(7);
        deq.pushBack(11);
        deq.pushBack(13);
        System.out.println(deq.toString());

        deq.remove(1, 2);
        System.out.println(deq.toString());
        deq.clear();

        System.out.println("--TEST OF 'POP' METHOD--");
        deq.pushBack(6);
        deq.pushBack(7);
        deq.pushBack(8);
        deq.pushBack(9);
        deq.pushFront(3);
        System.out.println(deq.asList());

        deq.popBack();
        System.out.println(deq);
        deq.popBack();
        System.out.println(deq);
        deq.pushBack(8);
        deq.pushFront(2);
        System.out.println(deq);
        deq.remove(0, 3);
        System.out.println(deq);

        deq.pushFront(2);
        deq.pushFront(2);
        deq.pushFront(2);
        deq.pushFront(2);
        deq.pushFront(3);
        System.out.println(deq.asList());
        deq.sort();
        System.out.println(deq.asList());

    }

}


