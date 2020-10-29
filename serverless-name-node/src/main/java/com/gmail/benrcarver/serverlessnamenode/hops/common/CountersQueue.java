package com.gmail.benrcarver.serverlessnamenode.hops.common;

import java.util.ArrayDeque;
import java.util.Queue;

public class CountersQueue {
    public static class Counter {
        private final long start;
        private final long end;
        private long current;

        public Counter(long start, long end) {
            this.start = start;
            this.end = end;
            this.current = start;
        }

        public long next() {
            return current++;
        }

        public boolean hasNext() {
            return current < end;
        }

        public long getEnd() {
            return end;
        }

        public long getStart() {
            return start;
        }

        @Override
        public String toString() {
            return "Counter{" + "end=" + end + ", current=" + current + '}';
        }
    }

    public class EmptyCountersQueueException extends RuntimeException {
    }

    private int available;
    private Queue<Counter> queue;

    public CountersQueue() {
        queue = new ArrayDeque<>();
        available = 0;
    }

    public synchronized void addCounter(long start, long end) {
        addCounter(new Counter(start, end));
    }

    public synchronized void addCounter(Counter counter) {
        queue.offer(counter);
        available += counter.end - counter.start;
    }


    public synchronized long next() {
        Counter c = queue.peek();
        while (c != null) {
            if (c.hasNext()) {
                available--;
                return c.next();
            } else {
                queue.remove();
                c = queue.peek();
            }
        }
        throw new EmptyCountersQueueException();
    }

    public synchronized boolean has(int expectedNumOfIds) {
        return available >= expectedNumOfIds && expectedNumOfIds != 0;
    }

    @Override
    public String toString() {
        return "CountersQueue{" + "available=" + available + ", queue=" + queue +
                '}';
    }
}

