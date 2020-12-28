package com.cyw.info_admin.entity.dto;

import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@ToString
public class EditUserDTO implements Serializable {

    /**
     * 主键
     */
    @NotNull(message = "编辑用户信息时主键不能为空")
    private Long id;

    /**
     * 账号
     */
    @NotEmpty(message = "编辑用户信息时账号不能为空")
    private String name;

    /**
     * 单位名称
     */
    @NotEmpty(message = "编辑用户信息时所属机构不能为空")
    private String company;

    /**
     * 密码
     */
    @NotEmpty(message = "编辑用户信息时登录密码不能为空")
    private String password;

    /**
     * 用户类型：0管理员、1普通用户
     */
    @NotNull(message = "编辑用户信息时用户类型不能为空")
    private Integer userType;
}
