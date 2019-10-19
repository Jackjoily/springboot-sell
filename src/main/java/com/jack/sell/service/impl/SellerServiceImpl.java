package com.jack.sell.service.impl;

import com.jack.sell.dataobject.SellerInfo;
import com.jack.sell.repository.SellInfoRepository;
import com.jack.sell.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: sell
 * @description:
 * @author: jackjoily
 * @create: 2019-10-17 17:11
 */
@Service
public class SellerServiceImpl implements SellerService {
    @Autowired
    private SellInfoRepository repository;
    @Override
    public SellerInfo findSellerInfoByopenid(String openid) {
        return repository.findByOpenid(openid);
    }
}