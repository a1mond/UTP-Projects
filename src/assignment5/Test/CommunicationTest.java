import assignment5.Communication.MQueue;
import assignment5.Participants.Requestor;
import assignment5.Participants.Service;

import java.util.LinkedList;
import java.util.List;

public class CommunicationTest {
    private static Service service;
    private static List<Requestor> requestorList;
    private static MQueue queue;

    private static final int COUNT = 3;

    public static void main(String[] args) {
        init();
        start();
    }
    private static void init() {
        queue = new MQueue();
        service = new Service(queue);
        requestorList = new LinkedList<>();

        for (int i = 0; i < COUNT; i++) {
            requestorList.add(new Requestor(queue));
        }
    }
    private static void start() {
        requestorList.forEach(Thread::start);
        service.start();
    }
}
