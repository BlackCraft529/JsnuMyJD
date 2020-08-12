package com.jsnu.jd.jsnujd.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jsnu.jd.jsnujd.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author 魏荣轩
 * @date 2020/8/12 20:05
 */
@Controller
public class OrderController {
    @Autowired
    @Qualifier("OrderService")
    private OrderService orderService;

    /**
     * json工具对象
     */
    private ObjectMapper jsonObjectMapper =  new ObjectMapper();

    /**
     * 获取用户order数据
     * @param jsonData json数据
     * @return order数据表
     * @throws JsonProcessingException json转换错误
     */
    @RequestMapping("/getOrder")
    @ResponseBody
    public String getOrder(@RequestBody String jsonData) throws JsonProcessingException {
        JsonNode node = jsonObjectMapper.readTree(jsonData);
        String userId =  node.get("uuid").toString().replaceAll("\"","");
        return jsonObjectMapper.valueToTree(orderService.getNewestOrderListByUserId(userId)).toString();
    }


}
