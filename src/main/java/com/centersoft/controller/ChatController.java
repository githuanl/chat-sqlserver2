package com.centersoft.controller;

import com.centersoft.entity.MessageEntity;
import com.centersoft.enums.EResultType;
import com.centersoft.service.ChatSerivice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping(value = "/chat")
public class ChatController extends BaseController<MessageEntity> {

    @Autowired
    ChatSerivice chatSerivice;

    @ResponseBody
    @RequestMapping(value = "/t")
    public String test() {
        return retResultData(EResultType.SUCCESS, "ks");
    }

    @ResponseBody
    @RequestMapping(value = "/t4")
    public String tests() {
        return retResultData(EResultType.SUCCESS);
    }


}
