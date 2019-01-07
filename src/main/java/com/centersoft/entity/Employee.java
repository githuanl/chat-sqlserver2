package com.centersoft.entity;

import com.centersoft.enums.EmployeeSortEnum;

import java.util.Date;

/**
 * Created by liuliyuan on 2017/4/17.
 */
public class Employee extends BaseEntity {
    private Integer xh;
    private String id;//编号
    private String psnname;//名称
    private String memo;//助记码
  /*  public  enum Gender{
        man,//男
        woman//女
    }*/
    private String gender;//性别
    private Date birthday;//出生日期
    private String marriage;//婚否
    private String nation;//民族
    private String address;//家庭住址
    private String education;//教育程度
    private String idcard;//身份证
    private String position;//职务岗位
    private String expertise;//特长
    private String depart;//部门
    private String resume;//说明
    private String linkman;//联系人
    private String linkmantel;//联系人电话
    private String linkmanrelax;//关系
    private String onlinestate;
    private String builtin;
    private String role;
    private String menustyle;
    private Date getmsgdate;
    private Date setmsgdate;
    private String getmsginterval;
    private String getmsgprompt;
    private String graphics;
    private String firstpagecontent;
    private String workphone;//工作电话
    private String homephone;//家庭电话
    private String mobile;
    private String email;
    private String visible;
    private String accountEnabled;
    private EmployeeSortEnum sort;
    /**
     * 银行卡号
     */
    private String bankaccount;
    /**
     * 银行名称
     */
    private String bankname;
    /**
     * 合同签订时间
     */
    private Date contrdate;
    /**
     * 续签时间
     */
    private Date renewdate;
    /**
     * 到期时间
     */
    private Date expiredate;
    /**
     * 离职时间
     */
    private Date quitdate;
    /**
     * 头像地址
     */
    private String imgUrl;


    public Integer getXh() {
        return xh;
    }

    public void setXh(Integer xh) {
        this.xh = xh;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPsnname() {
        return psnname;
    }

    public void setPsnname(String psnname) {
        this.psnname = psnname;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getMarriage() {
        return marriage;
    }

    public void setMarriage(String marriage) {
        this.marriage = marriage;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getExpertise() {
        return expertise;
    }

    public void setExpertise(String expertise) {
        this.expertise = expertise;
    }

    public String getDepart() {
        return depart;
    }

    public void setDepart(String depart) {
        this.depart = depart;
    }

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public String getLinkman() {
        return linkman;
    }

    public void setLinkman(String linkman) {
        this.linkman = linkman;
    }

    public String getLinkmantel() {
        return linkmantel;
    }

    public void setLinkmantel(String linkmantel) {
        this.linkmantel = linkmantel;
    }

    public String getLinkmanrelax() {
        return linkmanrelax;
    }

    public void setLinkmanrelax(String linkmanrelax) {
        this.linkmanrelax = linkmanrelax;
    }

    public String getOnlinestate() {
        return onlinestate;
    }

    public void setOnlinestate(String onlinestate) {
        this.onlinestate = onlinestate;
    }

    public String getBuiltin() {
        return builtin;
    }

    public void setBuiltin(String builtin) {
        this.builtin = builtin;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getMenustyle() {
        return menustyle;
    }

    public void setMenustyle(String menustyle) {
        this.menustyle = menustyle;
    }

    public Date getGetmsgdate() {
        return getmsgdate;
    }

    public void setGetmsgdate(Date getmsgdate) {
        this.getmsgdate = getmsgdate;
    }

    public Date getSetmsgdate() {
        return setmsgdate;
    }

    public void setSetmsgdate(Date setmsgdate) {
        this.setmsgdate = setmsgdate;
    }

    public String getGetmsginterval() {
        return getmsginterval;
    }

    public void setGetmsginterval(String getmsginterval) {
        this.getmsginterval = getmsginterval;
    }

    public String getGetmsgprompt() {
        return getmsgprompt;
    }

    public void setGetmsgprompt(String getmsgprompt) {
        this.getmsgprompt = getmsgprompt;
    }

    public String getGraphics() {
        return graphics;
    }

    public void setGraphics(String graphics) {
        this.graphics = graphics;
    }

    public String getFirstpagecontent() {
        return firstpagecontent;
    }

    public void setFirstpagecontent(String firstpagecontent) {
        this.firstpagecontent = firstpagecontent;
    }

    public String getWorkphone() {
        return workphone;
    }

    public void setWorkphone(String workphone) {
        this.workphone = workphone;
    }

    public String getHomephone() {
        return homephone;
    }

    public void setHomephone(String homephone) {
        this.homephone = homephone;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getVisible() {
        return visible;
    }

    public void setVisible(String visible) {
        this.visible = visible;
    }

    public String getAccountEnabled() {
        return accountEnabled;
    }

    public void setAccountEnabled(String accountEnabled) {
        this.accountEnabled = accountEnabled;
    }

    public EmployeeSortEnum getSort() {
        return sort;
    }

    public void setSort(EmployeeSortEnum sort) {
        this.sort = sort;
    }

    public String getBankaccount() {
        return bankaccount;
    }

    public void setBankaccount(String bankaccount) {
        this.bankaccount = bankaccount;
    }

    public String getBankname() {
        return bankname;
    }

    public void setBankname(String bankname) {
        this.bankname = bankname;
    }

    public Date getContrdate() {
        return contrdate;
    }

    public void setContrdate(Date contrdate) {
        this.contrdate = contrdate;
    }

    public Date getRenewdate() {
        return renewdate;
    }

    public void setRenewdate(Date renewdate) {
        this.renewdate = renewdate;
    }

    public Date getExpiredate() {
        return expiredate;
    }

    public void setExpiredate(Date expiredate) {
        this.expiredate = expiredate;
    }

    public Date getQuitdate() {
        return quitdate;
    }

    public void setQuitdate(Date quitdate) {
        this.quitdate = quitdate;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
