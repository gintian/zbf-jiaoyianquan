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


}
