package com.example.ShopAPI.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.util.MultiValueMap;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;

import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

@Configuration
public class PgAdminSyncProxyConfig implements WebMvcConfigurer {

    @Value("${pgadmin.host:localhost}")
    private String pgAdminHost;

    @Value("${pgadmin.port:5432}")
    private int pgAdminPort;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/admin/**").setViewName("forward:/proxy");
    }


    @RestController
    @RequestMapping("/proxy")
    public static class ProxyController{
        private RestTemplate restTemplate;
        private String pgAdminHost;
        private int pgAdminPort;

        public ProxyController(RestTemplate restTemplate,  @Value("${pgadmin.host:localhost}") String pgAdminHost,  @Value("${pgadmin.port:5432}") int pgAdminPort){
            this.restTemplate = restTemplate;
            this.pgAdminHost = pgAdminHost;
            this.pgAdminPort = pgAdminPort;
        }
        @GetMapping("/**")
        public ResponseEntity<byte[]> proxyGetRequest(HttpServletRequest request) {
            return proxyRequest(request, HttpMethod.GET);
        }
        @PostMapping("/**")
        public ResponseEntity<byte[]> proxyPostRequest(HttpServletRequest request) {
            return proxyRequest(request, HttpMethod.POST);
        }

        private ResponseEntity<byte[]> proxyRequest(HttpServletRequest request, HttpMethod method){
            String path = request.getRequestURI().replaceFirst("/proxy", "");

            HttpHeaders headers = new HttpHeaders();
            Enumeration<String> headerNames = request.getHeaderNames();
            while (headerNames.hasMoreElements()) {
                String headerName = headerNames.nextElement();
                headers.addAll(headerName,  java.util.Collections.list(request.getHeaders(headerName)));
            }

            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            Enumeration<String> paramNames = request.getParameterNames();
            while (paramNames.hasMoreElements()) {
                String paramName = paramNames.nextElement();
                String[] parameterValues = request.getParameterValues(paramName);
                if (parameterValues != null) {
                    params.addAll(paramName, Arrays.asList(parameterValues));
                }

            }
            HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(params, headers);
            try {
                ResponseEntity<byte[]> response = restTemplate.exchange("http://" + pgAdminHost + ":" + pgAdminPort + path,
                        method, httpEntity, byte[].class);
                return response;
            } catch(Exception ex){
                return ResponseEntity.status(500).body(("Error during proxy request: " + ex.getMessage()).getBytes());
            }
        }
    }

}
