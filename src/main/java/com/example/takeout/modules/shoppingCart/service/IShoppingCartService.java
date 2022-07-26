package com.example.takeout.modules.shoppingCart.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.takeout.modules.shoppingCart.entity.ShoppingCart;

import java.util.List;

/**
 * @author xiaoning
 * @date 2022/07/25
 */
public interface IShoppingCartService extends IService<ShoppingCart> {

    /**
     * 得到用户购物车中的商品集合
     *
     * @return
     */
    List<ShoppingCart> listShoppingCart();

    /**
     * 向购物车中添加一个菜品/套餐
     *
     * @param shoppingCart
     * @return
     */
    ShoppingCart add(ShoppingCart shoppingCart);

    /**
     * 购物车中减少一个菜品/套餐
     *
     * @param shoppingCart
     * @return
     */
    ShoppingCart sub(ShoppingCart shoppingCart);

    /**
     * 清空用户购物车中的内容
     */
    void clear();
}
