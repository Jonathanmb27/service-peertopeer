package com.nttdata.client;


import com.nttdata.client.dao.ClientResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ClientResultClientImpl implements ClientResultClient{




    private  RestTemplate restTemplate=new RestTemplate();
    private final String clientHost;

    public ClientResultClientImpl(@Value("${productHost}") final String clientHost){

        this.clientHost=clientHost;
    }
    @Override
    public ClientResult retrieveResult() {

        return restTemplate.getForObject(clientHost+"/bootcoin",ClientResult.class);
    }


}
