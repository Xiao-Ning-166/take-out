package com.example.takeout.modules.dishes.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.takeout.modules.common.dto.DishesDTO;
import com.example.takeout.modules.dishes.entity.Dishes;

import java.util.List;

/**
 * @author xiaoning
 * @date 2022/07/08
 */
public interface IDishesService extends IService<Dishes> {

    /**
     * 分页查询菜品数据
     *
     * @param page
     * @param dishes
     * @return
     */
    IPage<Dishes> list(IPage<Dishes> page, Dishes dishes);

    /**
     * 添加菜品
     *
     * @param dishesDTO
     */
    void add(DishesDTO dishesDTO);


    /**
     * 根据id获取菜品信息
     *
     * @param id
     * @return
     */
    DishesDTO getDishesDTOById(Long id);

    /**
     * 修改菜品信息
     *
     * @param dishesDTO
     */
    void edit(DishesDTO dishesDTO);

    /**
     * 批量修改菜品状态
     *
     * @param ids
     * @param status
     */
    void batchStatus(String ids, Integer status);

    /**
     * 批量删除菜品
     *
     * @param ids
     */
    void batchDelete(String ids);

    /**
     * 通过条件获取菜品集合
     *
     * @param dishes
     * @return
     */
    List<Dishes> listDishesByCondition(Dishes dishes);
}
