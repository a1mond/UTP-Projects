package assignment5.Communication;

import java.util.PriorityQueue;
import java.util.Queue;

public class MQueue {
    private final Queue<Message> queue;

    public MQueue() {
        this.queue = new PriorityQueue<>();
    }
    public synchronized void addToQueue(Message m) {
        queue.add(m);
    }
    public synchronized Request getReqFromQueue() {
        return (Request) queue.poll();
    }
    public synchronized Response getResFromQueue() {
        return (Response) queue.poll();
    }
}
