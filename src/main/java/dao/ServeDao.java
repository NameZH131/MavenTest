package dao;

import bean.Serve;
import dbConnUtil.JDBCConnUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ServeDao {

    //导入conn
    private final Connection conn = JDBCConnUtil.openDB();


    public List<Serve> getAllServes() {
        List<Serve> serves = new ArrayList<>();
        // SQL查询语句
        String sql = "SELECT * FROM serve";
        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Serve serve = new Serve();
                serve.setsId(rs.getInt("s_id"));
                serve.setcId(rs.getInt("c_id"));
                serve.settId(rs.getInt("t_id"));
                serve.setScore(rs.getString("score"));
                serves.add(serve);
            }
        } catch (SQLException e) {
            System.out.println("查询服务记录失败");
            e.printStackTrace();
        } finally {
            JDBCConnUtil.closeDB();
        }
        return serves;
    }


    public List<Serve> getServesByStudentId(int sId) {
        List<Serve> serves = new ArrayList<>();
        String sql = "SELECT * FROM serve WHERE s_id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, sId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Serve serve = new Serve();
                serve.setsId(rs.getInt("s_id"));
                serve.setcId(rs.getInt("c_id"));
                serve.settId(rs.getInt("t_id"));
                serve.setScore(rs.getString("score"));
                serves.add(serve);
            }
        } catch (SQLException e) {
            System.out.println("查询对应课程通过course_id的服务记录失败");
            e.printStackTrace();
        }finally {
            JDBCConnUtil.closeDB();
        }
        return serves;
    }


    public void addServe(Serve serve) {
        String sql = "INSERT INTO serve (s_id, c_id, t_id, score) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, serve.getsId());
            ps.setInt(2, serve.getcId());
            ps.setInt(3, serve.gettId());
            ps.setString(4, serve.getScore());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("添加服务记录失败");
            throw new RuntimeException(e);
        } finally {
            JDBCConnUtil.closeDB();
        }
    }

    public void deleteServe(int sId) {
        String sql = "DELETE FROM serve WHERE s_id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, sId);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("删除服务记录失败");
            throw new RuntimeException(e);
        } finally {
            JDBCConnUtil.closeDB();
        }
    }

    public void updateServe(Serve serve) {
        String sql = "UPDATE serve SET c_id = ?, t_id = ?, score = ? WHERE s_id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, serve.getcId());
            ps.setInt(2, serve.gettId());
            ps.setString(3, serve.getScore());
            ps.setInt(4, serve.getsId());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("更新服务记录失败");
            throw new RuntimeException(e);
        } finally {
            JDBCConnUtil.closeDB();
        }
    }

    public Optional<Serve> getServeById(int sId) {
        String sql = "SELECT * FROM serve WHERE s_id = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql);) { // 将 ResultSet 纳入 try-with-resources 管理
                ps.setInt(1, sId);
                ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Serve serve = new Serve();
                // 设置 Serve 对象的属性
                serve.setsId(rs.getInt("s_id"));
                serve.setcId(rs.getInt("c_id"));
                serve.settId(rs.getInt("t_id"));
                serve.setScore(rs.getString("score")); // 假设 score 是一个字符串类型字段
                return Optional.of(serve); // 使用 Optional 包装返回值
            }
        } catch (SQLException e) {
            // 提供更详细的错误信息
            System.out.println("查询服务记录失败");
            throw new RuntimeException("DaServe: " + sId, e);
        } finally {
            JDBCConnUtil.closeDB();
        }

        return Optional.empty(); // 查询无结果时返回 Optional.empty()
    }



    // 这里添加其他数据库操作方法，比如添加服务记录、更新服务信息等


}

    

