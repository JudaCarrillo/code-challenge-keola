package com.codechallenge.keola.api.infrastructure.health;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthcheckController {

    @GetMapping("/health-check")
    @ResponseBody
    public String healthCheck() {
        return "OK";
    }
}
