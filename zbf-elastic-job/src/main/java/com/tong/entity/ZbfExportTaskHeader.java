package com.tong.entity;

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


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public Integer getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(Integer taskStatus) {
        this.taskStatus = taskStatus;
    }

    public Integer getTaskTailNum() {
        return taskTailNum;
    }

    public void setTaskTailNum(Integer taskTailNum) {
        this.taskTailNum = taskTailNum;
    }

    public Integer getTaskTailOkNum() {
        return taskTailOkNum;
    }

    public void setTaskTailOkNum(Integer taskTailOkNum) {
        this.taskTailOkNum = taskTailOkNum;
    }

    public String getTaskSql() {
        return taskSql;
    }

    public void setTaskSql(String taskSql) {
        this.taskSql = taskSql;
    }

    public LocalDate getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDate createTime) {
        this.createTime = createTime;
    }
}
