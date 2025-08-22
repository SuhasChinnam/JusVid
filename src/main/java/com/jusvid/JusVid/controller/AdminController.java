package com.jusvid.JusVid.controller;

import com.jusvid.JusVid.repository.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping("admin")
public class AdminController {
    @Autowired
    PersonRepository personRepository;
}

