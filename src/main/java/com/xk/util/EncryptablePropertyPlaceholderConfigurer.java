package com.xk.util;

import java.util.Properties;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanInitializationException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

public class EncryptablePropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer {  
	
	
	
    protected void processProperties(ConfigurableListableBeanFactory beanFactory, Properties props)  
        throws BeansException {  
            try {  
                
                String username = props.getProperty("username");  
                if (username != null) {  
                    props.setProperty("username", new String(EncUtil.decryptBASE64(username)));  
                }  
                  
                String password = props.getProperty("password");  
                if (password != null) {  
                    props.setProperty("password", new String(EncUtil.decryptBASE64(password)));  
                }  
                  
                String url = props.getProperty("url");  
                if (url != null) {  
                    props.setProperty("url", new String(EncUtil.decryptBASE64(url)));  
                }  
               
                  
                super.processProperties(beanFactory, props);  
            } catch (Exception e) {  
                e.printStackTrace();  
                throw new BeanInitializationException(e.getMessage());  
            }  
        }

}