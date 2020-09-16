1. If you need to add stuff to the start of middle
2. ArrayList, because you are accessing, but not shifting, values in the middle of the list
3. An iterator iterates over every element in a list. It is used with linked lists because getting the value of arbitrary indexes
4.  ```java
    Integer[] a = { 1, 1, 3, 5, 5, 5, 5, 7, 7, 11 };
    LinkedList<Integer> list = new LinkedList<>(Arrays.asList(a));
    int duplicates = 0;
    int previous;
    Iterator<Integer> itr = list.iterator();
    previous = itr.next();
    while (itr.hasNext()) {
        Integer current = itr.next();
        if (previous == current)
            duplicates++;
        previous = current;
    }
    ```
