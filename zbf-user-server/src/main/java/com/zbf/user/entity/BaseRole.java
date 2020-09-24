package com.zbf.user.entity;

import java.io.Serializable;
import java.util.List;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author tong
 * @since 2020-09-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class BaseRole implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 这是角色表
     */
    private Long id;

    /**
     * 角色编码
     */
    private String roleCode;

    /**
     * 角色表
     */
    private String name;

    /**
     * 描述
     */
    private String miaoshu;

    @TableField(exist = false)
    private List<BaseMenu> baseMenus;

    @TableField(exist = false)
    private Long[] mids;


    public Long[] getMids() {
        return mids;
    }

    public void setMids(Long[] mids) {
        this.mids = mids;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMiaoshu() {
        return miaoshu;
    }

    public void setMiaoshu(String miaoshu) {
        this.miaoshu = miaoshu;
    }

    public List<BaseMenu> getBaseMenus() {
        return baseMenus;
    }

    public void setBaseMenus(List<BaseMenu> baseMenus) {
        this.baseMenus = baseMenus;
    }
}
