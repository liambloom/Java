1. If you need to add stuff to the start of middle
2. ArrayList, because you are accessing, but not shifting, values in the middle of the list
3. An iterator iterates over every element in a list. It is used with linked lists because getting the value of arbitrary indexes
4.  ```java
    // Given variables
    final Integer[] a = { 1, 1, 3, 5, 5, 5, 5, 7, 7, 11 };
    final LinkedList<Integer> list = new LinkedList<>(Arrays.asList(a));
    // Self-check code
    int duplicates = 0;
    int previous;
    final Iterator<Integer> itr = list.iterator();
    previous = itr.next();
    while (itr.hasNext()) {
        final Integer current = itr.next();
        if (previous == current)
            duplicates++;
        previous = current;
    }
    ```
5.  ```java
    // Given variables
    final String[] a = { "Alpha", "Baker", "Foxtrot", "Tango", "Whiskey" };
    final LinkedList<String> list = new LinkedList<>(Arrays.asList(a));
    final String insert = "Charlie";
    // Self-check Code
    final Iterator<String> iter = list.iterator();
    for (int i = 0; iter.hasNext(); i++) {
        if (iter.next().compareTo(insert) > 0) {
            list.add(i, insert);
            System.out.println(list);
            return;
        }
    }
    list.add(insert); // Adds it to the list if it belongs at the end
    ```
6.  ```java
    // Instead of just making it work with Integers, I made it work with any type using generics
    public static <T> void removeAll(LinkedList<T> list, T value) {
        list.removeIf(e -> e.equals(value));
    }
    ```
7.  ```java
    public static <T> void wrapHalf(LinkedList<T> list) {
        List<T> secondHalf = list.subList(list.size() / 2, list.size());
        List<T> secondHalfClone = List.copyOf(secondHalf);
        secondHalf.clear();
        list.addAll(0, secondHalfClone);
    }
    ```
8. A specific specification for the things a type can do, but not necessarily how it does it (so like an interface or abstract class). A `LinkedList` implements the `List` interface, which is an ADT
9.  ```java
    public static void countDuplicates(List<Integer> list) {
        int duplicates = 0;
        int previous;
        final Iterator<Integer> itr = list.iterator();
        previous = itr.next();
        while (itr.hasNext()) {
            final Integer current = itr.next();
            if (previous == current)
                duplicates++;
            previous = current;
        }
    }
    ```
10. Sets are good for places where you don’t want to have any duplicates, and, because of this, will sometimes take up less space
