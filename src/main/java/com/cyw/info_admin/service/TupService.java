package com.cyw.info_admin.service;

import com.cyw.info_admin.entity.Tup;
import com.cyw.info_admin.entity.common.PageParam;
import com.cyw.info_admin.entity.query.TupPageQuery;
import com.github.pagehelper.PageInfo;

public interface TupService {

    /**
     * 根据主键获取物品提交记录
     * @param id
     * @return
     */
    Tup selectByPrimaryKey(Long id);

    /**
     * 分页获取提交信息
     * @param pageParam
     * @param query
     * @return
     */
    PageInfo<Tup> pageList(PageParam pageParam, TupPageQuery query);

    /**
     * 审核/驳回提交数据
     * @param id
     * @param status
     * @return
     */
    Integer updateStatusById(Long id, Integer status);

    /**
     * 批量审核/驳回提交数据
     * @param ids
     * @param status
     * @return
     */
    Integer batchUpdateStatusById(String ids, Integer status);
}
