package com.centersoft.entity;

public class SUser extends BaseEntity{
    // 帐号状态(正常使用,被冻结)
    public enum AccountState {
        normal, //正常使用
        frozen //被冻结
    }
    private int id;
    private String loginname; // 登陆用户名
    private String lastlogin; // 上次登陆时间
    private String remark; // 备注
    private String password; // 登陆密码
    private AccountState accountstate; // 帐号状态
    private String depid; // 所属部门编号
    private String employeeid;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLoginname() {
        return loginname;
    }

    public void setLoginname(String loginname) {
        this.loginname = loginname;
    }

    public String getLastlogin() {
        return lastlogin;
    }

    public void setLastlogin(String lastlogin) {
        this.lastlogin = lastlogin;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public AccountState getAccountstate() {
        return accountstate;
    }

    public void setAccountstate(AccountState accountstate) {
        this.accountstate = accountstate;
    }

    public String getDepid() {
        return depid;
    }

    public void setDepid(String depid) {
        this.depid = depid;
    }

    public String getEmployeeid() {
        return employeeid;
    }

    public void setEmployeeid(String employeeid) {
        this.employeeid = employeeid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
