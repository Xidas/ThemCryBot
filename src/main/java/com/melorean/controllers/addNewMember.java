package com.melorean.controllers;

import com.melorean.entity.MemberEntity;
import com.melorean.services.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class addNewMember {

    @Autowired
    MemberService service;

    @GetMapping(value = "/add")
    public void addNewMember(){
        try {
//            List<MemberEntity> list = new ArrayList<>();
//            list.add(new MemberEntity(null, "asdadas","asdadas","asdadas",new Date()));
//            service.saveOrUpdateAllMembers(list);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
