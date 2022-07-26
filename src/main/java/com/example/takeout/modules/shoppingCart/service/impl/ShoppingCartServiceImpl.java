package com.example.takeout.modules.shoppingCart.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.takeout.modules.shoppingCart.entity.ShoppingCart;
import com.example.takeout.modules.shoppingCart.mapper.ShoppingCartMapper;
import com.example.takeout.modules.shoppingCart.service.IShoppingCartService;
import com.example.takeout.utils.BaseContext;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author xiaoning
 * @date 2022/07/25
 */
@Service
public class ShoppingCartServiceImpl extends ServiceImpl<ShoppingCartMapper, ShoppingCart> implements IShoppingCartService {

    @Autowired
    private ShoppingCartMapper shoppingCartMapper;

    /**
     * 得到用户购物车中的商品集合
     *
     * @return
     */
    @Override
    public List<ShoppingCart> listShoppingCart() {
        Long loginUserId = BaseContext.getLoginUserId();

        LambdaQueryWrapper<ShoppingCart> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ShoppingCart::getUserId, loginUserId);

        List<ShoppingCart> shoppingCarts = shoppingCartMapper.selectList(queryWrapper);
        return shoppingCarts;
    }

    /**
     * 向购物车中添加一个菜品/套餐
     *
     * @param shoppingCart
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ShoppingCart add(ShoppingCart shoppingCart) {
        // 1、获取当前登录的用户id
        Long loginUserId = BaseContext.getLoginUserId();
        shoppingCart.setUserId(loginUserId);

        LambdaQueryWrapper<ShoppingCart> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ShoppingCart::getUserId, loginUserId);

        // 2、判断添加的是菜品还是套餐
        Long dishesId = shoppingCart.getDishesId();
        if (dishesId != null) {
            // 添加的是菜品
            queryWrapper.eq(ShoppingCart::getDishesId, dishesId);
        } else {
            // 添加的是套餐
            queryWrapper.eq(ShoppingCart::getSetMealId, shoppingCart.getSetMealId());
        }

        // 3、判断当前菜品/套菜在购物车中是否已存在
        ShoppingCart cartOne = shoppingCartMapper.selectOne(queryWrapper);
        if (ObjectUtils.isNull(cartOne)) {
            // 不存在，直接保存
            shoppingCartMapper.insert(shoppingCart);
            cartOne = shoppingCart;
        } else {
            // 存在，更新数量（在原来基础上+1）
            Integer number = cartOne.getNumber();
            cartOne.setNumber(number + 1);
            shoppingCartMapper.updateById(cartOne);
        }

        return cartOne;
    }

    /**
     * 购物车中减少一个菜品/套餐
     *
     * @param shoppingCart
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ShoppingCart sub(ShoppingCart shoppingCart) {
        // 1、获取当前登录的用户id
        Long loginUserId = BaseContext.getLoginUserId();
        shoppingCart.setUserId(loginUserId);

        LambdaQueryWrapper<ShoppingCart> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ShoppingCart::getUserId, loginUserId);

        // 2、判断添加的是菜品还是套餐
        Long dishesId = shoppingCart.getDishesId();
        if (dishesId != null) {
            // 添加的是菜品
            queryWrapper.eq(ShoppingCart::getDishesId, dishesId);
        } else {
            // 添加的是套餐
            queryWrapper.eq(ShoppingCart::getSetMealId, shoppingCart.getSetMealId());
        }

        // 3、判断当前菜品/套菜在购物车中的数量是否大于1
        ShoppingCart cartOne = shoppingCartMapper.selectOne(queryWrapper);
        if (cartOne.getNumber() > 1) {
            // 数量大于1，更新数量（减1）
            Integer number = cartOne.getNumber();
            cartOne.setNumber(number - 1);
            shoppingCartMapper.updateById(cartOne);
        } else {
            // 数量刚好=1，删除这个菜品/套餐
            shoppingCartMapper.deleteById(cartOne);
        }

        return cartOne;
    }

    /**
     * 清空用户购物车中的内容
     */
    @Override
    public void clear() {
        Long loginUserId = BaseContext.getLoginUserId();

        LambdaQueryWrapper<ShoppingCart> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ShoppingCart::getUserId, loginUserId);

        shoppingCartMapper.delete(queryWrapper);
    }
}
