package com.cyw.info_admin.entity.query;

import lombok.Data;
import lombok.ToString;
import java.io.Serializable;

@Data
@ToString
public class UserPageQuery implements Serializable {

    /**
     * 账号
     */
    private String username;

    /**
     * 单位名称
     */
    private String company;
}
