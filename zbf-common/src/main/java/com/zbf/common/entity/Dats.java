package com.zbf.common.entity;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 *@Author tongdaowei
 *@Description //TODO
 *@Date 2020/9/18 0018 下午 6:37
 *@miaoshu
 **/
@Data
public class Dats<t> {
    private Long id;
    private Integer version;
    private String userName;
    private String loginName;
    private String passWord;
    private String tel;
    private String buMen;
    private String salt;
    private Date createTime;
    private String email;
    private String image;
    private Date updateTime;
    private Integer current=1;
    private Integer size=4;
    private Integer totals;
    private List<t> dat;
    private String roleName;
    private Integer[] ids;

    private Integer total;
    private String type[];
    private String order;
    private String orderType[];
    private String excleName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getBuMen() {
        return buMen;
    }

    public void setBuMen(String buMen) {
        this.buMen = buMen;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getCurrent() {
        return current;
    }

    public void setCurrent(Integer current) {
        this.current = current;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getTotals() {
        return totals;
    }

    public void setTotals(Integer totals) {
        this.totals = totals;
    }

    public List<t> getDat() {
        return dat;
    }

    public void setDat(List<t> dat) {
        this.dat = dat;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Integer[] getIds() {
        return ids;
    }

    public void setIds(Integer[] ids) {
        this.ids = ids;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public String[] getType() {
        return type;
    }

    public void setType(String[] type) {
        this.type = type;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String[] getOrderType() {
        return orderType;
    }

    public void setOrderType(String[] orderType) {
        this.orderType = orderType;
    }

    public String getExcleName() {
        return excleName;
    }

    public void setExcleName(String excleName) {
        this.excleName = excleName;
    }
}
