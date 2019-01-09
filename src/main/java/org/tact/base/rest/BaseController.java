package org.tact.base.rest;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/base")
public class BaseController {
	
	/**
	 * 
	 * @return
	 * 
	 * Possible urls:
	 * 		http://localhost:1878/base/
	 */
    @GetMapping(value = "")
    public <T> T testBase() {
        
    	Map<String, Object> map = new LinkedHashMap<String, Object>();
        map.put("one", "two");
        map.put("three", "four");
        map.put("five", "six");
        map.put("seven", "eight");
        
        return (T) map;
    }
    
    /**
     * 
     * @return
     * 
     * Possible urls:
	 * 		http://localhost:1878/base/one/two
	 * 
     */
    @GetMapping(value = "/one/two")
    public <T> T testOne() {
        
    	Map<String, Object> map = new LinkedHashMap<String, Object>();
        map.put("one", "two");
        map.put("three", "four");
        
        return (T) map;
    }
    
    /**
     * 
     * @param apikey
     * @param request
     * @param response
     * @return
     * 
     * Possible urls:
	 * 		http://localhost:1878/base/one/three?apikey=one
     */
    @GetMapping(value = "/one/three")
    public <T> T testTwo(
		@RequestParam(value = "apikey") String apikey,
		final HttpServletRequest request, 
		final HttpServletResponse response
    ) {
        
    	Map<String, Object> map = new LinkedHashMap<String, Object>();
        map.put("apikey", apikey);
        map.put("three", "four");
        
        return (T) map;
    }
}
