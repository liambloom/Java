1086
1. Hashing generates a numeric value from an object, which is generally unique or close to unique
2. d
3. It depends on the collision resolution strategy used. If it uses linear probing, it becomes full when the size is equal to the array length. A has table with chained hashing never becomes full, you can always add more elements (although it becomes progressively more inefficient).
4. The elements will no longer be in the correct locations, because the hashing algorithm depends on the capacity
5.  | `[0]` | `[1]` | `[2]` | `[3]` | `[4]` | `[5]` | `[6]` | `[7]` | `[8]` | `[9]` |
    | :-: | :-: | :-: | :-: | :-: | :-: | :-: | :-: | :-: | :-: |
    |80||2|42||35|15|95|66||
6. | `[0]` | `[1]` | `[2]` | `[3]` | `[4]` | `[5]` | `[6]` | `[7]` | `[8]` | `[9]` |
    | :-: | :-: | :-: | :-: | :-: | :-: | :-: | :-: | :-: | :-: |
    |80||2|||35|66||||
    |||42|||15|||||
    ||||||95|||||
7.  | `[0]` | `[1]` | `[2]` | `[3]` | `[4]` | `[5]` | `[6]` | `[7]` | `[8]` | `[9]` | `[10]` | `[11]` | `[12]` | `[13]` | `[14]` | `[15]` | `[16]` | `[17]` | `[18]` | `[19]` |
    | :-: | :-: | :-: | :-: | :-: | :-: | :-: | :-: | :-: | :-: | :-: | :-: | :-: | :-: | :-: | :-: | :-: | :-: | :-: | :-: |
    |||32||24|5|44|||||||X||X||17|47||
    
    Size: 6\
    Capacity: 20\
    Load Factor: 0.3
8.  | `[0]` | `[1]` | `[2]` | `[3]` | `[4]` | `[5]` | `[6]` | `[7]` | `[8]` | `[9]` | `[10]` |
    | :-: | :-: | :-: | :-: | :-: | :-: | :-: | :-: | :-: | :-: | :-: |
    |||||70|82|50|39|15|29|18|

    Size: 7\
    Capacity: 11\
    Load Factor: 7/11 &asymp; 0.6363636363636364
9. a. It's okay, but not great. It follows the rules, and has some distribution, but it's not an even distribution, as there are some numbers (like 0) that appear often, and others (like primes) that almost never appear.

    b. No, it is not distributed at all

    c. No, it is not consistent between equal instances.

10. ```java
    @Override
    public int hashCode() {
        return year + month + day;
    }
    ```
11. ```java
    @Override
    public int hashCode() {
        return name.hashCode() + age + studentId + Double.valueOf(weight).hashCode();
    }
    ```
12. | `[0]` | `[1]` | `[2]` | `[3]` | `[4]` | `[5]` | `[6]` | `[7]` | `[8]` | `[9]` | `[10]` | `[11]` | `[12]` | `[13]` | `[14]` | `[15]` | `[16]` | `[17]` | `[18]` | `[19]` |
    | :-: | :-: | :-: | :-: | :-: | :-: | :-: | :-: | :-: | :-: | :-: | :-: | :-: | :-: | :-: | :-: | :-: | :-: | :-: | :-: |
    |||||84 &rArr; Spatarshi||6 &rArr; Tina|7 &rArr; Meghan||||||33 &rArr; Kona|X|15 &rArr; Daisy||X|||

    Size: 5\
    Capacity: 20\
    Load Factor: 0.25
13. b
14. 5, 7, because the height of a tree is always &lceil;log<sub>2</sub>N&rceil;, where N is the number of nodes in the tree
15. a. Invalid because `12 < 9`, so `12` cannot be above `9`\
    b. Valid\
    c. Valid ~~Invalid because `94 < 78`, so `94` cannot be above `78`~~
16. The left and right children of the element at 8 are at 16 and 17, respectively. For 23, they are at 46 and 47
17. 1. Set the left child of `84` to `21`
    2. Switch `84` and `21`
    3. Switch `70` and `21`
18. 1. Set the right child of `70` to `7`
    2. Switch `70` and `7`
    3. Switch `21` and `7`
    4. Switch `12` and `7`
19. ```
            2
          /   \
        3       4
       / \     / \
      6   7   5   8
     /
    9
    ```
20. Remove `6`:
    ```
          2
        /   \
      3       4
     / \     / \
    9   7   5   8
    ```
    Remove `2`:
    ```
          3
        /   \
      7       4
     / \     /
    9   8   5
    ```
    Remove `8`:
    ```
        3
       / \
      5   4
     / \
    9   7
    ```
21. ```
             1
          /     \
        3         7
       / \       / \
      8   11   15   12
     / \
    14  9
    ```
22. Remove `14`:
    ```
             1
          /     \
        3         7
       / \       / \
      8   11   15   12
     /
    9
    ```
    Remove `3`:
    ```
             1
          /     \
        8         7
       / \       / \
      9   11   15   12
    ```
    Remove `11`:
    ```
             1
          /     \
        8         7
       / \       /
      9   12   15
    ```
23. Things wrong with the tree:
    - The element at index 0 is not empty
    - The tree this represents does not follow min-heap rules
    - Most of the elements in the array are not even in the tree shown in SC17

    Changes that need to be made: There is too much wrong with it for me to even tell where the mistakes are, so I certainly can't tell you how to fix them. 
24. ```
             29
           /    \
        41        30
       /  \      /  \
      55  68    37  41
     /
    80
    ```
25. `[ , 2, 3, 4, 6, 7, 5, 8, 9]`
26. `[ , 1, 3, 7, 8, 11, 15, 12, 14, 9]`
