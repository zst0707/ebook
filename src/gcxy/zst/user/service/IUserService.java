package gcxy.zst.user.service;

import gcxy.zst.user.model.User;
import org.omg.CORBA.UserException;

import java.util.List;

public interface IUserService{
    public void updatePassword(String uid, String newPass, String oldPass) throws UserException, gcxy.zst.user.service.exception.UserException;
    public User login(User user);
    public boolean ajaxValidateLoginname(String loginname);
    public boolean ajaxValidateEmail(String email);
    public void regist(User user);
    public List<User> findAll();
    public void update(User user);
    public User load(String uid);
    public void freezing(String uid);
    public void unfreeze(String uid);
}
