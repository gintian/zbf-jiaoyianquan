package com.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDate;
import com.baomidou.mybatisplus.annotation.TableId;
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
@TableName("zbf_export_task_header")
public class ZbfExportTaskHeader implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 任务头ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 版本
     */
    private String version;

    /**
     * 操作人
     */
    private Integer userId;

    /**
     * 任务名称
     */
    private String taskName;

    /**
     * 任务状态 默认状态0准备状态 1表示进行中 2表示已完成
     */
    private Integer taskStatus;

    /**
     * 任务明细个数
     */
    private Integer taskTailNum;

    /**
     * 任务明细完成的个数
     */
    private Integer taskTailOkNum;

    /**
     * 任务的SQL
     */
    private String taskSql;

    /**
     * 创建时间
     */
    private LocalDate createTime;


}
