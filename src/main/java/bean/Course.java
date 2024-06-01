package bean;


public class Course {
    private int cId;
    private String cName;

    public Course() {

    }

    public Course(int cId,String cName) {
        this.cId = cId;

        this.cName = cName;
    }


    public int getcId() {
        return cId;
    }

    public void setcId(int cId) {
        this.cId = cId;
    }


    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }


    @Override
    public String toString() {
        return "Course{" +
                "cId=" + cId +
                ", cName='" + cName + '\'' +
                '}';
    }
}