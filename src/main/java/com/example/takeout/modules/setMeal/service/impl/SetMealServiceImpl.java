package com.example.takeout.modules.setMeal.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.takeout.modules.common.dto.SetMealDTO;
import com.example.takeout.modules.setMeal.entity.SetMeal;
import com.example.takeout.modules.setMeal.entity.SetMealDishes;
import com.example.takeout.modules.setMeal.mapper.SetMealMapper;
import com.example.takeout.modules.setMeal.service.ISetMealDishesService;
import com.example.takeout.modules.setMeal.service.ISetMealService;
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
public class SetMealServiceImpl extends ServiceImpl<SetMealMapper, SetMeal> implements ISetMealService {

    @Autowired
    private SetMealMapper setMealMapper;

    @Autowired
    private ISetMealDishesService setMealDishesService;

    /**
     * 分页查询套餐信息
     *
     * @param pageInfo
     * @param setMeal
     * @return
     */
    @Override
    public IPage<SetMeal> list(IPage<SetMeal> pageInfo, SetMeal setMeal) {
        return setMealMapper.list(pageInfo, setMeal);
    }

    /**
     * 添加一个套餐
     *
     * @param setMealDTO
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(SetMealDTO setMealDTO) {
        // 1、保存套餐基本信息
        SetMeal setMeal = new SetMeal();
        BeanUtils.copyProperties(setMealDTO, setMeal);
        setMealMapper.insert(setMeal);

        // 2、保存套餐中的菜品信息
        List<SetMealDishes> setMealDishesList = setMealDTO.getSetMealDishesList();
        setMealDishesList = setMealDishesList.stream().map((setMealDishes) -> {
            setMealDishes.setSetMealId(setMeal.getId());
            return setMealDishes;
        }).collect(Collectors.toList());
        setMealDishesService.saveBatch(setMealDishesList);
    }

    /**
     * 通过套餐id获取套餐信息
     *
     * @param id
     * @return
     */
    @Override
    public SetMealDTO querySetMealById(Long id) {
        return setMealMapper.querySetMealById(id);
    }

    /**
     * 修改套餐信息
     *
     * @param setMealDTO
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void edit(SetMealDTO setMealDTO) {
        // 1、更新套餐基本信息
        SetMeal setMeal = new SetMeal();
        BeanUtils.copyProperties(setMealDTO, setMeal);
        setMealMapper.updateById(setMeal);

        // 2、删除套餐中的菜品信息
        LambdaQueryWrapper<SetMealDishes> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SetMealDishes::getSetMealId, setMeal.getId());
        setMealDishesService.remove(queryWrapper);

        // 3、保存修改的菜品信息
        List<SetMealDishes> setMealDishesList = setMealDTO.getSetMealDishesList();
        setMealDishesList = setMealDishesList.stream().map((setMealDishes) -> {
            setMealDishes.setSetMealId(setMeal.getId());
            return setMealDishes;
        }).collect(Collectors.toList());
        setMealDishesService.saveBatch(setMealDishesList);
    }

    /**
     * 批量修改套餐状态
     *
     * @param ids
     * @param status
     */
    @Override
    public void batchStatus(String ids, Integer status) {
        setMealMapper.batchStatus(ids, status);
    }

    /**
     * 批量删除套餐
     *
     * @param ids
     */
    @Override
    public void batchDelete(String ids) {
        setMealMapper.batchDelete(ids);
    }
}
