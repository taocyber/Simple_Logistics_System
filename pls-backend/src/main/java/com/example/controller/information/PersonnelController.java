package com.example.controller.information;


import com.example.service.information.PersonnelService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class PersonnelController {


    @Resource
    PersonnelService service;



}