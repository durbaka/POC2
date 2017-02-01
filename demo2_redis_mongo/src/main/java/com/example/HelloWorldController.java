package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
	
	@Autowired
    private ApplicationContext applicationContext;

    @RequestMapping("/hello")
    public String hello(@RequestParam(value="key", required=false, defaultValue="World") String name, Model model) {

        String[] beanNames = applicationContext.getBeanDefinitionNames();

        for (String beanName : beanNames) {

            System.out.println(beanName + " : " + applicationContext.getBean(beanName).getClass().toString());
        }

        model.addAttribute("name", name);

        return "helloworld";
    }

}
