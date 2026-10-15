package com.gaofeiyang.wms.service;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.gaofeiyang.wms.controller.args.LoginRequest;
import com.gaofeiyang.wms.controller.args.LoginResponse;
import com.gaofeiyang.wms.service.model.Goods;
import com.gaofeiyang.wms.service.model.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class WMSService {
    private final static ObjectMapper OBJECT_MAPPER = JsonMapper.builder()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            .build()
            .setSerializationInclusion(JsonInclude.Include.NON_NULL);

    @Resource
    DataSource dataSource;

    public LoginResponse login(LoginRequest req) {
        boolean success = false;
        String message = "用户不存在或者密码错";

        TransactionalJdbcTemplate jdbcTemplate = new TransactionalJdbcTemplate(dataSource);
        List<Map<String, Object>> rs = jdbcTemplate.queryForList("select * from user where id = ?", req.getId());
        if (!rs.isEmpty()) {
            User user = convert(rs.get(0), User.class);
            if (user.getPassword().equals(req.getPassword())) {
                success = true;
                message = "用户[" + user.getName()  + "]成功登录";
            }
        }

        return new LoginResponse(success, message);
    }

    public List<Goods> getGoodsList() {
        TransactionalJdbcTemplate jdbcTemplate = new TransactionalJdbcTemplate(dataSource);//建立和数据库的链接
        List<Map<String, Object>> rs = jdbcTemplate.queryForList("select * from goods where GoodsId != '4'");//
        List<Goods> goods = new ArrayList<>();
        if (!rs.isEmpty()) {
            for (Map<String, Object> rec : rs) {
                Goods g = convert(rec, Goods.class);
                goods.add(g);
            }
        }

        return goods;
    }







    private <T> T convert(Map<String, Object> record, Class<T> klass) {
        try {
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            OBJECT_MAPPER.writeValue(output, record);
            return OBJECT_MAPPER.readValue(output.toByteArray(),  klass);
        } catch (Exception ex) {
            throw new RuntimeException("failed to convert record to object", ex);
        }
    }
}
