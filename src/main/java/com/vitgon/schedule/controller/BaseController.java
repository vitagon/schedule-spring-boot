package com.vitgon.schedule.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/{locale:en|ru}")
public class BaseController {

}
