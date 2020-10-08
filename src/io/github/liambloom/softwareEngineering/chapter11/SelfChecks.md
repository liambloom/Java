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
11. A `TreeSet` is better if you want your values sorted, while a `HashSet` is more efficient (`HashSet` is O(1) while a worst case scenario for `TreeSet` is O(n)).
12. You use an iterator
13. - 12
    - 9
    - 999
14. | Operation    | Method                                                                                                                             |
    | ------------ | ---------------------------------------------------------------------------------------------------------------------------------- |
    | Union        | [`Set#addAll()`](https://docs.oracle.com/en/java/javase/14/docs/api/java.base/java/util/Set.html#addAll(java.util.Collection))       |
    | Intersection | [`Set#retainAll()`](https://docs.oracle.com/en/java/javase/14/docs/api/java.base/java/util/Set.html#retainAll(java.util.Collection)) |
15. <!-- Unfortunately, you can't do a lettered list with markdown, so I had to use HTML -->
    <ol style="list-style-type: lower-alpha;">
    <li><code>[amanda]</code></li>
    <li><code>[riley]</code></li>
    <li><code>[zorah, alex, tyler, roy, charlie, phil]</code></li>
    </ol>
16. ```java
    Map<String, Short> ages = new HashMap<>();
    ages.put("Liam", 16);
    ages.put("Alyssa", 16);
    ages.put("Damien", 15);
    ```
17. You can get use the methods [`Map#keySet()`](https://docs.oracle.com/en/java/javase/14/docs/api/java.base/java/util/Map.html#keySet()) to get the keys and [`Map#values()`](https://docs.oracle.com/en/java/javase/14/docs/api/java.base/java/util/Map.html#values()) to get the values.
18. `{8=Ocho, 50=Fifty, 132=OneThreeTwo, 79=Seventy-nine, 50=Forty-one, 28=18}`
19. <ol style="list-style-type: lower-alpha;">
    <li><code>{chiq=five, deux=two, four=quatre, one=un, three=trois}</code></li>
    <li><code>{board=skate, car=drive, computer=play}</code></li>
    <li><code>{begin=end, boy=girl, evert=siskel, first=last, H=T}</code></li>
    <li><code>{cotton=rain, light=tree, seed=tree, tree=violin}</code></li>
    </ol>
20. <ol style="list-style-type: lower-alpha;">
    <li><code>[house, cast]</code></li>
    <li><code>[ball, winkie, corn, grass, emerald]</code></li>
    <li><code>[pumpkin, corn, pie]</code></li>
    <li><code>[lab, lion, corgi, emu, nyan]</code></li>
    </ol>
21. <ol style="list-style-type: lower-alpha;">
    <li><code>{bar=earth, baz=wind, foo=air, mumble=fire}</code></li>
    <li><code>{five=quatro, one=dos, three=tres}</code></li>
    <li><code>{b=years, c=seven, e=ago, g=seven}</code></li>
    </ol>
22. ```java
    // ...

    public class WordCount {
        // ...

        public static void main(String[] args) throws FileNotFoundException {
            // ...

            final SortedMap<Integer, String> reverseWordCountMap = reverseMap(wordCountMap);
            for (Integer key : new TreeSet<>(reverseWordCountMap.keySet())) {
                if (key <= OCCURRENCES)
                    break;
                System.out.println(reverseWordCountMap.get(key));
            }
        }

        public static <K, V extends Comparable<V>> SortedMap<V, K> reverseMap(final Map<K, V> map) {
            final SortedMap<V, K> reverse = new TreeMap<>();
            for (K key : map.keySet())
                reverse.put(map.get(key), key);
            return reverse;
        }

        // ...
    }
    ```