package com.example.takeout.modules.setMeal.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.takeout.modules.common.dto.SetMealDTO;
import com.example.takeout.modules.setMeal.entity.SetMeal;

/**
 * @author xiaoning
 * @date 2022/07/08
 */
public interface ISetMealService extends IService<SetMeal> {

    /**
     * 分页查询套餐信息
     *
     * @param pageInfo
     * @param setMeal
     * @return
     */
    IPage<SetMeal> list(IPage<SetMeal> pageInfo, SetMeal setMeal);

    /**
     * 添加一个套餐
     *
     * @param setMealDTO
     */
    void add(SetMealDTO setMealDTO);

    /**
     * 通过套餐id获取套餐信息
     *
     * @param id
     * @return
     */
    SetMealDTO querySetMealById(Long id);

    /**
     * 修改套餐信息
     *
     * @param setMealDTO
     */
    void edit(SetMealDTO setMealDTO);

    /**
     * 批量修改套餐状态
     *
     * @param ids
     * @param status
     */
    void batchStatus(String ids, Integer status);

    /**
     * 批量删除套餐
     *
     * @param ids
     */
    void batchDelete(String ids);
}
