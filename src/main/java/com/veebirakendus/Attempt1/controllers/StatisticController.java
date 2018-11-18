package com.veebirakendus.Attempt1.controllers;

import com.veebirakendus.Attempt1.services.StatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StatisticController {

    @Autowired

    StatService statisticService;

    @GetMapping(value = "statistics/browsers", produces = "application/json")
    public List<Object> getBrowsers() {
        return statisticService.getTopBrowsers();
    }

    @GetMapping(value = "statistics/os", produces = "application/json")
    public List<Object> getOSs() {
        return statisticService.getTopOSs();
    }

    @GetMapping(value = "statistics/traffic", produces = "application/json")
    public List<Integer> getTraffic() {
        return statisticService.getTrafficByHour();
    }


}