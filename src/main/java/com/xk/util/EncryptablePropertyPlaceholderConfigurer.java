package com.xk.util;

import java.util.Properties;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanInitializationException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

public class EncryptablePropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer {  
	
	
	
//    protected void processProperties(ConfigurableListableBeanFactory beanFactory, Properties props)  
//        throws BeansException {  
//            try {  
//                
//                String username = props.getProperty("encrypted_username");  
//                if (username != null) {  
//                    props.setProperty("encrypted_username", new String(EncUtil.decryptBASE64(username)));  
//                }  
//                  
//                String password = props.getProperty("encrypted_password");  
//                if (password != null) {  
//                    props.setProperty("encrypted_password", new String(EncUtil.decryptBASE64(password)));  
//                }  
//                  
//                String url = props.getProperty("encrypted_url");  
//                if (url != null) {  
//                    props.setProperty("encrypted_url", new String(EncUtil.decryptBASE64(url)));  
//                }  
//               
//                  
//                super.processProperties(beanFactory, props);  
//            } catch (Exception e) {  
//                e.printStackTrace();  
//                throw new BeanInitializationException(e.getMessage());  
//            }  
//        }
 //方法2
	@Override
	protected String convertProperty(String propertyName, String propertyValue) {
		if(propertyName.startsWith("encrypted")) {
			try {
				return new String(EncUtil.decryptBASE64(propertyValue));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		return propertyValue;
	}

}