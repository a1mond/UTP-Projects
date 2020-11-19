package assignment5.Communication;

import assignment5.Participants.Requestor;

import java.time.LocalDate;

public abstract class Message implements Comparable<Message> {
    private static int currId = 0;

    private final int id;
    private final Requestor req;
    private final MPriority mp;
    private final LocalDate date;

    public Message(Requestor req, MPriority mp) {
        this.id = getCurrId();
        this.req = req;
        this.mp = mp;
        this.date = LocalDate.now();
    }

    private synchronized static int getCurrId() {
        return currId++;
    }

    public int getId() {
        return id;
    }

    public Requestor getReq() {
        return req;
    }

    public MPriority getMp() {
        return mp;
    }

    public LocalDate getDate() {
        return date;
    }

    @Override
    public int compareTo(Message o) {
        if (o != null) {
            int res = getMp().compareTo(o.getMp());

            if (res == 0)
                res = getDate().compareTo(o.getDate());
            if (res == 0)
                return id - o.id;
            return res;
        } else
            return -1;
    }

    @Override
    public String toString() {
        return "MESSAGE ID: [" + id + "] PRIORITY: [" + mp + "] ";
    }
}
