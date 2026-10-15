package com.gaofeiyang.wms.controller;

import com.gaofeiyang.wms.controller.args.LoginRequest;
import com.gaofeiyang.wms.controller.args.LoginResponse;
import com.gaofeiyang.wms.service.WMSService;
import com.gaofeiyang.wms.service.model.Goods;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/")
public class Controller {
    @Autowired
    WMSService service;

    @RequestMapping(value = "/login", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public LoginResponse login(@RequestBody LoginRequest req) {
        return service.login(req);
    }

    @RequestMapping(value = "/goods", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Goods> getGoodsList() {
        return service.getGoodsList();
    }

}
