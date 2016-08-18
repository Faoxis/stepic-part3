package main;

import accounts.AccountService;
import accounts.UserProfile;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.util.resource.Resource;
import servlets.SessionsServlet;
import servlets.SignInServlet;
import servlets.SignUpServlet;
import servlets.UsersServlet;

/**
 * Created by sergei on 8/17/16.
 */
public class Main {
    public static void main(String[] args) throws Exception {
        AccountService accountService = new AccountService();

//        accountService.addNewUser(new UserProfile("admin"));
 //       accountService.addNewUser(new UserProfile("test"));

        ServletContextHandler contextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);
        contextHandler.addServlet(new ServletHolder(new UsersServlet(accountService)), "/api/v1/users");
        contextHandler.addServlet(new ServletHolder(new SessionsServlet(accountService)), "/api/v1/sessions");

        contextHandler.addServlet(new ServletHolder(new SignInServlet(accountService)), "/signin");
        contextHandler.addServlet(new ServletHolder(new SignUpServlet(accountService)), "/signup");

        ResourceHandler resourceHandler = new ResourceHandler();
        resourceHandler.setResourceBase("public_html");

        HandlerList handlers = new HandlerList();
        handlers.setHandlers(new Handler[]{resourceHandler, contextHandler});

        Server server = new Server(8080);
        server.setHandler(handlers);

        server.start();
        java.util.logging.Logger.getGlobal().info("Server started");
        server.join();
    }
}
