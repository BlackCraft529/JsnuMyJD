package com.jsnu.jd.jsnujd.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jsnu.jd.jsnujd.utils.AddressUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 魏荣轩
 * @date 2020/8/8 21:35
 */
@Controller
public class UtilsController {
    private ObjectMapper jsonObjectMapper =  new ObjectMapper();

    /**
     * 获取用户的地址
     * @param jsonData json数据
     * @return 地址数据
     */
    @RequestMapping("/loginAction")
    @ResponseBody
    public String getAddressByIp(@RequestBody String jsonData) throws IOException {
        JsonNode node = jsonObjectMapper.readTree(jsonData);
        String ip=node.get("ip").toString();
        Map<String,String> address=new HashMap<>();
        address.put("location", AddressUtil.getLocationByIp(ip));
        return jsonObjectMapper.writeValueAsString(address);
    }
}
