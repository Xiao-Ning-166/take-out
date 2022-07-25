package com.example.takeout.modules.dishes.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.takeout.modules.common.dto.DishesDTO;
import com.example.takeout.modules.dishes.entity.Dishes;
import com.example.takeout.modules.dishes.entity.Flavor;
import com.example.takeout.modules.dishes.mapper.DishesMapper;
import com.example.takeout.modules.dishes.service.IDishesService;
import com.example.takeout.modules.dishes.service.IFlavorService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author xiaoning
 * @date 2022/07/08
 */
@Service
public class DishesServiceImpl extends ServiceImpl<DishesMapper, Dishes> implements IDishesService {

    @Autowired
    private DishesMapper dishesMapper;

    @Autowired
    private IFlavorService flavorService;

    /**
     * 分页查询菜品数据
     *
     * @param page
     * @param dishes
     * @return
     */
    @Override
    public IPage<Dishes> list(IPage<Dishes> page, Dishes dishes) {
        return dishesMapper.list(page, dishes);
    }

    /**
     * 添加菜品
     *
     * @param dishesDTO
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(DishesDTO dishesDTO) {
        Dishes dishes = new Dishes();
        // 保存菜品信息
        BeanUtils.copyProperties(dishesDTO, dishes);
        dishesMapper.insert(dishes);

        // 保存口味信息
        List<Flavor> flavors = dishesDTO.getFlavors();
        flavors = flavors.stream().map((flavor) -> {
            flavor.setDishesId(dishes.getId());
            return flavor;
        }).collect(Collectors.toList());
        flavorService.saveBatch(flavors);
    }

    /**
     * 根据id获取菜品信息
     *
     * @param id
     * @return
     */
    @Override
    public DishesDTO getDishesDTOById(Long id) {
        return dishesMapper.getDishesDTOById(id);
    }

    /**
     * 修改菜品信息
     *
     * @param dishesDTO
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void edit(DishesDTO dishesDTO) {
        Dishes dishes = new Dishes();
        BeanUtils.copyProperties(dishesDTO, dishes);

        // 1、更新菜品信息
        dishesMapper.updateById(dishes);

        // 2、先删除菜品的口味信息
        LambdaQueryWrapper<Flavor> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Flavor::getDishesId, dishes.getId());
        flavorService.remove(queryWrapper);

        // 3、保存菜品的口味信息
        List<Flavor> flavors = dishesDTO.getFlavors();
        flavors = flavors.stream().map((flavor) -> {
            flavor.setDishesId(dishes.getId());
            return flavor;
        }).collect(Collectors.toList());
        flavorService.saveBatch(flavors);
    }

    /**
     * 批量修改菜品状态
     *
     * @param ids
     * @param status
     */
    @Override
    public void batchStatus(String ids, Integer status) {
        dishesMapper.batchStatus(ids, status);
    }

    /**
     * 批量删除菜品
     *
     * @param ids
     */
    @Override
    public void batchDelete(String ids) {
        dishesMapper.batchDelete(ids);
    }

    /**
     * 通过条件获取菜品集合
     *
     * @param dishes
     * @return
     */
    @Override
    public List<DishesDTO> listDishesByCondition(Dishes dishes) {
        return dishesMapper.listDishesByCondition(dishes);
    }
}
