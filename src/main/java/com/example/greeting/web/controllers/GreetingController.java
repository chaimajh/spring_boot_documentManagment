package com.example.greeting.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.greeting.web.models.User;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class GreetingController {

@GetMapping({"/","/home"})
public String getHomePage() {
    return "home";
}


  @RequestMapping("/greeting")
  //  public String greeting(@RequestParam(value="name",required = false,defaultValue = "World") String name,Model model){
    public String greeting(@RequestParam String name,@RequestParam int age,Model model){
       
        String [] names=new String[]{"demo1","demo2","demo3"};
       //model.addAttribute("name","World");
       model.addAttribute("names", names);
       model.addAttribute("user", new User("Demo","admin"));
       model.addAttribute("name",name);
       model.addAttribute("age", age);
        return "greeting";
    }
   /* @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String missingServletRequestParameterExceptionHandler(MissingServletRequestParameterException e){
        return "Error:"+e.getMessage();
    }*/
  
    /*@RequestMapping("/greeting")
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String greeting(@RequestParam(value="name",required = false,defaultValue = "World") String name,Model model){

       //model.addAttribute("name","World");
       model.addAttribute("name",name);
        return "<h1>Hello , "+name+" from java<h1>";
    }*/
    
}