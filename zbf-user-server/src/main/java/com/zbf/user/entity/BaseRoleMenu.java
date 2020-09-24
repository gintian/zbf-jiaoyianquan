package com.zbf.user.entity;

import java.io.Serializable;
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
public class BaseRoleMenu implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 这是角色菜单关系表
     */
    private Long id;

    private Integer version;

    private Long roleId;

    /**
     * 角色菜单表
     */
    private Long menuId;

    //有参构造
    public BaseRoleMenu(Long id, Integer version, Long roleId, Long menuId) {
        this.id = id;
        this.version = version;
        this.roleId = roleId;
        this.menuId = menuId;
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

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }
}
