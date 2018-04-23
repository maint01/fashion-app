package com.demo.fashion.web.rest;

import com.codahale.metrics.annotation.Timed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collections;

@Controller
@RequestMapping(value = "/")
public class RedirectController {
    private Logger log = LoggerFactory.getLogger(RedirectController.class);

    @RequestMapping(value = "admin")
    @Timed
    public ModelAndView redirectAdminPage() {
        log.debug("Request to get admin page");
        return new ModelAndView("admin", "vm", Collections.emptyMap());
    }


    @RequestMapping(value = "site")
    @Timed
    public ModelAndView redirectSite() {
        log.debug("Request to get site page");
        return new ModelAndView("site", "vm", Collections.emptyMap());
    }
}
