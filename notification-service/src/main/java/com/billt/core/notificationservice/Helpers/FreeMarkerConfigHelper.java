package com.billt.core.notificationservice.Helpers;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ui.freemarker.FreeMarkerConfigurationFactoryBean;

@Configuration
public class FreeMarkerConfigHelper {
    @Bean(name="freemarkerhelper")
    //public FreeMarkerConfigurationFactoryBean getFreeMarkerConfiguration() {
    public freemarker.template.Configuration getFreeMarkerConfiguration() {
        /*FreeMarkerConfigurationFactoryBean bean = new FreeMarkerConfigurationFactoryBean();
        bean.setTemplateLoaderPath("/templates/");
        return bean;*/
        freemarker.template.Configuration config = new freemarker.template.Configuration();
        config.setClassForTemplateLoading(this.getClass(), "/templates/");
        return config;
    }
}

