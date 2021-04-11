1\. Both are used to store a varying number of elements using the `List` interface, but they do it differently. An array list stores data in an array, everything is listed sequentially in a block of memory, and when that block is full, a new, larger block is allocated and the old array is moved there. A linked list only holds pointers to the first and last elements in the list, and they are stored in `Node`s. Each node points its contained value and the next node in the list (also the previous node if it's a double linked list), and they are not necessarily stored sequentially in memory.

15\. 
```java
// Initial
list = 1 -> 2 -> 3
temp = 4 -> 5 -> 6

// Solution
list.next.next.next = temp; // 3 -> 4
temp = list.next; // temp = 2
list = temp.next.next.next; // list = 5
list.next = temp.next // 5 -> 3
list.next.next.next = temp; // 4 -> 2
temp.next = null; // 2 -> null

// Result
list = 5 -> 3 -> 4 -> 2
```

19\. The middle is most expensive. The list contains pointers to the head and tail of the list, so adding to either the start of end has a time complexity of O(1). By comparison, to get to the middle of the list, you need to iterate through it to find the element at the specified index, which means it has a worst case time complexity of O(N).

26\. `front == null` checks if the list is empty. `front.data >= value` checks if the value to be inserted goes before the current head. `current.next != null` checks if we have reached the end of the list. `current.next.data < value` checks if this is where the value goes.

27\. You have two pointers to consecutive values in the list, `current` and `prev`. This allows you to go along the list, comparing things without having to keep doing `current.next.data`, which can be confusing, and have access to both values.

28\. 
```java
public int sum() {
    int sum = 0;
    for (Node e = head; e != null; e = e.next)
        sum += e.data;
    return sum;
}

public double average() {
    return size() == 0 ? 0.0 : (double) sum / size()
}
```