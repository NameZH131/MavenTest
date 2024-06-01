package bean;

public class Serve {
    private int sId;
    private int cId;
    private int tId;
    private String score;


    public Serve() {

    }

    public Serve(int sId, int cId, int tId, String score) {
        this.sId = sId;
        this.cId = cId;
        this.tId = tId;
        this.score = score;
    }

    public int getcId() {
        return cId;
    }

    public void setcId(int cId) {
        this.cId = cId;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public int getsId() {
        return sId;
    }

    public void setsId(int sId) {
        this.sId = sId;
    }

    public int gettId() {
        return tId;
    }

    public void settId(int tId) {
        this.tId = tId;
    }

    @Override
    public String toString() {
        return "Serve{" +
                "cId=" + cId +
                ", sId=" + sId +
                ", tId=" + tId +
                ", score='" + score + '\'' +
                '}';
    }
}
