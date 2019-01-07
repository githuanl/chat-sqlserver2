package com.centersoft.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by liudong on 2018/6/20.
 */

//群成员
@Entity
@Table(name = "c_group_user")
public class GroupUser extends BaseEntity {


    private String id;
    private String group_id;    // 群 id
    private String user_id;     //群成员的id
    private String username;    //群成员的名字

    // 冗余数据，不想关联查询
    private String avatar;      //群成员的头像
    private String sign;        //签名
    private String joninTime;   //加入时间

    public String getJoninTime() {
        return joninTime;
    }

    public void setJoninTime(String joninTime) {
        this.joninTime = joninTime;
    }

    @Id
    @GeneratedValue(generator = "_uuid")
    @Column(columnDefinition = "varchar(36)")
    @GenericGenerator(name = "_uuid", strategy = "org.hibernate.id.UUIDGenerator")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }


    public String getGroup_id() {
        return group_id;
    }

    public void setGroup_id(String group_id) {
        this.group_id = group_id;
    }
}
