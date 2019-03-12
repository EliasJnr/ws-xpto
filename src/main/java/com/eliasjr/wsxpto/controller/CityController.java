package com.eliasjr.wsxpto.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;

@Api(value = "City controller", description = "Controller de cidades")
@RestController
@RequestMapping(value = "/city")
public class CityController {

}
