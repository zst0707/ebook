package gcxy.zst.user.dao;

import gcxy.zst.user.model.User;

import java.sql.SQLException;
import java.util.List;

/**
 * @ClassName IUserDao
 * @Description TODO
 * @Author 郑思婷
 * @Date 2019/9/1820:11
 * @Version 1.0
 **/
public interface IUserDao {
    public boolean findByUidAndPassword(String uid, String password) throws SQLException;
    public void updatePassword(String uid, String password) throws SQLException;
    public User findByLoginnameAndLoginpass(String loginname, String loginpass) throws SQLException;
    public boolean ajaxValidateLoginname(String loginname) throws SQLException;
    public boolean ajaxValidateEmail(String email) throws SQLException;
    public void add(User user) throws SQLException;
    public List<User> findAll() throws SQLException;
    public void update(User user) throws SQLException;
    public User load(String uid) throws SQLException;
    public void freezing(String uid) throws SQLException;
    public void unfreeze(String uid) throws SQLException;
}
