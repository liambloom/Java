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