package service;
/*
 * Created by IntelliJ IDEA.
 * User: jake
 * Date: 2024/5/28
 * Time: 上午12:53
 * To change this template use File | Settings | File Templates.
 */

import bean.Course;
import bean.Student;
import dao.StudentDao;

import java.util.List;
import java.util.Optional;

public class StudentService {
    private final StudentDao studentDao = new StudentDao();

    public List<Student> getAllStudents() {
        return studentDao.getAllStudents();
    }

    public void addStudent(Student student) {
        studentDao.addStudent(student);
    }

    public void deleteStudent(int sId) {
        studentDao.deleteStudent(sId);
    }

    public void updateStudent(Student student) {
        studentDao.updateStudent(student);
    }

    public Optional<Student> getStudentById(int sId) {
        return studentDao.getStudentById(sId);
    }

    public List<Student> getStudentByName(String sName) {
        return studentDao.getStudentByName(sName);
    }

    public List<Student> getStudentByGrade(int sGrade) {
        return studentDao.getStudentByGrade(sGrade);
    }

    public List<Student> getStudentByAge(int sAge) {
        return studentDao.getStudentByAge(sAge);
    }

    public List<Student> getStudentByGender(String sGender) {
        return studentDao.getStudentByGender(sGender);
    }
    // 这里添加其他业务逻辑方法


    /**
     * 根据cId获取学生密码
     *
     * @param sId
     * @return sPassword
     */
    public String getStudentPassword(int sId) {
        return studentDao.getStudentPassword(sId);
    }

    /**
     * 获取学生所有课程对象通过sIc
     *
     * @param sId
     * @return
     */
    public List<Course> getAllCourseByStudentId(int sId) {
        return studentDao.getAllCourseByStudentId(sId);
    }

}
