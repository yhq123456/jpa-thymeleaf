package com.neo.web;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.neo.filter.TokenProvider;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
@Api(value = "HelloController", description = "用户登录登出接口")
@RestController
public class HelloController {
	
	private final TokenProvider tokenProvider;
	
	public HelloController(TokenProvider tokenProvider){
		this.tokenProvider = tokenProvider;
	}

	@RequestMapping("/hello")
    public String hello(Model model, @RequestParam(value="name", required=false, defaultValue="World") String name) {
        model.addAttribute("name", name);
        return "hello";
    }
    @RequestMapping("/hello1")
    public String hello1(Model model, @RequestParam(value="name", required=false, defaultValue="World") String name) {
        model.addAttribute("name", name);
        return "hello";
    }
    @ApiOperation(value="用户登录", notes="用户登录接口")
    @ApiImplicitParams({
          @ApiImplicitParam(name = "email", value = "用户名", required = true ,dataType = "string"),
          @ApiImplicitParam(name = "pass", value = "密码", required = true ,dataType = "string")
    })
    @GetMapping("/login")
    public ResponseEntity<Object> hello(String email, String pass,HttpServletResponse response) {
    	String jwt = tokenProvider.createToken(email, false);
    	System.out.println(jwt);
    	response.addHeader("Authorization", "Bearer " + jwt);
    	JSONObject jo = new JSONObject();
    	jo.put("msg", "0");
        return ResponseEntity.ok().body(jo);
    }
}
