package assignment5.Participants;

import assignment5.Communication.MQueue;
import assignment5.Communication.Request;
import assignment5.Communication.Response;

public class Service extends Participant {
    public Service(MQueue mq) {
        super(mq);
    }

    @SuppressWarnings("InfiniteLoopStatement")
    @Override
    public void run() {
        while (true) {
            Request r = getMq().getReqFromQueue();
            if (r != null) {
                Response res = new Response(r);
                System.out.println(">>> SERVICE: REQUEST: (" + r + "),\n\t\t\tRESPONSE: (" + res + ")");
                r.getReq().getResQueue().offerQueue(res);
            }
        }
    }
}
