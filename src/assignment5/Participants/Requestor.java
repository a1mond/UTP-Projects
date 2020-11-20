package assignment5.Participants;

import assignment5.Communication.MQueue;
import assignment5.Communication.Request;
import assignment5.Communication.Response;

public class Requestor extends Participant {
    private final MQueue res_q;

    public Requestor(MQueue mq) {
        super(mq);
        this.res_q = new MQueue();
    }

    @SuppressWarnings("InfiniteLoopStatement")
    @Override
    public void run() {
        while (true) {
            Request r = new Request(this);
            System.out.println("REQUESTOR RUNNING ID: " + getId() + " ADDING REQUEST: [" + r + "]");
            getMq().addToQueue(r);

            boolean consumed = false;
            while (!consumed) {
                Response res = res_q.getResFromQueue();
                if (res != null)
                    consumed = true;
                sleep();
            }
        }
    }

    public MQueue getResQueue() {
        return res_q;
    }
}
