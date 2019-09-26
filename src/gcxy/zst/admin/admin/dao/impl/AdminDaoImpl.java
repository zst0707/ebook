package gcxy.zst.admin.admin.dao.impl;

import cn.itcast.jdbc.TxQueryRunner;
import gcxy.zst.admin.admin.dao.IAdminDao;
import gcxy.zst.admin.admin.model.Admin;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.SQLException;

/**
 * @ClassName AdminDaoImpl
 * @Description TODO
 * @Author 郑思婷
 * @Date 2019/9/2216:54
 * @Version 1.0
 **/
public class AdminDaoImpl implements IAdminDao {
    QueryRunner runner=new TxQueryRunner();
    /**
     * 通过管理员登录名和登录密码查询
     * @param adminname
     * @param adminpwd
     * @return
     * @throws SQLException
     */
    public Admin find(String adminname, String adminpwd) throws SQLException{
        String sql="select *from t_admin where adminname=? and adminpwd=?";
        return runner.query(sql,new BeanHandler<Admin>(Admin.class),adminname,adminpwd);
    }
}
