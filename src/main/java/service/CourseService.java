package service;
/*
 * Created by IntelliJ IDEA.
 * User: jake
 * Date: 2024/5/28
 * Time: 上午12:37
 * To change this template use File | Settings | File Templates.
 */

import bean.Course;
import dao.CourseDao;

import java.util.List;
import java.util.Optional;

public class CourseService {
    private final CourseDao courseDao = new CourseDao();

    public List<Course> getAllCourses() {
        return courseDao.getAllCourses();
    }

    public List<Course> getCoursesByStudentId(int id) {
        return courseDao.getCoursesByStudentId(id);
    }

    public Optional<Course> getCourseByCoursecId(int id) {
        return courseDao.getCourseByCoursecId(id);
    }

    public boolean addCourse(Course course) {
        return courseDao.addCourse(course);
    }

    public int updateCourse(Course course) {
        return courseDao.updateCourse(course);
    }

    public boolean deleteCourse(int id) {
        return courseDao.deleteCourse(id);
    }

    // 这里添加其他业务逻辑方法
}
