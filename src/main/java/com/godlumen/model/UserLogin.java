package com.godlumen.model;

import org.joda.time.DateTime;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class UserLogin implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    @Column(unique =true)
    private String mobile;//手机号
    private String nickName;//昵称
    private String password; //密码
    private String salt;//加密密码的盐
    private byte state;//用户状态,0:创建未认证（比如没有激活，没有输入验证码等等）--等待验证的用户 , 1:正常状态,2：用户被锁定.
    private DateTime lastLogin;
    @ManyToMany(fetch= FetchType.EAGER)//立即从数据库中进行加载数据;
    @JoinTable(name = "SysUserRole", joinColumns = { @JoinColumn(name = "uid") }, inverseJoinColumns ={@JoinColumn(name = "roleId") })
    private List<SysRole> roleList;// 一个用户具有多个角色

    public UserLogin() {
    }
    public UserLogin(Long id, String mobile, String nickName, String password, String salt, byte state, DateTime lastLogin, List<SysRole> roleList) {
        this.id = id;
        this.mobile = mobile;
        this.nickName = nickName;
        this.password = password;
        this.salt = salt;
        this.state = state;
        this.lastLogin = lastLogin;
        this.roleList = roleList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public byte getState() {
        return state;
    }

    public void setState(byte state) {
        this.state = state;
    }

    public DateTime getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(DateTime lastLogin) {
        this.lastLogin = lastLogin;
    }

    public List<SysRole> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<SysRole> roleList) {
        this.roleList = roleList;
    }

    /**
     * 密码盐.
     * @return
     */
    public String getCredentialsSalt(){
        return this.mobile+this.salt;
    }
    //重新对盐重新进行了定义，用户名+salt，这样就更加不容易被破解

    @Override
    public String toString() {
        return "id:"+id+" mobile:"+mobile+" nickName:"+nickName+" password:"+password+" salt:"+salt+
                " state:"+state+" lastLogin:"+lastLogin.toString("yyyy-MM-dd HH:mm:ss")+" roleList.size:"+roleList.size();
    }
}
