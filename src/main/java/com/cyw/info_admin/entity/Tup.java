package com.cyw.info_admin.entity;

import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;

@Data
@ToString
public class Tup {
    /**
     * 主键
     */
    private Long id;

    /**
     * 产品id
     */
    private Long tpid;

    /**
     * 用户id
     */
    private Long tuid;

    /**
     * 账号(用户名)
     */
    private String username;

    /**
     * 物品名称
     */
    private String pname;

    /**
     * 提交编号(每次提交生成唯一的编号)
     */
    private String submitNumber;

    /**
     * 产品数量
     */
    private Long tpcount;

    /**
     * 产品总价
     */
    private BigDecimal ptotal;

    /**
     * 审核状态：0待审核、1审核通过、2驳回
     */
    private Integer status;

    /**
     * 创建时间
     */
    private Date createtime;

    /**
     * 更新时间
     */
    private Date updatetime;
}