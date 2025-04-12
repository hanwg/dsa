# Arrays - 2 Pointers

## Used in following problems:

- Searching for elements in an array that fulfill a certain condition
- Partitioning an array

## Approach:

1) Start with 2 pointers, 1 at each end of the array.  
2) Use a `while` loop until they meet.
3) In each iteration, move either of the pointers towards each other

```
 . . . . . . . . . .
 ^                 ^
left ->        <- right
```

```java
public boolean binarySearch(int[] nums, int target) {
    int left = 0;
    int right = nums.length - 1;
    
    while (left <= right) {
        // do work
        // in this case, we are checking the middl[cheatsheet.md](../../dsa2/cheatsheet.md)e element between the 2 pointers
        int mid = (left + right) / 2;
        if (nums[mid] == target) {
            return true;
        }

        // move the pointers towards each other
        if (nums[mid] < target) {
            right = mid - 1;
        } else {
            left = mid + 1;
        }
    }

    return false;
}
```
---

# Arrays - Sliding window

## Used in following problems:

- Find subarrays or substrings of length *k*

## Approach:

1) Initialize the window with the first *k* elements. (i.e. Compute)
2) Iterate through the array. In each step, move the window and compute the delta.

---

# Top *k* elements

## Used in following problems:

Find the *k* largest, smallest, or most frequent elements.

## Approach:

1) Iterate through each element.
2) For each subsequent element, insert it into the heap.
3) Compare the element with the top heap element.
3) If greater (for min-heap), pop from the heap and insert the element.

---

# Lists - Fast and Slow Pointers

## Used in following problems:

- Find the middle element
- Find the *nth* element from the end of the list
- Cycle detection

## Approach:

Cycle detection example
1) Slow pointer moves 1 step while fast pointer moves 2 steps.
2) When the pointers meet, we have detected a cycle.
3) Move the slow pointer to `head`.
4) Move both slow and fast pointers 1 step until they meet again.
5) The pointers are pointing to the element with a cycle.

---

# List - In-place Reversal

## Approach:

1) Use 3 pointers: `next`, `current`, `prev`.
2) Initially, point current to the head of the list.
3) Loop
4) In each iteration,
   - Move the `next` pointer.
   - Update the next pointer of the `current` node to `prev`
   - Move the `prev` pointer to `current`.
   - Move the `current` pointer to `next`.
5) At the end of the loop, `prev` is pointing to the head of the reversed list.
```
prev         next             prev           next              prev   next
      [0] -> [1] -> [2]        [0] <- [1]    [2]         [0] <- [1]    [2] 
    current                         current                          current
```

---

# Stack -  Find the next/previous greater/smaller number

## Approach:

1) Use a stack. The stack holds all elements which we haven't found a solution for.
2) Iterate through all elements.
3) For each element, check against all elements in the stack.
4) Add the element to the stack

```java
import java.util.Stack;

public void stack(int[] nums) {
    // list of indices which we haven't found the solution
    Stack<Integer> stack = new Stack<>();
    
    for (int i = 0; i < nums.length - 1; i++) {
        while (!stack.isEmpty() && stack.peek() < nums[i]) { // replace operator accordingly
            int value = stack.pop();
            // do something
        }
        
        stack.add(i);
    }
}
```

---

# Overlapping intervals

## Used in following problems:

- Merge ranges
- Detect conflicts
- Find gaps or missing intervals

## Approach:

```
  |-----| merged
    |---| current
  |---| previous
  
|-|-|-|-|-|-|-|-|-|
```

1) Sort intervals by their `start`.
2) Create an empty list called `merged`.
3) Iterate through the intervals.
4) For each interval, check for overlap with previous interval.
   - If it overlaps, insert into `merged` and update the interval with the larger `end` value of the interval.
   - If no overlap, add the current interval into the `merged` list.
