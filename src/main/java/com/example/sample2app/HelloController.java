package com.example.sample2app;

import com.example.sample2app.repositories.PersonRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.beans.factory.annotation.Autowired;


@Controller
public class HelloController {

  @Autowired
  PersonRepository repository;

  @RequestMapping("/")
  public ModelAndView index(ModelAndView mav){
    mav.setViewName("index");
    mav.addObject("title", "Hello page");
    mav.addObject("msg","this is JPA sample data");
    Iterable<Person> list = repository.findAll();
    mav.addObject("data", list);
    return mav;
  }
}
