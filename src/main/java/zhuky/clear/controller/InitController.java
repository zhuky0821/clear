package zhuky.clear.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zhuky.clear.service.InitService;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

@RestController
@RequestMapping("/init")
public class InitController {
    Logger logger = LoggerFactory.getLogger(InitController.class);

    @Autowired
    private InitService initService;

    @GetMapping("/schema")
    public void SchemaInitial() throws Exception {
        initService.initSchema();
    }


}
