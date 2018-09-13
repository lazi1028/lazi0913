package com.bcht.axletempmonitor.controller;

import com.bcht.axletempmonitor.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test")
public class TymeleafTestController {

    @Autowired
    private IUserService userServiceImpl;

  /* @RequestMapping(value = "/getuser2",method = RequestMethod.GET)
    public String getUserByID1(Map<String,Object> map,@RequestParam("id") Integer id){
      User1 u= userServiceImpl.selectUserByID(id);
      map.put("user1",u);
      return "index";

   }

    @RequestMapping(value = "/getuser1",method = RequestMethod.GET)
    public String getUserByID(Model model,@RequestParam("id") Integer id){
        User1 u= userServiceImpl.selectUserByID(id);
        model.addAttribute("user1",u);
        return "index";
    }*/
}
