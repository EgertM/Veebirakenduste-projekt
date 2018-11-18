package com.veebirakendus.Attempt1.services;

import com.veebirakendus.Attempt1.repositories.StatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StatService {
    @Autowired
    StatRepository statRepository;

    public List<Object> getTopBrowsers() {
        return statRepository.getDistinctTopByBrowser();
    }

    public List<Object> getTopOSs() {
        return statRepository.getDistinctTopByOs();
    }

    public List<Integer> getTrafficByHour() {
        List<Integer> trafficList = new ArrayList<>();
        for (int i = 0; i < 24; i++) {
            trafficList.add(statRepository.getRequestsByHour(i));
        }
        return trafficList;
    }
}
