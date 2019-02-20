package com.mwsgateway.apigateway;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class ProductGateway {
    @RequestMapping("/api")
    public String index() {
        return "API SERVICE IS ABOUT TO START";
    }

}
