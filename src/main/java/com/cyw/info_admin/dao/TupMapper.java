package com.cyw.info_admin.dao;

import com.cyw.info_admin.entity.Tup;
import com.cyw.info_admin.entity.query.TupPageQuery;
import org.apache.ibatis.annotations.Param;
import java.util.List;

public interface TupMapper {

    /**
     * 复合条件分页查询
     * @param query
     * @return
     */
    List<Tup> pageListByQuery(TupPageQuery query);

    int deleteByPrimaryKey(Long id);


    /**
     * 根据主键获取物品提交记录
     * @param id
     * @return
     */
    Tup selectByPrimaryKey(@Param("id") Long id);

    int updateByPrimaryKeySelective(Tup record);

    int updateByPrimaryKey(Tup record);

    /**
     * 根据主键修改状态
     * @param id
     * @param status
     * @return
     */
    Integer updateStatusById(@Param("id") Long id, @Param("status") Integer status);

    /**
     * 批量修改提交数据的状态
     * @param idList
     * @param status
     * @return
     */
    Integer batchUpdateStatusById(@Param("idList") List<Long> idList, @Param("status") Integer status);
}