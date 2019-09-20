package com.dmz.dmzServer.control;

import com.dmz.dmzServer.bean.ConfigBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/outToInner")
public class InternetToInstinetControl {

    @RequestMapping("/in")
    public String InnerCallOutControl(){
        return "hello THIS is v2 world, this is people info V2 of ";

    }
}
