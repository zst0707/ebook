package gcxy.zst.user.model;

/**
 * @ClassName User
 * @Description TODO
 * @Author 郑思婷
 * @Date 2019/9/1819:48
 * @Version 1.0
 **/
public class   User {
    // 对应数据库表
    private String uid;//主键
    private String loginname;//登录名
    private String loginpass;//登录密码
    private String email;//邮箱
    private int status;//冻结用户，0为冻结，1为正常
    @Override
    public String toString() {
        return "User{" +
                "uid='" + uid + '\'' +
                ", loginname='" + loginname + '\'' +
                ", loginpass='" + loginpass + '\'' +
                ", email='" + email + '\'' +
                ", status=" + status +
                ", reloginpass='" + reloginpass + '\'' +
                ", newpass='" + newpass + '\'' +
                '}';
    }


    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }


    // 注册表单
    private String reloginpass;//确认密码

    // 修改密码表单
    private String newpass;//新密码

    public String getReloginpass() {
        return reloginpass;
    }
    public void setReloginpass(String reloginpass) {
        this.reloginpass = reloginpass;
    }

    public String getUid() {
        return uid;
    }
    public void setUid(String uid) {
        this.uid = uid;
    }
    public String getLoginname() {
        return loginname;
    }
    public void setLoginname(String loginname) {
        this.loginname = loginname;
    }
    public String getLoginpass() {
        return loginpass;
    }
    public void setLoginpass(String loginpass) {
        this.loginpass = loginpass;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getNewpass() {
        return newpass;
    }
    public void setNewpass(String newpass) {
        this.newpass = newpass;
    }
}
