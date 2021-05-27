### 30.1
It's not *needed*, but it can improve the performance of an application, especially one that deals with asynchronous events, by executing multiple things at once, or, on a single processor system, by rapidly switching between threads. 

### 30.2
A runnable object is an object that implements the functional interface `Runnable`, which allows it to be run, usually on a different thread. Threads are pieces of a program that run simultaneously

### 30.3
It is simply a class that implements `Runnable`. You create a thread for it by calling `new Thread(task)`

### 30.4
The tasks would run sequentially, all on the main thread

### 30.5
(a) That's not how constructors work. That's infinite recursion. Fix it by replacing the body of the constructor with

```java
new Thread(this).start()
```
(b) You cannot start the same thread twice. Remove one of the lines that calls `t.start()`

### 30.6
| Method | Is instance method? | Throws `InterruptedException` | Is deprecated? |
| --- | --- | --- | --- |
| `run` | y | n | n |
| `start` | y | n | n |
| `stop` | y | n | y |
| `suspend` | y | n | y |
| `resume` | y | n | y |
| `sleep` | n | y | n |
| `interrupt` | y | n | n |
| `yield` | n | n | n |
| `join` | n | n | n |

### 30.7
If the `try-catch` block were inside the loop, `InterruptedException`s wouldn't break out of the loop

### 30.8
`Thread#setPriority(int)`, `5`

### 30.9
The while loop alternately setting the text to `"Welcome"` or `""`

### 30.10
No

### 30.11
It sends code to the main thread, which is useful when another thread needs to interact with the GUI, which can only be done from the main thread.

### 30.12
This question could be shortened to "Did you pay any attention whatsoever while reading this section?" To which the answer is yes.

### 30.13
The text flashes very quickly

### 30.14
It allows you to do many multithreaded tasks without having to manually create threads for each one (and can avoid accidentally creating too many threads)

### 30.15
`Executors.newFixedThreadPool(3)`, `threadPool.execute(task)`, `threadPool.isTerminated()`

### 30.16
Multiple threads incrementing a counter may take time between getting and setting the value, causing it to not increment correctly. You can synchronize threads by writing or calling `syncrhonized` blocks or methods

### 30.17
Yes

### 30.18
You create a lock object by instantiating a concrete subclass of the `Lock` interface. You acquire a lock by calling the `lock()` method, and release it by calling `unlock()`

### 30.19
You create a lock with the static method `Lock.newCondition()`. `await()` releases the lock, and then waits for another thread to signal it. `signal()` signals one awaiting thread. `signalAll()` signals all awaiting threads

### 30.20
Withdrawing would only wait for one deposit, ignoring whether that deposit brought it above the amount being withdrawn

### 30.21
Main doesn't catch or throw the checked exception `InteruptedException`

### 30.22
Call the `await()` method on a condition without having the lock.

### 30.23
Yes, this was how synchronization was done prior to the introduction of locks in java 5

### 30.24
It's very convoluted and doesn't make a ton of sense, but without knowing the types of the variables or the intent of the code, I can't tell if/what is wrong

### 30.25
No

### 30.26
It waits for the condition `notEmpty`

### 30.27
It waits for the condition `notFull`

### 30.28
A blocking queue is thread safe queue which has the capability to block until an element or space is available. Java has an `ArrayBlockingQueue`, a `LinkedBlockingQueue`, and a `PriorityBlockingQueue`.

### 30.29
You use `put` to add to a blocking queue, and the method blocks until there is available space in the queue if the queue is full.

### 30.30
You use `take` to remove elements from the queue, and the method blocks if the queue is empty

### 30.31
They both restrict how many threads can access a resource, but whereas a lock (generally) restricts it to just one thread, semaphores can restrict it to an arbitrary number of threads.

### 30.32
Create a semaphore that allows access by 3 threads: `new Semaphore(3)`

Acquire a semaphore: `semaphore.aquire()`

Release a semaphore: `semaphore.relese()`

### 30.33
A deadlock is when two threads are each waiting for a lock on a resource the other has, meaning that both will block permanently. One way to avoid deadlocks is to have a set order in which resources must be obtained.

### 30.34
A thread state is the state of a thread. The possible thread states are:
| State | Description |
| --- | --- |
| New | The thread exists, but has not yet been started |
| Ready | The thread has been started, but is not currently running |
| Running | The thread is running |
| Blocked | The thread is blocked (sleeping or waiting for something) |
| Finished | The thread has completed its task |

### 30.35
A synchronized collection is a collection whose methods are synchronized, meaning it can be safely accessed across threads. `ArrayList` is not synchronized, but passing it into `Collections.synchronizedList(List)` returns a synchronized list which uses the `ArrayList` internally

### 30.36
An iterator should not iterate over changing data, and so it throws a `ConcurrentModificationExceptoin` if the data is modified during iteration (the same reason you can't add/remove elements from something while iterating over it with a for-each loop)

### 30.37
A `ForJoinTask` is defined by overriding the `compute()` method `RecursiveTask` returns a value, `RecursiveAction` doesn't.

### 30.38
The `invoke` and `invokeAll` methods.

### 30.39
`Future#isDone`

### 30.40
You create a `ForkJoinPool` by calling its constructor. You place a task into it by calling the `invoke` method

