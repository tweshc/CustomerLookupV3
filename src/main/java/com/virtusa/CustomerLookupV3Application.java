package com.virtusa;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.WebApplicationContext;

/*
 * Logging Aspect
 * Input Validation
 * Unit Testing
 */

@SpringBootApplication
public class CustomerLookupV3Application implements CommandLineRunner{

  @Autowired
  private ApplicationContext appContext;
  
  @Autowired
  private WebApplicationContext wc;
  
	public static void main(String[] args) {
		SpringApplication.run(CustomerLookupV3Application.class, args);
	}

  @Override
  public void run(String... args) throws Exception {
    String[] beans = appContext.getBeanDefinitionNames();
    String[] webBeans = wc.getBeanDefinitionNames();
    
    Arrays.sort(beans);
    for (String bean : beans) {
        System.out.println(bean);
    }
    System.out.println("===============================");
    Arrays.sort(webBeans);
    for (String bean : webBeans) {
        System.out.println(bean);
    }
    
  }
  
  public WebApplicationContext getWebApplicationContext() {
    return this.wc;
  }
}
