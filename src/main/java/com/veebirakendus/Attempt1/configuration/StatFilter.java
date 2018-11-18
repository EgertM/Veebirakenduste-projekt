package com.veebirakendus.Attempt1.configuration;

import com.blueconic.browscap.Capabilities;
import com.blueconic.browscap.ParseException;
import com.veebirakendus.Attempt1.entity.RequestData;
import com.veebirakendus.Attempt1.repositories.StatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;
import org.springframework.web.util.WebUtils;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.time.LocalTime;

import com.blueconic.browscap.UserAgentParser;
import com.blueconic.browscap.UserAgentService;

@Component
public class StatFilter implements Filter {
    final UserAgentParser parser = new UserAgentService().loadParser();


    @Autowired
    StatRepository statisticRepository;

    public StatFilter() throws IOException, ParseException {
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        chain.doFilter(request, response);
        HttpServletRequest servletRequest = (HttpServletRequest) request;
        try {
            String sessionId = WebUtils.getCookie(servletRequest, "JSESSIONID").getValue();

            if (statisticRepository.findBySessionId(sessionId) == null) {
                String userAgent = servletRequest.getHeader("User-Agent");
                System.out.println(userAgent);
                final Capabilities capabilities = parser.parse(userAgent);

                //final String browser = capabilities.getBrowser();
                //final String browserType = capabilities.getBrowserType();
                //final String browserMajorVersion = capabilities.getBrowserMajorVersion();
                //final String deviceType = capabilities.getDeviceType();

                //final String platform = capabilities.getPlatform();
                //final String platformVersion = capabilities.getPlatformVersion();



                String browserName = capabilities.getBrowser();
                System.out.println(browserName);
                String osName = capabilities.getPlatform();
                System.out.println(osName);
                LocalTime time = LocalTime.now();
                System.out.println(time);

                try {
                    statisticRepository.save(new RequestData(sessionId, browserName, osName, time));
                } catch (DataIntegrityViolationException e) {
                }
            }
        } catch (NullPointerException e) {

        }

    }

    @Override
    public void destroy() {

    }
}