package com.example.takeout.modules.setMeal.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.takeout.modules.common.dto.SetMealDTO;
import com.example.takeout.modules.setMeal.entity.SetMeal;
import com.example.takeout.modules.setMeal.service.ISetMealDishesService;
import com.example.takeout.modules.setMeal.service.ISetMealService;
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

import java.util.List;

/**
 * @author xiaoning
 * @date 2022/07/18
 */
@Slf4j
@RestController
@RequestMapping("/setMeal")
public class SetMealController {

    @Autowired
    private ISetMealService setMealService;

    /**
     * 分页查询套餐信息
     *
     * @param setMeal
     * @param current 当前第几页
     * @param size    每页大小
     * @return
     */
    @GetMapping("/list")
    public Result<?> list(SetMeal setMeal,
                          @RequestParam(name = "current", defaultValue = "1") Integer current,
                          @RequestParam(name = "size", defaultValue = "10") Integer size) {
        IPage<SetMeal> pageInfo = new Page<>(current, size);
        IPage<SetMeal> setMealInfo = setMealService.list(pageInfo, setMeal);

        return Result.OK(setMealInfo);
    }

    /**
     * 添加一个套餐
     *
     * @param setMealDTO
     * @return
     */
    @PostMapping("/add")
    public Result<?> add(@RequestBody SetMealDTO setMealDTO) {
        setMealService.add(setMealDTO);

        return Result.OK().success("套餐添加成功!");
    }

    /**
     * 通过id获取套餐信息
     *
     * @param id
     * @return
     */
    @GetMapping("/getById")
    public Result<?> getById(Long id) {
        SetMealDTO setMealDTO = setMealService.querySetMealById(id);

        return Result.OK(setMealDTO);
    }

    /**
     * 修改套餐信息
     *
     * @param setMealDTO
     * @return
     */
    @PutMapping("/edit")
    public Result<?> edit(@RequestBody SetMealDTO setMealDTO) {
        setMealService.edit(setMealDTO);

        return Result.OK().success("套餐信息修改成功!");
    }

    /**
     * 批量修改套餐状态
     *
     * @param ids
     * @param status
     * @return
     */
    @GetMapping("/batchStatus")
    public Result<?> batchStatus(String ids, Integer status) {
        setMealService.batchStatus(ids, status);
        return Result.OK().success("套餐状态修改成功!");
    }

    /**
     * 批量删除套餐
     *
     * @param ids
     * @return
     */
    @DeleteMapping("/batchDelete")
    public Result<?> batchDelete(String ids) {
        setMealService.batchDelete(ids);
        return Result.OK().success("套餐删除成功!");
    }

    @GetMapping("/listSetMeal")
    public Result<?> listSetMeal(SetMeal setMeal) {

        List<SetMealDTO> list = setMealService.listSetMeal(setMeal);

        return Result.OK(list);
    }
}
