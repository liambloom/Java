### Multiple Choice
1. C
2. A
3. D
4. B
5. B
6. D
7. D
8. B
9. ~~E~~ A
10. E
11. C
12. E
13. ~~D~~ C
14. E
15. E
16. A
17. B
18. E
19. B
20. C
21. E
22. B
23. B
24. D
25. C
26. C
27. A
28. B
29. D
30. E
31. A
32. C
33. E
34. D

### Free Response

3\. a.
```java
public void addGameToMap(ScrabblePlayer player, Game theGame) {
    if (!map.containsKey(player))
        map.put(player, new HashSet<>()); // This is better than putIfAbsent because it's lazy
	map.get(player).add(game);
}
```

b.
```java
public int countExpertWins(ScabblePlayer player) {
	int expertWins = 0;
	for (Game game : map.get(player)) {
		if (game.wonGame() && game.getOpponent().rating() > 1600)
			expertWins++;
	}
    return expertWins;
}
```

c.
```java
public void removeWeakOpponents(ScrabblePlayer player) {
	Iterator<Game> iter = map.get(player).iterator();
	while (iter.hasNext()) {
		if (iter.next().rating() < 1300)
			iter.remove();
	}
}
```

d. 

| Method                | Big-O run time |
| :-------------------: | :------------: |
| `addGameToMap`        | O(1)           |
| `countExpertWins`     | O(c)           |
| `removeWeakOpponents` | O(c)           |

4\. a.
```java
public void makeCircular() {
    if (getFirstNode() == null)
        return;
	ListNode node = getFirstNode();
	while (node.getNext() != null)
		node = node.getNext();
	getFirstNode().setNext(node);
}
```

b. 
```java
public void reverse() {
	ListNode next = getFirstNode()
	ListNode prev = null;
	while (next != null) {
		ListNode current = next;
		next = current.getNext();
		current.setNext(prev);
		prev = current;
	}
	setFirstNode(prev);
}
```

4\. a.
```java
public static int[] getStats(TreeNode root) {
	if (root == null)
		return new int[]{0, 0, 0, 0};
	int[] heads = getStats(root.getLeft());
	int[] tails = getStats(root.getRight());
	return new int[]{Math.max(heads[0], tails [0]) + 1, heads[1] + tails [1], heads[2] + tails[2] + heads[1], heads[3] + tails[3] + tails[1]};
}
```

b. 
```java
private static boolean isHTtree(TreeNode root, String path) {
    return root == null || (path.length() == 2 
        ? root.getLeft() == null && root.getRight() == null && root.getValue() > 0)
        : root.getValue() == 0 
            && (root.getLeft() != null || root.getRight() != null) 
            && isHTtree(root.getLeft(), path.equals("L") ? "LL" : "L") 
            && isHTtree(root.getRight(), path.equals("R") ? "RR" : "R")
}
```