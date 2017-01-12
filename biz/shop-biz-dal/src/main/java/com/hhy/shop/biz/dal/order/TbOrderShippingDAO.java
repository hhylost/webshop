/**
 *  File: com.hhy.shop.biz.dal.ITbOrderShippingDAO.java
 *  Description:These codes were generated by CodeSmith.
 *  Copyright 2015-2016 greenline.group Corporation. All rights reserved.
 *  @author
 */
package com.hhy.shop.biz.dal.order;

import com.hhy.shop.biz.dal.order.dataobject.TbOrderShippingDO;

import java.sql.SQLException;

/**
 * .
 * User: vinfer
 * Date: 15-2-20
 * Time: 上午9:21
 * To change this template use File | Settings | File Templates.
 */
public interface TbOrderShippingDAO {

   /**
     * 添加
     * 
     * @param tbOrderShippingDO
     * @return
     */
     Long add(TbOrderShippingDO tbOrderShippingDO);

    /**
     * 修改
     * 
     * @param tbOrderShippingDO
     * @return
     */
     int update(TbOrderShippingDO tbOrderShippingDO);

    /**
     * 根据活动id查找
     * 
     * @param id
     * @return
     */
     TbOrderShippingDO queryById(Long id);
     /**
     * 根据活动id删除
     * 
     * @param id
     * @return
     */
     int delete(Long id) throws SQLException;
     

}