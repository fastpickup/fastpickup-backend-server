package com.project.fastpickup.admin.main;

/*
 * Date   : 2023.07.26
 * Author : 조상희
 * E-mail : jo_sh_1028@naver.com
 */

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Log4j2
@RequestMapping("/admin/")
public class MainController {

  //Main Page
  @GetMapping("index")
  public void mainPage(){
    log.info("Main Page....................");
  }
}
