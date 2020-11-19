package assignment5.Communication;

import assignment5.Participants.Requestor;

public class Request extends Message {
    private final int c1, c2;

    public Request(Requestor req) {
        super(req, MPriority.random());
        c1 = (int)(Math.random() * 9999999);
        c2 = (int)(Math.random() * 9999999);
    }

    public int getC1() {
        return c1;
    }

    public int getC2() {
        return c2;
    }

    @Override
    public String toString() {
        return super.toString() + "[" + c1 + ", " + c2 + "]";
    }
}
