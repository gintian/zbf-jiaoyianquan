package com.zbf.user.entity;

import com.baomidou.mybatisplus.annotation.TableField;
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
public class BaseUserRole implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    @TableField("roleId")
    private Long roleId;

    @TableField("userId")
    private Long userId;


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
