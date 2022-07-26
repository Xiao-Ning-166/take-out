package com.example.takeout.modules.shoppingCart.controller;

import com.example.takeout.modules.shoppingCart.entity.ShoppingCart;
import com.example.takeout.modules.shoppingCart.service.IShoppingCartService;
import com.example.takeout.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author xiaoning
 * @date 2022/07/25
 */
@RestController
@RequestMapping("/shoppingCart")
public class ShoppingCartController {

    @Autowired
    private IShoppingCartService shoppingCartService;

    /**
     * 获取用户购物车集合
     *
     * @return
     */
    @GetMapping("/list")
    public Result<?> list() {
        List<ShoppingCart> list = shoppingCartService.listShoppingCart();

        return Result.OK(list);
    }

    /**
     * 向购物车中添加一个菜品/套餐
     *
     * @param shoppingCart
     * @return
     */
    @PostMapping("/add")
    public Result<?> add(@RequestBody ShoppingCart shoppingCart) {
        ShoppingCart newShoppingCart = shoppingCartService.add(shoppingCart);

        return Result.OK(newShoppingCart);
    }

    /**
     * 向购物车中减少一个菜品/套餐
     *
     * @param shoppingCart
     * @return
     */
    @PostMapping("/sub")
    public Result<?> sub(@RequestBody ShoppingCart shoppingCart) {
        ShoppingCart newShoppingCart = shoppingCartService.sub(shoppingCart);

        return Result.OK(newShoppingCart);
    }

    /**
     * 清空用户购物车中的内容
     *
     * @return
     */
    @DeleteMapping("/clear")
    public Result<?> clear() {
        shoppingCartService.clear();

        return Result.OK().success("购物车已清空!");
    }

}
