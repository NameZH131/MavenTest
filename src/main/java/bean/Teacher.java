package bean;

public class Teacher {
    private int tId;
    private String tName;
    private int tAge;
    private String tGender;
    private int tWorkage;
    private String tMajor;

    public Teacher() {
    }

    public Teacher(int tId, String tName, int tAge, String tGender, int tWorkage, String tMajor) {
        this.tId = tId;
        this.tName = tName;
        this.tAge = tAge;
        this.tGender = tGender;
        this.tWorkage = tWorkage;
        this.tMajor = tMajor;
    }


    public int gettId() {
        return tId;
    }

    public void settId(int tId) {
        this.tId = tId;
    }

    public String gettName() {
        return tName;
    }

    public void settName(String tName) {
        this.tName = tName;
    }

    public int gettAge() {
        return tAge;
    }

    public void settAge(int tAge) {
        this.tAge = tAge;
    }

    public String gettGender() {
        return tGender;
    }

    public void settGender(String tGender) {
        this.tGender = tGender;
    }

    public int gettWorkage() {
        return tWorkage;
    }

    public void settWorkage(int tWorkage) {
        this.tWorkage = tWorkage;
    }

    public String gettMajor() {
        return tMajor;
    }

    public void settMajor(String tMajor) {
        this.tMajor = tMajor;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "tId=" + tId +
                ", tName='" + tName + '\'' +
                ", tAge=" + tAge +
                ", tGender='" + tGender + '\'' +
                ", tWorkage=" + tWorkage +
                ", tMajor='" + tMajor + '\'' +
                '}';
    }
}
