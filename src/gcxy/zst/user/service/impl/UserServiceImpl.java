package gcxy.zst.user.service.impl;

import cn.itcast.commons.CommonUtils;
import cn.itcast.mail.Mail;
import cn.itcast.mail.MailUtils;
import gcxy.zst.user.dao.impl.UserDaoImpl;
import gcxy.zst.user.model.User;
import gcxy.zst.user.service.IUserService;
import gcxy.zst.user.service.exception.UserException;


import javax.mail.MessagingException;
import javax.mail.Session;
import java.io.IOException;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.List;
import java.util.Properties;

/**
 * @ClassName UserServiceImpl
 * @Description TODO
 * @Author 郑思婷
 * @Date 2019/9/1820:51
 * @Version 1.0
 **/
public class UserServiceImpl implements IUserService {
    UserDaoImpl userDao = new UserDaoImpl();

    /**
     * 修改密码
     *
     * @param uid
     * @param newPass
     * @param oldPass
     * @throws UserException
     */
    @Override
    public void updatePassword(String uid, String newPass, String oldPass) throws UserException {
        try {
            /*
             * 1. 校验老密码
             */
            boolean bool = userDao.findByUidAndPassword(uid, oldPass);
            if (!bool) {//如果老密码错误
                throw new UserException("密码错误！");
            }
            /*
             * 2. 修改密码
             */
            userDao.updatePassword(uid, newPass);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 登录功能
     *
     * @param user
     * @return
     */
    @Override
    public User login(User user) {
        try {
            return userDao.findByLoginnameAndLoginpass(user.getLoginname(), user.getLoginpass());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    /**
     * 用户名注册校验
     *
     * @param loginname
     * @return
     */
    @Override
    public boolean ajaxValidateLoginname(String loginname) {
        try {
            return userDao.ajaxValidateLoginname(loginname);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Email校验
     *
     * @param email
     * @return
     */
    @Override
    public boolean ajaxValidateEmail(String email) {
        try {
            return userDao.ajaxValidateEmail(email);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 注册功能
     *
     * @param user
     */
    @Override
    public void regist(User user) {

        /*
         * 2. 向数据库插入
         */
        try {
            userDao.add(user);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * 查询所有用户
     * @return
     */
    @Override
    public List<User> findAll() {
        try {
            return userDao.findAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 修改用户信息
     * @param user
     */
    @Override
    public void update(User user) {
        try {
            userDao.update(user);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     *按照uid查询
     * @param uid
     * @return
     */
    public User load(String uid){
        try {
            return userDao.load(uid);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 冻结用户
     * @param uid
     */
    @Override
    public void freezing(String uid) {
        try {
            userDao.freezing(uid);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 解冻用户
     * @param uid
     */
    @Override
    public void unfreeze(String uid) {
        try {
            userDao.unfreeze(uid);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}

