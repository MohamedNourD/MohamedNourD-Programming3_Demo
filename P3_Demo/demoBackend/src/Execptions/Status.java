package Execptions;

public class Status {
    private boolean done;
    private String msg;

    public Status(String msg) {
        this.done = false;
        this.msg = msg;
    }
    public Status() {
        this.done = true;
    }

    public boolean isDone() {
        return done;
    }

    public String getMsg() {
        return msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }
    public void setDone(boolean done) {
        this.done = done;
    }
}
