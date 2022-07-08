package com.example.takeout.modules.category.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.takeout.modules.category.entity.Category;
import com.example.takeout.modules.category.service.ICategoryService;
import com.example.takeout.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xiaoning
 * @date 2022/07/08
 */
@Slf4j
@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private ICategoryService categoryService;

    /**
     * 分页查询分类信息
     *
     * @param category
     * @param current
     * @param size
     * @return
     */
    @GetMapping("/list")
    public Result<?> list(Category category,
                          @RequestParam(name = "current", defaultValue = "1") Integer current,
                          @RequestParam(name = "size", defaultValue = "10") Integer size) {
        LambdaQueryWrapper<Category> categoryQueryWrapper = new LambdaQueryWrapper<>();
        categoryQueryWrapper.orderByAsc(Category::getSort);
        IPage<Category> page = new Page<>(current, size);
        IPage<Category> pageInfo = categoryService.page(page, categoryQueryWrapper);
        return Result.OK(pageInfo);
    }

    /**
     * 添加分类信息
     *
     * @param category
     * @return
     */
    @PostMapping("/add")
    public Result<?> add(@RequestBody Category category) {
        categoryService.add(category);

        return Result.OK().success("添加分类信息成功!");
    }

    /**
     * 修改分类信息
     *
     * @param category
     * @return
     */
    @PutMapping("/edit")
    public Result<?> edit(@RequestBody Category category) {
        categoryService.updateById(category);

        return Result.OK().success("更新分类信息成功!");
    }

    /**
     * 删除分类信息
     *
     * @param id
     * @return
     */
    @DeleteMapping("/delete")
    public Result<?> delete(Long id) {

        categoryService.delete(id);

        return Result.OK().success("删除分类成功!");
    }

}
