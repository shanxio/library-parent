package com.nf.library.service;

import com.nf.library.entity.Address;

import java.util.List;

/**
 * 地址表的相关业务。
 * @author Sam
 */
public interface AddressService {
    /**
     * 地址的级联操作
     * @param pid
     * @return
     */
    List<Address> getByPid(String pid);
}
