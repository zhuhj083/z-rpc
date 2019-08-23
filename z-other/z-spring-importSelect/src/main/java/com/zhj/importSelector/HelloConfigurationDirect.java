package com.zhj.importSelector;

import com.zhj.service.impl.HelloServiceImplA;
import com.zhj.service.impl.HelloServiceImplB;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author zhj on 2019-08-22.
 */
@Configuration
@Import({HelloServiceImplA.class, HelloServiceImplB.class})
public class HelloConfigurationDirect {

}
