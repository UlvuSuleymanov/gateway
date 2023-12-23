package com.tourist.gateway.controller;

import com.tourist.gateway.dto.Route;
import com.tourist.gateway.properties.RouteList;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.web.reactive.result.view.CsrfRequestDataValueProcessor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.reactive.result.view.RequestDataValueProcessor;

import java.util.Arrays;
import java.util.List;

@Controller
@AllArgsConstructor
public class SwaggerController {

    private final RouteList routeList;
    @GetMapping("swagger")
    String getSwagger(Model model ){

          List<Route> routeList1 = Arrays.asList(
                new Route("Auth",routeList.getAuthUrl()+"/swagger-ui/index.html"),
                new Route("Restaurant",routeList.getRestaurantUrl()+"/swagger-ui/index.html"),
                new Route("Rent a car",routeList.getCarUrl()+"/swagger-ui/index.html")
        );
        model.addAttribute("url", routeList1);
        return "swagger";
    }


}
