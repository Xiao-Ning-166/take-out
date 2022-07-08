package com.example.takeout.modules.category.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.takeout.modules.category.entity.Category;

/**
 * @author xiaoning
 * @date 2022/07/08
 */
public interface ICategoryService extends IService<Category> {

    /**
     * 添加分类信息
     *
     * @param category
     */
    void add(Category category);

    /**
     * 删除分类信息
     *
     * @param id
     */
    void delete(Long id);
}
