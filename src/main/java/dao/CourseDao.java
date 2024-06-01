package dao;
/*
 * Created by IntelliJ IDEA.
 * User: jake
 * Date: 2024/5/28
 * Time: 上午12:39
 * To change this template use File | Settings | File Templates.
 */

import bean.Course;
import dbConnUtil.JDBCConnUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CourseDao {

    //    导入conn
    private final Connection conn =JDBCConnUtil.openDB();

    public List<Course> getAllCourses() {
        List<Course> courses = new ArrayList<>();
        // SQL查询语句
        String sql = "SELECT * FROM course";
        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Course course = new Course();
                course.setcId(rs.getInt("c_id"));
                course.setcName(rs.getString("c_name"));
                courses.add(course);
            }
        } catch (SQLException e) {
            System.out.println("查询全部课程信息失败");
            e.printStackTrace();
        } finally {
            JDBCConnUtil.closeDB();
        }
        return courses;
    }

    /**
     * 找到学生的课程通过学生ID
     * @param id
     * @return
     */
    public List<Course> getCoursesByStudentId(int id) {
        List<Course> courses = new ArrayList<>();
        // SQL查询语句
        String sql = "SELECT * FROM course WHERE c_id IN (SELECT c_id FROM serve WHERE s_id =?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Course course = new Course();
                course.setcId(rs.getInt("c_id"));
                course.setcName(rs.getString("c_name"));
                courses.add(course);
            }
        } catch (SQLException e) {
            System.out.println("查询课程信息通过学生ID失败");
            e.printStackTrace();
        }
        return courses;
    }


    public Optional<Course> getCourseByCoursecId(int id) {
        // SQL查询语句
        String sql = "SELECT * FROM course WHERE c_id =?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
               Course course = new Course();
                course.setcId(rs.getInt("c_id"));
                course.setcName(rs.getString("c_name"));
                return Optional.of(course);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCConnUtil.closeDB();
        }
        return Optional.empty();
    }

    public boolean addCourse(Course course) {
        // SQL插入语句
        String sql = "INSERT INTO course(c_id, c_name) VALUES(?,?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1,course.getcId());
            ps.setString(3,course.getcName());
            int count = ps.executeUpdate();
            if (count > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCConnUtil.closeDB();
        }
        return false;
    }

    public int updateCourse(Course course) {
        String sql = "UPDATE course SET c_name = ? WHERE c_id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(2, course.getcName());
            ps.setInt(3, course.getcId());
            int count = ps.executeUpdate();
            if (count > 0) {
                return count;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCConnUtil.closeDB();
        }
        return 0;
    }

    public boolean deleteCourse(int id) {
        // SQL删除语句
        String sql = "DELETE FROM course WHERE c_id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            int count = ps.executeUpdate();
            if (count > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCConnUtil.closeDB();
        }
        return false;
    }



    // 这里添加其他数据库操作方法，比如添加课程、更新课程信息等

}
