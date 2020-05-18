package config;

import java.util.Locale;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.util.UrlPathHelper;

import interceptor.ProcessingTimeLogInterceptor;

@EnableWebMvc
@Configuration
@ComponentScan(basePackages = { "controller", "service", "dao" })
public class JavaBasedConfiguration extends WebMvcConfigurerAdapter {
	@Bean
	public InternalResourceViewResolver internalResourceViewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/jsp/");
		resolver.setSuffix(".jsp");
		resolver.setViewClass(JstlView.class);
		return resolver;
	}

	// @MatrixVariable açma
	public void configurePathMatch(PathMatchConfigurer configurer) {
		UrlPathHelper urlPathHelper = new UrlPathHelper();
		urlPathHelper.setRemoveSemicolonContent(false);
		configurer.setUrlPathHelper(urlPathHelper);
	}

	public void addResourceHandlers(final ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
		registry.addResourceHandler("/img/**").addResourceLocations("/resources/images/");
	}

	@Autowired
	private RequestMappingHandlerAdapter requestMappingHandlerAdapter;

	@PostConstruct
	public void init() {
		requestMappingHandlerAdapter.setIgnoreDefaultModelOnRedirect(true);
	}
	
	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource=new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("/WEB-INF/bundles/messages");
		return messageSource;
	}
	
	
	//loglama
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new ProcessingTimeLogInterceptor());
		LocaleChangeInterceptor localeChangeInterceptor=new LocaleChangeInterceptor();
		localeChangeInterceptor.setParamName("language");
		registry.addInterceptor(localeChangeInterceptor);
	}

	@Bean
	public LocaleResolver localeResolver() {
		SessionLocaleResolver resolver=new SessionLocaleResolver();
		resolver.setDefaultLocale(new Locale("en"));
		return resolver;
	}
	
	@Bean
	public LocalValidatorFactoryBean validator() {
		LocalValidatorFactoryBean bean=new LocalValidatorFactoryBean();
		bean.setValidationMessageSource(messageSource());
		return bean;
	}
	
	@Override
	public Validator getValidator() {
		return validator();
	}

}
