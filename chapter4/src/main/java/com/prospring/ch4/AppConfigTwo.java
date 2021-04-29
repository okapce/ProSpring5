package com.prospring.ch4;

import com.prospring.ch2.decoupled.MessageProvider;
import com.prospring.ch2.decoupled.MessageRenderer;
import com.prospring.ch2.decoupled.StandardOutMessageRenderer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;

@Configuration
@ComponentScan(basePackages={"com.prospring.ch4.annotated"})
public class AppConfigTwo {
 @Autowired
 MessageProvider provider;
 @Bean(name = "messageRenderer")
 public MessageRenderer messageRenderer() {
 MessageRenderer renderer =
 new StandardOutMessageRenderer();
 renderer.setMessageProvider(provider);
 return renderer;
 }
}
