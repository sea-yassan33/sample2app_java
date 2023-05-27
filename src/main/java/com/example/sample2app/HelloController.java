package com.example.sample2app;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloController {

  @RequestMapping("/")
  public ModelAndView index(ModelAndView mav){
    mav.setViewName("index");
    mav.addObject("title", "Hello page");
    mav.addObject("msg","this is JPA sample data");
    return mav;
  }
}
