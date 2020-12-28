package com.cyw.info_admin.service;

import com.cyw.info_admin.entity.User;
import com.cyw.info_admin.entity.common.PageParam;
import com.cyw.info_admin.entity.dto.AddUserDTO;
import com.cyw.info_admin.entity.dto.EditUserDTO;
import com.cyw.info_admin.entity.query.UserPageQuery;
import com.github.pagehelper.PageInfo;

public interface UserService {

    /**
     * 根据账号查询用户信息
     * @param username
     * @return
     */
    User findByName(String username);

    /**
     * 根据主键获取用户信息
     * @param id
     * @return
     */
    User selectByPrimaryKey(Long id);

    /**
     * 复合条件分页查询
     * @param pageParam
     * @param query
     * @return
     */
    PageInfo<User> pageList(PageParam pageParam, UserPageQuery query);

    /**
     * 保存用户信息
     * @param addUserDTO
     * @return
     */
    Integer saveUser(AddUserDTO addUserDTO);

    /**
     * 编辑用户信息
     * @param editUserDTO
     * @return
     */
    Integer updateUser(EditUserDTO editUserDTO);

    /**
     * 删除用户信息
     * @param id
     * @return
     */
    Integer deleteUserInfo(Long id);

    /**
     * 批量删除用户
     * @param ids
     * @return
     */
    Integer batchDeleteUser(String ids);
}
