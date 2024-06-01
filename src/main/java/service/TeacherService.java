package service;
/*
 * Created by IntelliJ IDEA.
 * User: jake
 * Date: 2024/5/28
 * Time: 上午12:56
 * To change this template use File | Settings | File Templates.
 */

import bean.Teacher;
import dao.TeacherDao;

import java.util.List;
import java.util.Optional;

public class TeacherService {
    private final TeacherDao teacherDao = new TeacherDao();

    public List<Teacher> getAllTeachers() {
        return teacherDao.getAllTeachers();
    }

    public List<Teacher> getTeacherByStudensId(int sId) {
        return teacherDao.getTeacherByStudensId(sId);
    }
    public Optional<Teacher> getTeacherByTeacherId(int tId) {
        return teacherDao.getTeacherByTeachertId(tId);
    }

    public boolean addTeacher(Teacher teacher) {
       return teacherDao.addTeacher(teacher);
    }

    public boolean deleteTeacher(int tId) {
        return teacherDao.deleteTeacher(tId);
    }

    public int updateTeacher(Teacher teacher) {
        return teacherDao.updateTeacher(teacher);
    }
    // 这里添加其他业务逻辑方法
}