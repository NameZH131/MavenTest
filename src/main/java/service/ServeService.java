package service;
/*
 * Created by IntelliJ IDEA.
 * User: jake
 * Date: 2024/5/28
 * Time: 上午12:57
 * To change this template use File | Settings | File Templates.
 */


import bean.Serve;
import dao.ServeDao;

import java.util.List;
import java.util.Optional;

public class ServeService {
    private final ServeDao servedao = new ServeDao();

    public List<Serve> getAllServes() {
        return servedao.getAllServes();
    }

    public List<Serve> getServesByStudentIdId(int sId) {
        return servedao.getServesByStudentId(sId);
    }
    public void addServe(Serve serve) {
        servedao.addServe(serve);
    }

    public void deleteServe(int sId) {
        servedao.deleteServe(sId);
    }

    public void updateServe(Serve serve) {
        servedao.updateServe(serve);
    }

    public Optional<Serve> getServeById(int sId) {
        return servedao.getServeById(sId);
    }
    // 这里添加其他业务逻辑方法
}
