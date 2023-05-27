package com.example.sample2app;

import com.example.sample2app.repositories.PersonRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

import com.example.sample2app.repositories.PersonRepository;

import jakarta.transaction.Transactional;

@Controller
public class HelloController {

  @Autowired
  PersonRepository repository;

  @RequestMapping("/")
  public ModelAndView index(
          @ModelAttribute("formModel") Person Person,
          ModelAndView mav){
    mav.setViewName("index");
    mav.addObject("title", "Hello page");
    mav.addObject("msg","this is JPA sample data");
    Iterable<Person> list = repository.findAll();
    mav.addObject("data", list);
    return mav;
  }

  @RequestMapping(value = "/", method = RequestMethod.POST)
  @Transactional
  public ModelAndView form(
          @ModelAttribute("formModel") Person Person,
          ModelAndView mav) {
    repository.saveAndFlush(Person);
    return new ModelAndView("redirect:/");
  }
}
