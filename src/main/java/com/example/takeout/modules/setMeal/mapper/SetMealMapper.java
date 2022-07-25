package com.example.takeout.modules.setMeal.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.takeout.modules.common.dto.SetMealDTO;
import com.example.takeout.modules.setMeal.entity.SetMeal;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author xiaoning
 * @date 2022/07/08
 */
@Mapper
public interface SetMealMapper extends BaseMapper<SetMeal> {

    /**
     * 分页查询套餐信息
     *
     * @param pageInfo
     * @param setMeal
     * @return
     */
    IPage<SetMeal> list(IPage<SetMeal> pageInfo, SetMeal setMeal);

    /**
     * 通过套餐id查询套餐信息
     *
     * @param id
     * @return
     */
    SetMealDTO querySetMealById(Long id);

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

    /**
     * 根据跳槽查询套餐集合
     *
     * @param setMeal
     * @return
     */
    List<SetMealDTO> listSetMeal(@Param("setMeal") SetMeal setMeal);
}
