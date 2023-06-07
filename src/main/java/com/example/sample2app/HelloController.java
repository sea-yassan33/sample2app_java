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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import java.util.List;
import java.util.Optional;

import com.example.sample2app.repositories.PersonRepository;

import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class HelloController {

  @Autowired
  PersonRepository repository;

  @Autowired
  PersonDAOPersonImpl dao;

  @RequestMapping("/")
  public ModelAndView index(
          @ModelAttribute("formModel") Person Person,
          ModelAndView mav){
    mav.setViewName("index");
    mav.addObject("title", "Hello page");
    mav.addObject("msg","this is JPA sample data");
    List<Person> list = dao.getAll();
    mav.addObject("data", list);
    return mav;
  }

  @RequestMapping(value = "/", method = RequestMethod.POST)
  @Transactional
  public ModelAndView form(
          @ModelAttribute("formModel") @Validated Person person,
          BindingResult result,
          ModelAndView mav) {
    ModelAndView res = null;
    System.out.println(result.getFieldErrors());
    if (!result.hasErrors()){
      repository.saveAndFlush(person);
      res = new ModelAndView("redirect:/");
    }else{
      mav.setViewName("index");
      mav.addObject("title","Hello page");
      mav.addObject("msg","sorry, error is occurred...");
      Iterable<Person> list = repository.findAll();
      mav.addObject("datalist", list);
      res = mav;
    }
    return res;
  }

  @PostConstruct
  public void init(){
//    data1
    Person p1 = new Person();
    p1.setName("taro");
    p1.setAge(39);
    p1.setMail("taro@tech.com");
    repository.saveAndFlush(p1);
    //    data2
    Person p2 = new Person();
    p2.setName("hanako");
    p2.setAge(28);
    p2.setMail("hanako@tech.com");
    repository.saveAndFlush(p2);
    //    data3
    Person p3 = new Person();
    p3.setName("sachiko");
    p3.setAge(22);
    p3.setMail("sachico@tech.com");
    repository.saveAndFlush(p3);
  }

  @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
  public ModelAndView edit(@ModelAttribute Person Person,
                           @PathVariable int id, ModelAndView mav){
    mav.setViewName("edit");
    mav.addObject("title", "edit Person");
    Optional<Person> data = repository.findById((long)id);
    mav.addObject("formModel", data.get());
    return mav;
  }

  @RequestMapping(value = "/edit", method = RequestMethod.POST)
  @Transactional
  public ModelAndView update(@ModelAttribute Person Person,
                             ModelAndView mav){
    repository.saveAndFlush(Person);
    return new ModelAndView("redirect:/");
  }

  @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
  public ModelAndView delete(@PathVariable int id,
                              ModelAndView mav){
    mav.setViewName("delete");
    mav.addObject("title", "Delete Person");
    mav.addObject("msg", "Can I delete this record?");
    Optional<Person> data = repository.findById((long)id);
    mav.addObject("formModel", data.get());
    return mav;
  }

  @RequestMapping(value = "/delete", method = RequestMethod.POST)
  @Transactional
  public ModelAndView remove(@RequestParam long id,
                             ModelAndView mav){
    repository.deleteById(id);
    return new ModelAndView("redirect:/");
  }

  @RequestMapping(value = "/find", method = RequestMethod.GET)
  public ModelAndView index(ModelAndView mav){
    mav.setViewName("find");
    mav.addObject("msg", "Personのサンプルです");
    Iterable<Person> list = dao.getAll();
    mav.addObject("data", list);
    return mav;
  }

  @RequestMapping(value = "/find", method = RequestMethod.POST)
  public ModelAndView search(HttpServletRequest request,
                             ModelAndView mav){
    mav.setViewName("find");
    String param = request.getParameter("find_str");
    if (param == ""){
      mav = new ModelAndView("redirect:/find");
    }else{
      String[] params = param.split(",");
      mav.addObject("title","Find result");
      mav.addObject("msg","「"+ param + "」の検索結果");
      mav.addObject("value", param);
      List<Person> list = repository.findByAge(
              Integer.parseInt(params[0]),
              Integer.parseInt(params[1]));
      mav.addObject("data", list);
    }
    return  mav;
  }
}
