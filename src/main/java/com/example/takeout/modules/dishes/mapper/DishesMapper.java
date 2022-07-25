package com.example.takeout.modules.dishes.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.takeout.modules.common.dto.DishesDTO;
import com.example.takeout.modules.dishes.entity.Dishes;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author xiaoning
 * @date 2022/07/08
 */
@Mapper
public interface DishesMapper extends BaseMapper<Dishes> {

    /**
     * 分页查询菜品数据
     *
     * @param page
     * @param dishes
     * @return
     */
    IPage<Dishes> list(IPage<Dishes> page, Dishes dishes);

    /**
     * 根据id获取菜品信息
     *
     * @param id
     * @return
     */
    DishesDTO getDishesDTOById(Long id);

    /**
     * 批量修改菜品售卖状态
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
    List<DishesDTO> listDishesByCondition(@Param("dishes") Dishes dishes);
}
