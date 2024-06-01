package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import dbConnUtil.JDBCConnUtil;
import bean.Teacher;

public class TeacherDao {

    private final Connection conn = JDBCConnUtil.openDB();

    public List<Teacher> getAllTeachers() {
        List<Teacher> teachers = new ArrayList<>();
        // SQL查询语句
        String sql = "SELECT * FROM teacher";
        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Teacher teacher = new Teacher();
                teacher.settId(rs.getInt("t_id"));
                teacher.settName(rs.getString("t_name"));
                teacher.settAge(rs.getInt("t_age"));
                teacher.settGender(rs.getString("t_gender"));
                teacher.settWorkage(rs.getInt("t_workgae"));
                teacher.settMajor(rs.getString("t_major"));
                teachers.add(teacher);
            }
        } catch (SQLException e) {
            System.out.println("查询全部老师信息失败！");
            e.printStackTrace();
        } finally {
            JDBCConnUtil.closeDB();
        }
        return teachers;
    }

    public Optional<Teacher> getTeacherByTeachertId(int tId) {
        String sql = "SELECT * FROM teacher WHERE t_id = ?";
        try ( PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, tId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Teacher teacher= new Teacher();
                teacher.settId(rs.getInt("t_id"));
                teacher.settName(rs.getString("t_name"));
                teacher.settAge(rs.getInt("t_age"));
                teacher.settGender(rs.getString("t_gender"));
                teacher.settWorkage(rs.getInt("t_workgae"));
                teacher.settMajor(rs.getString("t_major"));
                return Optional.of(teacher);
            }
        } catch (SQLException e) {
            System.out.println("根据ID查询老师信息失败！");
            e.printStackTrace();
        } finally {
            JDBCConnUtil.closeDB();
        }
        return Optional.empty();
    }

    public boolean addTeacher(Teacher teacher) {
        String sql = "INSERT INTO teacher (t_id,t_name, t_age, t_gender, t_workgae, t_major) VALUES (?,?, ?, ?, ?, ?)";
        try ( PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, teacher.gettId());
            ps.setString(2, teacher.gettName());
            ps.setInt(3, teacher.gettAge());
            ps.setString(4, teacher.gettGender());
            ps.setInt(5, teacher.gettWorkage());
            ps.setString(6, teacher.gettMajor());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("添加老师信息失败！");
            e.printStackTrace();
        }
        finally {
            JDBCConnUtil.closeDB();
        }
        return false;
    }

    public boolean deleteTeacher(int tId) {
        String sql = "DELETE FROM teacher WHERE t_id = ?";
        try ( PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, tId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("删除老师信息失败！");
            e.printStackTrace();
        }
        finally {
            JDBCConnUtil.closeDB();
        }
        return false;
    }

    public int updateTeacher(Teacher teacher) {
        String sql = "UPDATE teacher SET t_id=?, t_name=?, t_age = ?, t_gender = ?, t_workgae = ?, t_major = ? WHERE t_id = ?";
        try ( PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, teacher.gettId());
            ps.setString(2, teacher.gettName());
            ps.setInt(3, teacher.gettAge());
            ps.setString(4, teacher.gettGender());
            ps.setInt(5, teacher.gettWorkage());
            ps.setString(6, teacher.gettMajor());
            ps.setInt(7, teacher.gettId());
            return ps.executeUpdate();
        }

        catch (SQLException e) {
            System.out.println("更新老师信息失败！");
            e.printStackTrace();
        }finally {
           JDBCConnUtil.closeDB();
        }
        return 0;
    }

    public List<Teacher> getTeacherByStudensId(int sId) {
        List<Teacher> teachers = new ArrayList<>();
//        select * from teacher t right join serve s on s.t_id = t.t_id where s.s_id =3;
//        select * from teacher t  join serve s on s.t_id = t.t_id where s.s_id =3;
//        select * from serve inner join teacher on serve.t_id = teacher.t_id where serve.s_id =3;
//        String sql = "SELECT * FROM teacher WHERE t_id IN (SELECT t_id FROM serve WHERE s_id = ?)";
        String sql = "select teacher.* from teacher left join serve on serve.t_id = teacher.t_id where serve.s_id = ?";
        try ( PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, sId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Teacher teacher = new Teacher();
                teacher.settId(rs.getInt("t_id"));
                teacher.settName(rs.getString("t_name"));
                teacher.settAge(rs.getInt("t_age"));
                teacher.settGender(rs.getString("t_gender"));
                teacher.settWorkage(rs.getInt("t_workgae"));
                teacher.settMajor(rs.getString("t_major"));
                teachers.add(teacher);
            }
        } catch (SQLException e) {
            System.out.println("查询学生对应的老师信息失败！");
            e.printStackTrace();
        } finally {
            JDBCConnUtil.closeDB();
        }
        return teachers;

    }
    // 这里添加其他数据库操作方法，比如添加教师、更新教师信息等
}
