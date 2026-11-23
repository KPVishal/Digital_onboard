package com.bijlipay.config;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
@EnableWebMvc
@EnableSpringDataWebSupport
@Configuration
@ComponentScan("com.bijlipay")
public class WebConfig extends WebMvcConfigurerAdapter {
    public Pageable resolvePageable(Map<String, String> requestParams, Pageable pageable) {
        List<Sort.Order> orders=new ArrayList<>();
        Sort sorts = pageable.getSort();
        if(sorts !=null){
            sorts.forEach(sort ->{
                String propertyName =sort.getProperty();
                String dirKey= propertyName + ".dir";
                if (requestParams.containsKey(dirKey)){
                    String dirValue = requestParams.get(dirKey);
                    Sort.Direction direction=dirValue.equals("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
                    orders.add(new Sort.Order(direction,propertyName));
                }
            });
        }
        return orders.isEmpty() ? pageable : PageRequest.of(pageable.getPageNumber(),pageable.getPageSize(), Sort.by(orders));
    }
}

