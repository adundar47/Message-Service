package com.adundar.messageservice.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.adundar.messageservice.model.Result;
import com.adundar.messageservice.model.User;

@FeignClient(name = "user-service", fallback = UserClientFallback.class)
public interface UserClient {

    @RequestMapping(method = RequestMethod.GET, value = "/users/{name}")
    Result<User> retrieveUserByName(@RequestBody String userName);
}
