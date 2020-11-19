package assignment5.Participants;

import assignment5.Communication.MQueue;

public class Participant extends Thread {
    private static int currId = 0;

    private final int id;
    private final MQueue mq;

    public Participant(MQueue mq) {
        this.id = getCurrId();
        this.mq = mq;
    }

    public void sleep() {
        try {
            Thread.sleep(2000);
        } catch (Exception e) {
            System.out.println(e.getClass() + " happened with id: " + id);
        }
    }

    public MQueue getMq() {
        return mq;
    }

    public static int getCurrId() {
        return currId++;
    }

    @Override
    public String toString() {
        return "Participant id: " + id;
    }
}
