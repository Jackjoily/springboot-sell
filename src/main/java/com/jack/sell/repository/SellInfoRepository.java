package com.jack.sell.repository;

import com.jack.sell.dataobject.SellerInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @program: sell
 * @description:
 * @author: jackjoily
 * @create: 2019-10-17 16:50
 */
@Repository
public interface SellInfoRepository extends JpaRepository<SellerInfo,String> {
SellerInfo findByOpenid(String openid);
}