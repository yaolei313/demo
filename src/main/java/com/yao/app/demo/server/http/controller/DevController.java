package com.yao.app.demo.server.http.controller;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.AbstractEnvironment;
import org.springframework.core.env.EnumerablePropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DevController {

    private Environment environment;

    @Autowired
    public DevController(Environment environment) {
        this.environment = environment;
    }

    @RequestMapping(path = "/status", method = RequestMethod.GET)
    public Map<String, Object> status() {
        Map<String, Object> map = new HashMap<>();
        map.put("activeProfiles", environment.getActiveProfiles());
        map.put("defaultProfiles", environment.getDefaultProfiles());

        MutablePropertySources sources = ((AbstractEnvironment) environment).getPropertySources();
        for (PropertySource<?> source : sources) {
            if (source instanceof EnumerablePropertySource) {
                EnumerablePropertySource propertySource = (EnumerablePropertySource) source;
                Map<String, Object> tempMap = new HashMap<>();
                for (String s : propertySource.getPropertyNames()) {
                    tempMap.put(s, propertySource.getProperty(s));
                }
                map.put(source.getName(), tempMap);
            }
        }
        return map;
    }
}
