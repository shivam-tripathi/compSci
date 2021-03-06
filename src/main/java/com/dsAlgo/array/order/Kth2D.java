package com.dsAlgo.array.order;

/**
 * Given an n x n matrix, where every row and column is sorted in non-decreasing
 * order. Find the kth smallest element in the given 2D array.
 *
 * Imagine each column (or row) as a separate horse race. Now you have to pick
 * up the top k performers. Simple way to do this is to first compare all the
 * winners of the individual race (they are the fastest in their group).
 * The fastest among these is the fastest among all. Once we find the fastest,
 * we can then consider the next fastest member of the race from which the
 * previous winner was selected. This ensures that we continuously compare the
 * n fastest representatives from n groups - and the fastest among fastest in a
 * group is the overall fastest.
 */

import com.util.Pair;

import java.util.*;

class Kth2DSolution {
    abstract class BinaryHeap <T> {
        private ArrayList<T> heap;
        BinaryHeap(ArrayList<T> arr) {
            heap = arr;
            buildHeap();
        }

        abstract boolean cmp(T a, T b);

        private void buildHeap() {
            int size = heap.size();
            for (int i = size - 1; i >= 0; i--) {
                heapifyDown(i);
            }
        }

        private void heapifyDown(int index) {
            if (index >= heap.size()) {
                return;
            }
            int leftIndex = index * 2 + 1;
            int rightIndex = index * 2 + 2;

            int rootIndex = index;

            if (leftIndex < heap.size() && cmp(heap.get(leftIndex), heap.get(rootIndex))) {
                rootIndex = leftIndex;
            }

            if (rightIndex < heap.size() && cmp(heap.get(rightIndex), heap.get(rootIndex))) {
                rootIndex = rightIndex;
            }

            if (rootIndex != index) {
                T temp = heap.get(index);
                heap.set(index, heap.get(rootIndex));
                heap.set(rootIndex, temp);
                heapifyDown(rootIndex);
            }
        }

        private void heapifyUp(int index) {
            if (index < heap.size() && index > 0 && index/2 >= 0 && cmp(heap.get(index), heap.get(index/2))) {
                T temp = heap.get(index);
                heap.set(index, heap.get(index/2));
                heap.set(index/2, temp);
                heapifyUp(index/2);
            }
        }

        T extract() {
            T top = heap.get(0);
            T bottom = heap.remove(heap.size() - 1);
            if (heap.size() > 0) {
                heap.set(0, bottom);
                heapifyDown(0);
            }
            return top;
        }

        void insert(T item) {
            heap.add(item);
            int size = heap.size();
            if (size != 0) {
                heapifyUp(size - 1);
            }
        }

        boolean isEmpty() {
            return heap.size() == 0;
        }

    }

    class MinHeap <T extends Comparable<T>> extends BinaryHeap<T> {
        MinHeap(ArrayList<T> arr) {
            super(arr);
        }

        @Override
        boolean cmp(T a, T b) {
            boolean isRelevant = false;
            if (a.compareTo(b) < 0) {
                isRelevant = true;
            }
            return isRelevant;
        }
    }

    class HeapObject implements Comparable<HeapObject> {
        int item;
        Pair<Integer, Integer> pos;
        HeapObject(int a, int b, int c) {
            item = a;
            pos = new Pair<>(b, c);
        }

        @Override
        public int compareTo(HeapObject that) {
            int res = Integer.compare(this.item, that.item);
            if (res == 0) {
                res = this.pos.compareTo(that.pos);
            }
            return res;
        }
    }

    int solve(ArrayList<ArrayList<Integer>> arr, int k) {
        int size = arr.size();
        if (size == 0) {
            return -1;
        }

        ArrayList<HeapObject> firstColumns = new ArrayList<>();
        for (int i = 0; i < arr.get(0).size(); i++) {
            firstColumns.add(new HeapObject(arr.get(i).get(0), i, 0));
        }
        MinHeap<HeapObject> minHeap = new MinHeap<>(firstColumns);

        int counter = 0;
        HeapObject obj = null;
        while(!minHeap.isEmpty() && counter < k) {
            counter++;
            obj = minHeap.extract();
            System.out.printf("%3d] %d%n", counter, obj.item);
            int x = obj.pos.getFirst();
            int y = obj.pos.getSecond() + 1;
            if (y < size) {
                minHeap.insert(new HeapObject(arr.get(x).get(y), x, y));
            }
        }

        if (obj != null && obj.pos.getSecond() < size) {
            return arr.get(obj.pos.getFirst()).get(obj.pos.getSecond());
        }
        return -1;
    }
}

class Kth2D {
    public static void main(String[] args) {
        ArrayList<ArrayList<Integer>> arr = new ArrayList<>();
        int size = 10;
        int counter = -1;
        for (int i = 0; i < size; i++) {
            ArrayList<Integer> tempArr = new ArrayList<>();
            arr.add(tempArr);
            for (int j = 0; j < size; j++) {
                tempArr.add(++counter);
                System.out.printf(" %2d ", counter);
            }
            System.out.printf("%n");
        }

        Kth2DSolution solution = new Kth2DSolution();
        solution.solve(arr, size * size);
    }
}
