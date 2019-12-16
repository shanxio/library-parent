package com.nf.library.controller.be.address;


import com.nf.library.controller.be.BaseController;
import com.nf.library.entity.Address;
import com.nf.library.security.process.ResponseVo;
import com.nf.library.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Sam
 */
@Controller
@RestController
@RequestMapping("/admin/address")
public class AddressController extends BaseController {
    @Autowired
    private AddressService addressService;
    @RequestMapping("/pid")
    public ResponseVo getByPid(String  pid){
        this.checkNull(pid);
        List<Address> addresses = addressService.getByPid(pid);
        return ResponseVo.builder().code("200").data(addresses).build();
    }

}
