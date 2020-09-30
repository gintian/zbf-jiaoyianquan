package com.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDate;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author tongdaowei
 * @since 2020-09-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ZbfJobType implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * job编码
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * jpb描述
     */
    private String jobMark;

    /**
     * job类
     */
    private String jobClass;

    /**
     * 创建时间
     */
    @TableField("createTime")
    private LocalDate createTime;


}
