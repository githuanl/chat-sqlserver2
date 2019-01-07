package com.centersoft.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.centersoft.constant.CookieConstant;
import com.centersoft.constant.RedisConstant;
import com.centersoft.entity.UserEntity;
import com.centersoft.enums.EResultType;
import com.centersoft.service.GroupSerivice;
import com.centersoft.service.UserSerivice;
import com.centersoft.utils.CookieUtil;
import com.centersoft.utils.MD5Util;
import io.netty.util.internal.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Controller
@RequestMapping(value = "/user")
public class UserController extends BaseController<UserEntity> {

    @Value("${server.port}")
    private Integer port;

    @Autowired
    UserSerivice userSerivice;

    @Autowired
    GroupSerivice groupSerivice;

    /**
     * 登录
     *
     * @param name
     * @param password
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/login")
    public String login(String name, String password, HttpServletResponse response) {

        if (StringUtil.isNullOrEmpty(name) || StringUtil.isNullOrEmpty(password)) {
            return retResultData(-1, "用户名或密码不能为空");
        }
        UserEntity user = userSerivice.findUserByUserName(name);
        return setToken(user, password, response);
    }


    /**
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getUserInfo")
    public String getUserInfo() {
        return retResultData(EResultType.SUCCESS, getCurrentUser());
    }


    // 设备coockie
    private String setToken(UserEntity user, String password, HttpServletResponse response) {

        if (user != null) {
            if (!user.getPassword().equals(password)) {
                return retResultData(EResultType.PASSWORK_ERROR);
            }
            user.setPassword("");
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            String token = MD5Util.getMD5(String.format(RedisConstant.TOKEN_PREFIX, df.format(new Date()) + user.getId())).toUpperCase();
            // 设置token至redis
            Integer expire = RedisConstant.EXPIRE;
            redisTemplate.opsForValue().set(String.format(RedisConstant.TOKEN_PREFIX, token), user.getEmpid(), expire, TimeUnit.SECONDS);
            CookieUtil.set(response, CookieConstant.TOKEN, token, expire);
            return retResultData(EResultType.SUCCESS, user);
        } else {
            return retResultData(EResultType.PASSWORK_ERROR);
        }
    }

    // 注册
    @ResponseBody
    @GetMapping(value = "/register")
    public String register(String name, String password, String avatar) {

        if (StringUtil.isNullOrEmpty(name) || StringUtil.isNullOrEmpty(password)) {
            return retResultData(-1, "用户名或密码不能为空");
        }

        UserEntity user = userSerivice.findUserByUserName(name);
        if (user != null) {
            return retResultData(-1, "用户名已存在");
        }

        user = userSerivice.register(name, password, avatar);

        return retResultData(EResultType.SUCCESS, user);
    }

    //退出登录
    @RequestMapping(value = "/logout")
    public String logout() {
        return "/static/index.html";
    }

    //获取当前登录人的Token
    @ResponseBody
    @GetMapping(value = "/getToken")
    public String getAuthToken() {
        return retResultData(EResultType.SUCCESS, getCurrentUser().getAuth_token());
    }


    //修改 个性签名
    @ResponseBody
    @PostMapping(value = "/updateSign")
    public String updateSign(String sign) {

        UserEntity entity = getCurrentUser();
        entity = (UserEntity) userSerivice.getEntityById(entity.getId());
        entity.setSign(sign);
        userSerivice.saveEntity(entity);

        return retResultData(0, "修改成功");
    }


    /**
     * 目前是 查询系统中的 所有人员
     * 及 自己所在的群
     * 和 创建的群
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/findAllUser")
    public String findAllUser() {

        //获取所有的群组
        UserEntity userEntity = getCurrentUser();

        JSONObject obj = new JSONObject();
        obj.put("mine", userEntity);

        //分组
        JSONArray array = new JSONArray();
        JSONObject f = new JSONObject();
        f.put("groupname", "我的好友");
        f.put("id", "0");
        f.put("list", userSerivice.selectAll());
        array.add(f);
        obj.put("friend", array);

        obj.put("group", groupSerivice.findMyGroupsByUserId(userEntity.getId()));

        return retResultData(0, "", obj);
    }


    /**
     * 询消息盒子信息
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/findUsersByName")
    public String findUsersByName(String page, String name) {

        List<UserEntity> list = userSerivice.findUsersByName(page, name);
        return retResultData(0, "", list);
    }


    @Value("${web.upload-path}")
    private String path;        //文件上传的路径

    //文件上传
    @PostMapping(value = "/upload")
    public @ResponseBody
    String uploadImg(@RequestParam(value = "file", required = false) MultipartFile file,
                     HttpServletRequest request) {
        if (file.isEmpty()) {
            return retResultData(-1, "上传文件不能为空");
        }

//        String contentType = file.getContentType();
        String fileName = file.getOriginalFilename();
        String uuid = UUID.randomUUID().toString();

        byte[] bytes = new byte[0];
        try {
            bytes = file.getBytes();
            Path ph = Paths.get(path + uuid + fileName);

            Files.write(ph, bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }

//        InetAddress addr = InetAddress.getLocalHost();
//        String ip = addr.getHostAddress();//获得本机IP
        JSONObject obj = new JSONObject();
        obj.put("src", "/static/" + uuid + fileName);
        //返回json
        return retResultData(0, "", obj);
    }

}
