package com.billt.core.merchantpanel.Helper;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Qualifier;


@Configuration
public class FreeMarkerHelper {
    @Bean(name="merchantfreemarkerhelper")
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

