package com.cyw.info_admin.entity.dto;

import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@ToString
public class AddUserDTO implements Serializable {

    /**
     * 账号
     */
    @NotEmpty(message = "添加用户时账号不能为空")
    private String name;

    /**
     * 单位名称
     */
    @NotEmpty(message = "添加用户时所属机构不能为空")
    private String company;

    /**
     * 密码
     */
    @NotEmpty(message = "添加用户时登录密码不能为空")
    private String password;

    /**
     * 用户类型：0管理员、1普通用户
     */
    @NotNull(message = "添加用户时用户类型不能为空")
    private Integer userType;
}
