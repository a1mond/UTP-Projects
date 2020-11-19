package assignment5.Communication;

public class Response extends Message {
    private final Request request;
    private final int c1, c2;

    public Response(Request r) {
        super(r.getReq(), r.getMp());
        request = r;
        c1 = r.getC1();
        c2 = r.getC2();
    }

    private int calc() {
        return c1 + c2;
    }

    @Override
    public String toString() {
        return super.toString() + "[" + calc() + "] REQUEST ID: [" + request.getId() + "]";
    }
}
