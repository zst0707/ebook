package gcxy.zst.admin.admin.service.impl;

import gcxy.zst.admin.admin.dao.impl.AdminDaoImpl;
import gcxy.zst.admin.admin.model.Admin;
import gcxy.zst.admin.admin.service.IAdminService;

import java.sql.SQLException;

/**
 * @ClassName AdminServiceImpl
 * @Description TODO
 * @Author 郑思婷
 * @Date 2019/9/2217:06
 * @Version 1.0
 **/
public class AdminServiceImpl implements IAdminService {
    AdminDaoImpl adminDao=new AdminDaoImpl();

    /**
     * 登录功能
     * @param admin
     * @return
     */
    @Override
    public Admin login(Admin admin) {
        try {
            return adminDao.find(admin.getAdminname(),admin.getAdminpwd());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
