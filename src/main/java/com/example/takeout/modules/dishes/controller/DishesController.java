package com.example.takeout.modules.dishes.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.takeout.modules.common.dto.DishesDTO;
import com.example.takeout.modules.dishes.entity.Dishes;
import com.example.takeout.modules.dishes.service.IDishesService;
import com.example.takeout.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 菜品控制器
 *
 * @author xiaoning
 * @date 2022/07/12
 */
@RestController
@RequestMapping("/dishes")
public class DishesController {

    @Autowired
    private IDishesService dishesService;

    /**
     * 分页查询菜品信息
     *
     * @param dishes
     * @param current
     * @param size
     * @return
     */
    @GetMapping("/list")
    public Result<?> list(Dishes dishes,
                          @RequestParam(name = "current", defaultValue = "1") Integer current,
                          @RequestParam(name = "size", defaultValue = "10") Integer size) {
        IPage<Dishes> dishesPage = new Page<>(current, size);
        IPage<Dishes> dishesInfo = dishesService.list(dishesPage, dishes);
        return Result.OK(dishesInfo);
    }

    /**
     * 添加一个菜品
     *
     * @param dishesDto
     * @return
     */
    @PostMapping("/add")
    public Result<?> add(@RequestBody DishesDTO dishesDto) {

        dishesService.add(dishesDto);

        return Result.OK().success("菜品添加成功!");
    }

    /**
     * 通过id获取菜品信息
     *
     * @param id
     * @return
     */
    @GetMapping("/getById")
    public Result<?> getById(Long id) {
        DishesDTO dishesDTO = dishesService.getDishesDTOById(id);
        return Result.OK(dishesDTO);
    }

    /**
     * 修改菜品信息
     *
     * @param dishesDTO
     * @return
     */
    @PutMapping("/edit")
    public Result<?> edit(@RequestBody DishesDTO dishesDTO) {
        dishesService.edit(dishesDTO);
        return Result.OK().success("菜品信息修改成功!");
    }


    /**
     * 批量修改菜品状态
     *
     * @param ids
     * @param status
     * @return
     */
    @GetMapping("/batchStatus")
    public Result<?> batchStatus(String ids, Integer status) {
        dishesService.batchStatus(ids, status);
        return Result.OK().success("菜品状态修改成功!");
    }

    /**
     * 批量删除菜品
     *
     * @param ids
     * @return
     */
    @DeleteMapping("/batchDelete")
    public Result<?> batchDelete(String ids) {
        dishesService.batchDelete(ids);
        return Result.OK().success("菜品删除成功!");
    }

    /**
     * 通过条件获取菜品集合
     *
     * @param dishes
     * @return
     */
    @GetMapping("/listDishes")
    public Result<?> listDishes(Dishes dishes) {
        List<DishesDTO> list = dishesService.listDishesByCondition(dishes);

        return Result.OK(list);
    }

}
