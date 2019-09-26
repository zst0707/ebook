package gcxy.zst.user.dao.impl;

import cn.itcast.commons.CommonUtils;
import cn.itcast.jdbc.TxQueryRunner;
import gcxy.zst.user.dao.IUserDao;
import gcxy.zst.user.model.User;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @ClassName UserDaoImpl
 * @Description TODO
 * @Author 郑思婷
 * @Date 2019/9/1820:11
 * @Version 1.0
 **/
public class UserDaoImpl implements IUserDao {
    private QueryRunner runner = new TxQueryRunner();

    /**
     * 按uid和password查询
     * @param uid
     * @param password
     * @return
     * @throws SQLException
     */
    @Override
    public boolean findByUidAndPassword(String uid, String password) throws SQLException {
        String sql = "select count(*)from t_user where uid=? and loginpass=?";
        Number number = (Number) runner.query(sql, new ScalarHandler(), uid, password);
        return number.intValue() > 0;
    }

    /**
     * 更新密码
     * @param uid
     * @param password
     * @throws SQLException
     */
    @Override
    public void updatePassword(String uid, String password) throws SQLException {
        String sql = "update t_user set loginpass=? where uid=?";
        runner.update(sql,password,uid);
    }

    /**
     *按用户名和密码查询
     * @param loginname
     * @param loginpass
     * @return
     * @throws SQLException
     */
    @Override
    public User findByLoginnameAndLoginpass(String loginname, String loginpass) throws SQLException {
       String sql="select * from t_user where loginname=? and loginpass=?";
       return runner.query(sql,new BeanHandler<User>(User.class),loginname,loginpass);
    }

    /**
     * 校验用户名是否注册
     * @param loginname
     * @return
     * @throws SQLException
     */
    @Override
    public boolean ajaxValidateLoginname(String loginname) throws SQLException {
        String sql="select count(1) from t_user where loginname=?";
       Number number= (Number) runner.query(sql,new ScalarHandler(),loginname);
       return number.intValue() == 0;
    }

    /**
     * 校验邮箱是否注册
     * @param email
     * @return
     * @throws SQLException
     */
    @Override
    public boolean ajaxValidateEmail(String email) throws SQLException {
        String sql="select count(1) from t_user where email=?";
        Number number= (Number) runner.query(sql,new ScalarHandler(),email);
        return number.intValue() == 0;
    }

    /**
     * 添加用户
     * @param user
     * @throws SQLException
     */
    @Override
    public void add(User user) throws SQLException {
        user.setUid(CommonUtils.uuid());
        user.setStatus(1);
        String sql="insert into t_user values(?,?,?,?,?)";
        Object[] params = {user.getUid(), user.getLoginname(), user.getLoginpass(),
                user.getEmail(),user.getStatus()};
        runner.update(sql, params);
    }

    /**
     * 把map中的数据映射到user中
     * @param map
     * @return
     */
    private User toUser(Map<String,Object> map) {
        User user = CommonUtils.toBean(map, User.class);
        String uid = (String)map.get("uid");
        user.setUid(uid);
        return user;
    }
    private List<User> toUserList(List<Map<String,Object>> mapList) {
        List<User> userList = new ArrayList<User>();
        for(Map<String,Object> map : mapList) {
            User c = toUser(map);
            userList.add(c);
        }
        return userList;
    }
    /**
     * 查询所有用户
     * @return
     * @throws SQLException
     */
    public List<User> findAll() throws SQLException {
        String sql="select * from t_user";
        List<Map<String,Object>> mapList=runner.query(sql,new MapListHandler());
        List<User> users=toUserList(mapList);
        return users;
    }

    /**
     * 管理员修改用户信息
     * @param user
     * @throws SQLException
     */
    public void update(User user) throws SQLException{
        String sql="update t_user set loginname=?,loginpass=?,email=? where uid=?";
        Object[] params={user.getLoginname(),user.getLoginpass(),user.getEmail(),user.getUid()};
        runner.update(sql,params);
    }

    /**
     * 按uid查找
     * @param uid
     * @return
     * @throws SQLException
     */
    @Override
    public User load(String uid) throws SQLException {
        String sql="select *from t_user where uid=?";
        return toUser(runner.query(sql,new MapHandler(),uid));
    }

    /**
     * 冻结用户
     * @param uid
     * @throws SQLException
     */
    @Override
    public void freezing(String uid) throws SQLException {
        String sql="update t_user set status=0 where uid=?";
        runner.update(sql,uid);
    }

    /**
     * 解冻账号
     * @param uid
     * @throws SQLException
     */
    public void unfreeze(String uid) throws SQLException {
        String sql="update t_user set status=1 where uid=?";
        runner.update(sql,uid);
    }
}
