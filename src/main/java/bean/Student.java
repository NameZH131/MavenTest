package bean;

public class Student {
    //    javabean
    private int sId;
    private String sPassword;
    private String sName;
    private int sAge;
    private String sGender;
    private int sGrade;


    public Student() {
    }

    public Student(int sId, String sPassword, String sName, int sAge, String sGender, int sGrade) {
        this.sId = sId;
        this.sPassword = sPassword;
        this.sName = sName;
        this.sAge = sAge;
        this.sGender = sGender;
        this.sGrade = sGrade;
    }

    public Student(int sId, String sPassword) {
        this.sId = sId;
        this.sPassword = sPassword;
    }

    public String getsPassword() {
        return sPassword;
    }

    public void setsPassword(String password) {
        this.sPassword = password;
    }

    public int getsAge() {
        return sAge;
    }

    public void setsAge(int sAge) {
        this.sAge = sAge;
    }

    public String getsGender() {
        return sGender;
    }

    public void setsGender(String sGender) {
        this.sGender = sGender;
    }

    public int getsGrade() {
        return sGrade;
    }

    public void setsGrade(int sGrade) {
        this.sGrade = sGrade;
    }

    public int getsId() {
        return sId;
    }

    public void setsId(int sId) {
        this.sId = sId;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    @Override
    public String toString() {
        return "Student{" +
                "sId=" + sId +
                ", sPassword='" + sPassword + '\'' +
                ", sName='" + sName + '\'' +
                ", sAge=" + sAge +
                ", sGender='" + sGender + '\'' +
                ", sGrade=" + sGrade +
                '}';
    }
}
