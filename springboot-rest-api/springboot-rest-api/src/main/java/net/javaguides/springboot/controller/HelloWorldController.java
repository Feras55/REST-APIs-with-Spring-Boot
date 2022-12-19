package net.javaguides.springboot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//@Controller  //to make the controller class as Spring MVC controller
//@ResponseBody //to convert a response from java object to JSON
@RestController //This annotation consist of both annotations above and can be used instead of them
public class HelloWorldController {

    //to create a REST API -> 1. Create a method 2. use spring annotation to make that method a REST API
    //HTTP GET Request
    @GetMapping("/hello-world")
    public String helloWorld(){
        return "Hello World";
    }

}
