package com.SpringPhone.Cellphone.profile;

import com.SpringPhone.Cellphone.service.DBservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("test")
public class TestProfile {

    @Autowired
    private DBservice dbService;

    @Bean
    public void instanciaDb(){
        this.dbService.instanciaDb();
    }

}
