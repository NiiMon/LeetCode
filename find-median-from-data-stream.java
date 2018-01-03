/*

295. Find Median from Data Stream
https://leetcode.com/problems/find-median-from-data-stream/description/


Median is the middle value in an ordered integer list. If the size of the list
is even, there is no middle value. So the median is the mean of the two middle
value.

Examples: 
[2,3,4] , the median is 3

[2,3], the median is (2 + 3) / 2 = 2.5

Design a data structure that supports the following two operations:

void addNum(int num) - Add a integer number from the data stream to the data
structure. double findMedian() - Return the median of all elements so far. 
For example:

addNum(1)
addNum(2)
findMedian() -> 1.5
addNum(3) 
findMedian() -> 2

*/

class MedianFinder {

    /** initialize your data structure here. */
    PriorityQueue<Integer> _left;
    PriorityQueue<Integer> _right;
    public MedianFinder() {
        _left = new PriorityQueue<>((o1, o2) -> o2 - o1);
        _right = new PriorityQueue<>((o1, o2) -> o1 - o2);
    }
    
    public void addNum(int num) {
        // insert
        if (_left.isEmpty() || num <= _left.peek()) {
            _left.offer(num);
        } else {
            _right.offer(num);
        }
        
        // adjust
        if (_left.size() - _right.size() == 2) {
            _right.offer(_left.poll());
        }
        if (_right.size() - _left.size() == 1) {
            _left.offer(_right.poll());
        }
    }
    
    public double findMedian() {
        if (_left.isEmpty()) {
            throw new IllegalArgumentException("No number added.");
        }
        
        if (_left.size() == _right.size()) {
            return (_left.peek() + _right.peek()) / 2.0;
        } else {
            return _left.peek() * 1.0;
        }
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */

