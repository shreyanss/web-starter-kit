package com.sa.web;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;

import javax.ws.rs.Produces;

import com.sa.web.dto.SentenceDto;
import com.sa.web.dto.SentimentDto;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;

import com.fasterxml.jackson.core.JsonParseException;
import com.sa.web.dto.MachineDetails;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jackson.JsonObjectDeserializer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@CrossOrigin(origins = "*")
@RestController
public class SentimentController {

    @Value("${sa.logic.api.url}")
    private String saLogicApiUrl;

    @PostMapping("/sentiment")
    public SentimentDto sentimentAnalysis(@RequestBody SentenceDto sentenceDto) {
        RestTemplate restTemplate = new RestTemplate();

        return restTemplate.postForEntity(saLogicApiUrl + "/analyse/sentiment",
                sentenceDto, SentimentDto.class)
                .getBody();
    }
    
    @GetMapping("/hostDetailsPython")
    public String getPythonHostDetails(){
        RestTemplate restTemplate = new RestTemplate();
      final String response= restTemplate.getForObject("http://10.0.17.196/", String.class);
      return response;
    }
    @GetMapping("/hostDetailsPython1")
    public String getPythonHostDetails2(){
        RestTemplate restTemplate = new RestTemplate();
      final String response= restTemplate.getForObject(saLogicApiUrl, String.class);
      return response;
    }
    
    @GetMapping("/hostDetailsPythonWithPort")
    public String getPythonHostDetails1(){
        RestTemplate restTemplate = new RestTemplate();
      final String response= restTemplate.getForObject("http://10.0.17.196:5000/", String.class);
      return response;
    }
    
    @RequestMapping(value = "/hostdetailsSpringApp", method = RequestMethod.GET, produces = "application/json")
    public String getHostDetails() throws ParseException {
    	InetAddress hostdetails;
        String hostname;
        String hostip;
        JSONArray jarray = new JSONArray();
        JSONObject pythonobj = new JSONObject();
        JSONObject springobj = new JSONObject(); 
       
        try {
        	 RestTemplate restTemplate = new RestTemplate();
             final String response= restTemplate.getForObject(saLogicApiUrl + "/", String.class);
             JSONParser parser = new JSONParser();
             JSONObject jsonobj = (JSONObject)parser.parse(response);
             if(response != null)
             {
            	pythonobj.put("applicationName", jsonobj.get("applicationName")) ;
            	pythonobj.put("hostIP", jsonobj.get("hostIp"));
            	pythonobj.put("hostName", jsonobj.get("hostName"));
            	pythonobj.put("serviceType", jsonobj.get("serviceType"));
            	 
            	 
            	 
            }
       
             
        	MachineDetails md = new MachineDetails();
        	
            hostdetails = InetAddress.getLocalHost();
            hostname = hostdetails.getHostName();
            hostip = hostdetails.getHostAddress();
            
            md.setHostIP(hostip);
            md.setHostName(hostname);
            System.out.println("Your current IP address : " + hostip);
            System.out.println("Your current Hostname : " + hostname);
        
            springobj.put("applicationName","Spring");
            springobj.put("serviceType", "clusterIP");
            springobj.put("hostName", hostname);
            springobj.put("hostIP", hostip);
           jarray.add(pythonobj);
           jarray.add(springobj);

         return jarray.toJSONString();
 
        } catch (UnknownHostException e) {
 
            e.printStackTrace();
            return null;
        }
        
        
    }

    @GetMapping("/testHealth")
    public String  testHealth() {
 
return "Hello";
   }
    
    @GetMapping("/testHealth1")
    public String testHealth1() {
    	return saLogicApiUrl;
    	
    }
    
    
}


