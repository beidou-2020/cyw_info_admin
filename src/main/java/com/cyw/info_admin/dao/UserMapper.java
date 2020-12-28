package com.cyw.info_admin.dao;

import com.cyw.info_admin.entity.User;
import com.cyw.info_admin.entity.query.UserPageQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {

    /**
     * 根据账号查找用户信息
     * @param name
     * @return
     */
    User findByName(@Param("username") String name);

    /**
     * 复合条件分页查询
     * @param query
     * @return
     */
    List<User> pageList(UserPageQuery query);

    /**
     * 删除用户信息
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 批量删除用户信息
     * @param list
     * @return
     */
    Integer batchDelete(@Param("list") List<Long> list);

    /**
     * 保存用户信息
     * @param record
     * @return
     */
    int insertSelective(User record);

    /**
     * 根据主键获取用户信息
     * @param id
     * @return
     */
    User selectByPrimaryKey(@Param("id") Long id);

    /**
     * 更新用户信息
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(User record);
}