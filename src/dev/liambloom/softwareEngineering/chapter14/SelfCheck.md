1\. c only\
2\. Call stack in most programming languages and the javascript queue of things for the event loop to run.\
3\. `push` adds to the top of the stack, and `pop` removes from the end\
4\. `add` adds to the end of the queue, `remove` removes from the start\
5\. `3`\
6\. `1`\
7\. `Queue` is an interface, so it is not instantiable. You would instead instantiate a `LinkedList` or some other type that implements `Queue`.\
8\.  
```java
final Stack<String> stack = new Stack<>();
stack.push("hello");
stack.push("hi");
stack.push("goodbye");
stack.push("howdy");
```
9\. 
```java
final Stack<Integer> stack = new Stack<>();
for (int i = 100; i >= 0; i -= 2)
    stack.push(i);
```
10\.
```java
final Queue<String> q = new LinkedList<>();
q.add("alpha");
q.add("beta");
q.add("gamma");
q.add("delta");
```
11\. You must remove elements and, once you have the element you need, return them to the original collection. With a stack, you need to store the removed elements in some form of auxiliary storage (another stack works best), while with a queue, you can just add them to the end. Keep in mind, to return the queue in the same state is was given to you, you need to then remove and add back all the elements until you get to the element that was originally in front.\
12\. The biggest advantage is clarity. When I have a stack, it is very clear what purpose it serves and how it's meant to be used, whereas if I used a list *as* a stack, that wouldn't be obvious. \
13\. `[how, are, you]`\
16\. `[1, 1, 6, 6, 2, 2]`\
19\. The first problem with this is it assumes that `q` is not empty. Calling `q.remove()` at the start of the program will throw an exception if `q` is empty, and the method should only run this code after checking that `q` is not empty. The second, and much larger problem, is that the loop only iterates over half the queue. It runs until `i >= q.size()`, but since `i` is increasing at the same time that `q.size()` is decreasing, this will only iterate over half the elements in `q`. This could be fixed by replacing the `for` loop with a `while` loop that ran while `!q.isEmpty()`.\
21\. As soon as it hits an even element, it immediately puts it back on top of the stack, preventing the method from progressing further and causing an infinite loop. The odd elements should instead be added to an auxiliary stack and then, once the loop is completed, all elements from the auxiliary stack should be added back to the original one.
