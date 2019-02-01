package com.nikolaev.security.resetpassword;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "/auth")
public class ResetPasswordController {

    @RequestMapping(value = "/reset_password_request", method = RequestMethod.POST)
    public void createResetToken(){}

    @RequestMapping(value = "/reset_password", method = RequestMethod.POST)
    public void resetPassword(){}
}
