package com.tong.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
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
@TableName("zbf_export_task_detail")
public class ZbfExportTaskDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 明细的ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 任务的分片ID
     */
    private Integer shardId;

    /**
     * 任务头的ID
     */
    private Integer taskHeaderId;

    /**
     * 明细执行的任务SQL
     */
    private String taskSql;

    /**
     * 明细数据生成的Excel的名字
     */
    private String fileName;

    /**
     * 明细数据生成的Excel的地址 (这里是储存在Minio服务的地址)
     */
    private String filePath;


}
