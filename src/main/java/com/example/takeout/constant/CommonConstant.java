package com.example.takeout.constant;

/**
 * @author xiaoning
 * @date 2022/06/29
 */
public interface CommonConstant {

    /**
     * 响应成功
     */
    Integer SC_OK_200 = 200;

    /**
     * 服务器内部错误
     */
    Integer SC_SERVER_INNER_ERROR_500 = 500;

    /**
     * 资源未找到错误
     */
    Integer SC_PAGE_NOT_FOUND_ERROR_404 = 404;

    /**
     * 没有权限
     */
    Integer SC_NO_AUTHORITY = 403;


    /**
     * 员工状态为禁用
     */
    Integer EMPLOYEE_STATUS_DISABLED = 0;

    /**
     * 员工状态为正常
     */
    Integer EMPLOYEE_STATUS_ACTIVE = 1;


}
