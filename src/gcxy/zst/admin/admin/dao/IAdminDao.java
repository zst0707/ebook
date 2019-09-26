package gcxy.zst.admin.admin.dao;
import gcxy.zst.admin.admin.model.Admin;

import java.sql.SQLException;

public interface IAdminDao {
    public Admin find(String adminname, String adminpwd) throws SQLException;
}
