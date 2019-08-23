package com.zhj.importSelector;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author zhj on 2019-08-22.
 */
@Configuration
@Import(HelloImportSelector1.class)
public class HelloConfiguration1 {

}
