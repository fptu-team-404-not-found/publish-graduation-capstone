package team404.listener;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ServletListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        context.log("Deploying.....");
        String siteMapLocation = context.getInitParameter("SITE_MAP_LOCATION");
        InputStream is = null;
        if (siteMapLocation != null) {
            Properties properties = new Properties();
            is = context.getResourceAsStream(siteMapLocation);
            try {
                properties.load(is);
                context.setAttribute("SITE_MAP", properties);
            } catch (IOException ex) {
                context.log("ServletListener_IO: " + ex.getMessage());
            }
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        context.log("Destroying.....");
    }
}
