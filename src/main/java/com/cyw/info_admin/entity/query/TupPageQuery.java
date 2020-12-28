package com.cyw.info_admin.entity.query;

import lombok.Data;
import lombok.ToString;
import java.io.Serializable;
import java.util.Date;

@Data
@ToString
public class TupPageQuery implements Serializable {

    /**
     * 用户id
     */
    private String username;

    /**
     * 开始时间(根据提交时间)
     */
    private Date submitBegintime;

    /**
     * 结束时间(根据提交时间)
     */
    private Date submitEndtime;

    /**
     * 审核状态：0待审核(默认)、1审核通过、2驳回
     */
    private Integer status = 0;
}
