package com.creditcardpayment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import springfox.documentation.swagger2.annotations.EnableSwagger2;
@SpringBootApplication
@EnableSwagger2
public class CreditcardBillPaymentApplication {
	public static void main(String[] args) {
		SpringApplication.run(CreditcardBillPaymentApplication.class, args);
		System.out.println("Connected Database");
}

}   
  //http://localhost:9090/swagger-ui/index.html