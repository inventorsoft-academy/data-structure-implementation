public class Main {
    public static void main(String[] args){
        Deque<Integer> deque = new Deque<>();
        Deque<Integer> deque1 = new Deque<>();
        for(int i = 0; i < 7; i++){
            deque.addFront(i);
            deque1.addFront(i);
        }
        System.out.println(deque);
        System.out.println("Size of deque is: " + deque.size());
        for(int i = 0; i<5; i++){
            deque.addLast(i);
        }
        System.out.println(deque);
        System.out.println("Size of deque is: " + deque.size());

        deque.removeLast();
        System.out.println(deque);
        System.out.println("Size of deque after removeLast()  is " + deque.size());
        deque.removeFirst();
        System.out.println(deque);
        System.out.println("Size of deque after removFirst  is " + deque.size());

        System.out.println("getFirst() = " + deque.getFirst() +", " +  " getLast() = " + deque.getLast());

        deque.multipleRemoving(1, 3);
        System.out.println("Deque after multipleRemoving() - " + deque);
        System.out.println("Size of deque after removing  is " + deque.size());

        deque.removeIf(8, 1,2);
        System.out.println("Deque after removeIf() - " + deque);
        System.out.println("Size of deque after removing  is " + deque.size());

        deque.sort();
        System.out.println("Deque after sorting = " + deque);

        deque.distinct();
        System.out.println("Deque after distinct() = " + deque);
        System.out.println("Size of deque after distinct()  is " + deque.size());


    }

}
