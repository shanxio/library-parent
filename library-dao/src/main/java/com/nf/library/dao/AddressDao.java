package com.nf.library.dao;

import com.nf.library.entity.Address;

import java.util.List;

/**
 * 地址的相关操作
 * @author Sam
 */
public interface AddressDao {
    /**
     * 地址的级联操作
     * @param pid
     * @return
     */
    List<Address> getByPid(String pid);
}
