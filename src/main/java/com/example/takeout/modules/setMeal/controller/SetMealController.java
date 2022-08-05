package com.example.takeout.modules.setMeal.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.takeout.modules.common.dto.SetMealDTO;
import com.example.takeout.modules.setMeal.entity.SetMeal;
import com.example.takeout.modules.setMeal.service.ISetMealDishesService;
import com.example.takeout.modules.setMeal.service.ISetMealService;
import com.example.takeout.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api(tags = "套餐管理")
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
    @ApiOperation(value = "分页查询", notes = "分页查询")
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
    @ApiOperation(value = "添加套餐", notes = "添加套餐")
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
    @ApiOperation(value = "查询套餐信息", notes = "查询套餐信息")
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
    @ApiOperation(value = "修改套餐信息", notes = "修改套餐信息")
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
    @ApiOperation(value = "批量修改套餐状态", notes = "批量修改套餐状态")
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
    @ApiOperation(value = "批量删除套餐", notes = "批量删除套餐")
    public Result<?> batchDelete(String ids) {
        setMealService.batchDelete(ids);
        return Result.OK().success("套餐删除成功!");
    }

    /**
     * 通过条件查询套餐集合
     *
     * @param setMeal
     * @return
     */
    @GetMapping("/listSetMeal")
    @ApiOperation(value = "通过条件查询套餐", notes = "通过条件查询套餐")
    public Result<?> listSetMeal(SetMeal setMeal) {

        List<SetMealDTO> list = setMealService.listSetMeal(setMeal);

        return Result.OK(list);
    }
}
