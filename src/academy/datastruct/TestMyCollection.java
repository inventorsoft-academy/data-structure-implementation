package academy.datastruct;

public class TestMyCollection {

	public static void main(String[] args) {

		MyCollection<Integer> coll1 = new MyCollection<>();
		MyCollection<Integer> coll2 = new MyCollection<>(200);
		MyCollection<Integer> coll0 = new MyCollection<>(0);
		System.out.println(coll1); System.out.println(coll2); System.out.println(coll0);

		coll1.addFirst(1).addFirst(2).addLast(3).addLast(4);
		System.out.println(coll1);
		
		System.out.print("toArray() ");
		for( Object o : coll1.toArray() ) System.out.print(o + " "); System.out.println();
		System.out.print("empty toArray() ");
		for( Object o : coll0.toArray() ) System.out.print(o + " "); System.out.println();

		MyCollection<Integer> coll3 = new MyCollection<>(coll1);
		MyCollection<Integer> coll4 = new MyCollection<>(coll2);
		System.out.println(coll3); System.out.println(coll4);
		
		coll4.addFirst(1).addFirst(2).addLast(3).addLast(4).addFirst(5).addFirst(5).addLast(5).addFirst(1).addFirst(1);
		System.out.println(coll4);
		
		System.out.println("trimToSize() " + coll4.trimToSize());
		System.out.println("contains(5) " + coll4.contains(5));
		System.out.println("contains(10) " + coll4.contains(10));

		System.out.println(coll4);
		System.out.println("  getFirst() "+coll4.getFirst()+"  getLast() "+coll4.getLast());
		System.out.println("  setFirst(10)  setLast(-10)  addFirst(-100)");
		coll4.setFirst(10).setLast(-10).addFirst(-100);
		System.out.println(coll4);
		System.out.println("  getFirst() "+coll4.getFirst()+"  getLast() "+coll4.getLast());

		System.out.println("  removeFirst() "+coll4.removeFirst()+"  removeLast() "+coll4.removeLast()+"  removeFirst() "+coll4.removeFirst());
		System.out.println(coll4);

		System.out.println("sort() " + coll4.sort());
		System.out.println("distinct() " + coll4.distinct());
		System.out.println("removeMultipleEntries() " + coll4.removeMultipleEntries(1,3));
		System.out.println("removeIf() " + coll4.removeIf(5,1,3));

		MyCollection<String> collS = new MyCollection<>();
		collS.addFirst("a").addFirst("bb").addLast("ccc").addLast("dddd").addFirst("eeeee").addFirst("ffffff").addLast("z").addFirst("yy").addFirst("xxx");
		System.out.println(collS);
		System.out.println("indexOf(\"ccc\") " + collS.indexOf("ccc"));
		System.out.println("indexOf(\"abc\") " + collS.indexOf("abc"));
		System.out.println("contains(\"yy\") " + collS.contains("yy"));
		System.out.println("sort() comparator " + collS.sort( (s1,s2) -> s1.length()-s2.length() ));
		System.out.println("sort() comparable " + collS.sort());
		System.out.println("removeIf() " + collS.removeIf(9,3,3).trimToSize());

		MyCollection<Object> collObj = new MyCollection<>();
		collObj.addFirst(new Object()).addFirst(new Object()).addLast(new Object());
		System.out.println(collObj);
		try {
			System.out.println("sort() Object isn't comparable " + collObj.sort());
		} catch (UnsupportedOperationException e) {
			System.err.println(e.getMessage());
		}
	}
}
