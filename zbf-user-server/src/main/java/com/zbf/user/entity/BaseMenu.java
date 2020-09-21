package com.zbf.user.entity;

import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.List;

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
public class BaseMenu implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 菜单表
     */
    private Long id;

    private Integer version;

    private Long code;

    @TableField("menuName")
    private String menuName;

    @TableField("imagePath")
    private String imagePath;

    private String url;

    @TableField("parentCode")
    private Long parentCode;

    private Integer leval;

    @TableField("createTime")
    private LocalDateTime createTime;

    @TableField(exist = false)
    private List<BaseMenu> list;

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

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Long getParentCode() {
        return parentCode;
    }

    public void setParentCode(Long parentCode) {
        this.parentCode = parentCode;
    }

    public Integer getLeval() {
        return leval;
    }

    public void setLeval(Integer leval) {
        this.leval = leval;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public List<BaseMenu> getList() {
        return list;
    }

    public void setList(List<BaseMenu> list) {
        this.list = list;
    }
}
