package dao;

import bean.Course;
import bean.Student;
import dbConnUtil.JDBCConnUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StudentDao {

    //    导入conn
    private final Connection conn = JDBCConnUtil.openDB();

    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        // SQL查询语句
        String sql = "SELECT * FROM student";
        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Student student = new Student();
                student.setsId(rs.getInt("s_id"));
                student.setPassword(rs.getString("s_password"));
                student.setsName(rs.getString("s_name"));
                student.setsAge(rs.getInt("s_age"));
                student.setsGender(rs.getString("s_gender"));
                student.setsGrade(rs.getInt("s_grade"));
                students.add(student);
            }
        } catch (SQLException e) {
            System.out.println("查询学生失败");
            e.printStackTrace();
        } finally {
            JDBCConnUtil.closeDB();
        }
        return students;
    }

    public boolean addStudent(Student student) {
        String sql = "INSERT INTO student (s_id, s_password, s_name, s_age, s_gender, s_grade) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, student.getsId());
            ps.setString(2, student.getPassword());
            ps.setString(3, student.getsName());
            ps.setInt(4, student.getsAge());
            ps.setString(5, student.getsGender());
            ps.setInt(6, student.getsGrade());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("添加学生失败");
            e.printStackTrace();
            return false;
        } finally {
            JDBCConnUtil.closeDB();
        }
        return true;
    }

    public void deleteStudent(int sId) {
        String sql = "DELETE FROM student WHERE s_id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, sId);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("删除学生失败");
            e.printStackTrace();
        } finally {
            JDBCConnUtil.closeDB();
        }
    }

    public void updateStudent(Student student) {
        String sql = "UPDATE student SET s_password = ?, s_name = ?, s_age = ?, s_gender = ?, s_grade = ? WHERE s_id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, student.getPassword());
        } catch (SQLException e) {
            System.out.println("更新学生失败");
            throw new RuntimeException(e);
        } finally {
            JDBCConnUtil.closeDB();
        }
    }

    public Optional<Student> getStudentById(int sId) {
        String sql = "SELECT * FROM student WHERE s_id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, sId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Student student
                        = new Student(rs.getInt("s_id"),
                        rs.getString("s_password"),
                        rs.getString("s_name"),
                        rs.getInt("s_age"),
                        rs.getString("s_gender"),
                        rs.getInt("s_grade"));
                // 返回学生对象
                return Optional.of(student);
            }
        } catch (SQLException e) {
            System.out.println("查询学生失败");
            throw new RuntimeException(e);
        } finally {
            JDBCConnUtil.closeDB();
        }
        // 如果查询不到，返回空
        return Optional.empty();
    }

    public List<Student> getStudentByName(String sName) {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM student WHERE s_name = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, sName);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Student student = new Student(rs.getInt("s_id"),
                        rs.getString("s_password"),
                        rs.getString("s_name"),
                        rs.getInt("s_age"),
                        rs.getString("s_gender"),
                        rs.getInt("s_grade"));
                students.add(student);
            }
        } catch (SQLException e) {
            System.out.println("查询学生失败");
            e.printStackTrace();
        } finally {
            JDBCConnUtil.closeDB();
        }
        return students;
    }

    public List<Student> getStudentByGrade(int sGrade) {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM student WHERE s_grade = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, sGrade);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Student student = new Student(rs.getInt("s_id"),
                        rs.getString("s_password"),
                        rs.getString("s_name"),
                        rs.getInt("s_age"),
                        rs.getString("s_gender"),
                        rs.getInt("s_grade"));
                students.add(student);
            }
        } catch (SQLException e) {
            System.out.println("查询学生失败");
            e.printStackTrace();
        } finally {
            JDBCConnUtil.closeDB();
        }
        return students;
    }

    public List<Student> getStudentByAge(int sAge) {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM student WHERE s_age = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, sAge);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Student student = new Student(rs.getInt("s_id"),
                        rs.getString("s_password"),
                        rs.getString("s_name"),
                        rs.getInt("s_age"),
                        rs.getString("s_gender"),
                        rs.getInt("s_grade"));
                students.add(student);
            }
        } catch (SQLException e) {
            System.out.println("查询学生失败");
            e.printStackTrace();
        } finally {
            JDBCConnUtil.closeDB();
        }
        return students;
    }
    // 这里添加其他数据库操作方法，比如添加学生、更新学生信息等

    public List<Student> getStudentByGender(String sGender) {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM student WHERE s_gender = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, sGender);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Student student = new Student(rs.getInt("s_id"),
                        rs.getString("s_password"),
                        rs.getString("s_name"),
                        rs.getInt("s_age"),
                        rs.getString("s_gender"),
                        rs.getInt("s_grade"));
                students.add(student);
            }
        } catch (SQLException e) {
            System.out.println("查询学生失败");
            e.printStackTrace();
        } finally {
            JDBCConnUtil.closeDB();
        }
        return students;
    }

    /**
     * @param sId
     * @return sPassword
     */
    public String getStudentPassword(int sId) {
        String sql = "SELECT s_password FROM student WHERE s_id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, sId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("s_password");
            }
        } catch (SQLException e) {
            System.out.println("查询学生密码失败");
            e.printStackTrace();
        } finally {
            JDBCConnUtil.closeDB();
        }
        System.out.println("查询的密码为空");
        return null;
    }

    /**
     * 获取所有课程对象通过学生cId
     *
     * @param sId
     * @return List<Course>
     */
    public List<Course> getAllCourseByStudentId(int sId) {
        List<Course> courses = new ArrayList<>();
        String sql = "SELECT c.c_id,c.c_name FROM course c JOIN serve s ON c.c_id=s.c_id where s.s_id=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, sId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Course course = new Course(rs.getInt("c_id"),
                        rs.getString("c_name"));
                courses.add(course);
            }
        } catch (SQLException e) {
            System.out.println("查询学生全部课程失败通过学生sId");
            e.printStackTrace();
        } finally {
            JDBCConnUtil.closeDB();
        }
        return courses;
    }

}




