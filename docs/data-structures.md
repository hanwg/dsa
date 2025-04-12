# Strings

## Commonly used functions

```
// convert string to chracters
char[] chracters = "string".toCharArray();
```

```
// convert character to string
char[] chracters = ...
String string = new String(characters);
```

```
// append string
stringBuilder.append("string-to-be-appended");
```

```
// prepend string
stringBuilder.insert(0, "string-to-be-prepended");
```

# Arrays

## Commonly used functions

```
// prints the array for debugging
int[] nums = ...
String string = Arrays.toString(nums);
```

```
// in-place sorting
// O(n log n)
int[] nums = ...
Arrays.sort(nums);
```

```
// populate arrays with specified value
int[] nums = ...
Arrays.fill(nums, 0);
```

```
// copy 1 array into another
System.arraycopy(int[] src, int srcStart, int[] dest, int destStart, int srclength);
```

```
// splice an array
int[] splice = Arrays.copyOfRange(int[] src, int startIndex, int endIndexExclusive)
```

## Big O

| Operation       | Big O |
|-----------------|-------|
| lookup          | O(1)  |
| insert / delete | O(n)  |

# Map / HashMap

## Commonly used functions

```
int num = ...

boolean contains = map.containsKey(num);

value = map.getOrDefault(key, defaultValue);
map.keySet();
map.values();
```

## Big O

Every operation is O(1).

# Set / HashSet

## Commonly used functions

```
Set<Integer> set = new HashSet<>();
int num =...

set.add(num);
boolean contains = set.contains(num);
set.remove(num);
```

## Big O

Every operation is O(1).

# PriorityQueue (Binary heap)

```
// smallest to largest (natural order)
PriorityQueue<Integer> queue = new PriorityQueue<>();

// custom ordering
PriorityQueue<Integer> queue = new PriorityQueue<>((Integer c1, Integer c2) ->
    Integer.compare(c2, c1) // largest to smallest
    );

// enqueue
queue.add(num);

// dequeue
int num = queue.poll();
```

## Big O

| Operation       | Big O    |
|-----------------|----------|
| poll            | O(1)     |
| insert / delete | O(log n) |
