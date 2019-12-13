package com.nf.library.service.impl;

import com.nf.library.dao.AddressDao;
import com.nf.library.entity.Address;
import com.nf.library.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AddressServiceImpl implements AddressService {
    @Autowired
    private AddressDao addressDao;
    @Override
    public List<Address> getByPid(String pid) {
        return addressDao.getByPid(pid);
    }

}
