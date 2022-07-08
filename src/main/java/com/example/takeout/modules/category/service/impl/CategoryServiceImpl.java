package com.example.takeout.modules.category.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.takeout.exception.TakeOutException;
import com.example.takeout.modules.category.entity.Category;
import com.example.takeout.modules.category.mapper.CategoryMapper;
import com.example.takeout.modules.category.service.ICategoryService;
import com.example.takeout.modules.dishes.entity.Dishes;
import com.example.takeout.modules.dishes.service.IDishesService;
import com.example.takeout.modules.setMeal.entity.SetMeal;
import com.example.takeout.modules.setMeal.service.ISetMealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xiaoning
 * @date 2022/07/08
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements ICategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private IDishesService dishesService;

    @Autowired
    private ISetMealService setMealService;

    /**
     * 添加分类信息
     *
     * @param category
     */
    @Override
    public void add(Category category) {
        // 1、非空判断
        if (ObjectUtils.isNull(category)) {
            throw new TakeOutException("分类信息不能为空!");
        }
        // 2、名称非空判断
        if (StringUtils.isEmpty(category.getName())) {
            throw new TakeOutException("分类名称不能为空!");
        }
        // 3、判断名称是否重复
        LambdaQueryWrapper<Category> categoryWrapper = new LambdaQueryWrapper<>();
        categoryWrapper.eq(Category::getName, category.getName());
        List<Category> categoryList = categoryMapper.selectList(categoryWrapper);
        if (CollectionUtils.isNotEmpty(categoryList)) {
            throw new TakeOutException("分类名称不能重复!");
        }
        // 4、保存分类信息
        categoryMapper.insert(category);
    }

    /**
     * 删除分类信息
     *
     * @param id
     */
    @Override
    public void delete(Long id) {
        Category category = categoryMapper.selectById(id);
        // 1、非空判断
        if (ObjectUtils.isNull(category)) {
            throw new TakeOutException("分类信息不存在!");
        }
        // 2、判断是否关联菜品表
        LambdaQueryWrapper<Dishes> dishesWrapper = new LambdaQueryWrapper<>();
        dishesWrapper.eq(Dishes::getCategoryId, id);
        List<Dishes> dishesList = dishesService.list(dishesWrapper);
        if (CollectionUtils.isNotEmpty(dishesList)) {
            throw new TakeOutException("该分类关联了菜品，请先删除对应菜品!");
        }
        // 3、判断是否关联套餐表
        LambdaQueryWrapper<SetMeal> setMealWrapper = new LambdaQueryWrapper<>();
        setMealWrapper.eq(SetMeal::getCategoryId, id);
        List<SetMeal> setMealList = setMealService.list(setMealWrapper);
        if (CollectionUtils.isNotEmpty(setMealList)) {
            throw new TakeOutException("该分类关联了套餐，请先删除对应套餐!");
        }
        // 4、删除分类信息
        categoryMapper.deleteById(id);
    }
}
