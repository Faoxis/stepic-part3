package servlets;

import accounts.AccountService;
import accounts.UserProfile;
import dbService.DBService;
import dbService.dataSets.UsersDataSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by sergei on 8/17/16.
 */
public class SignInServlet extends HttpServlet {
    private final AccountService accountService;
    DBService dbService = new DBService();

    public SignInServlet (AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        response.setContentType("text/html;  charset=utf-8");

        if (login == null || password == null) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }


        UsersDataSet user = dbService.getUser(login);

        if (user == null || !user.getPassword().equals(password)) {
            response.getWriter().println("Unauthorized");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        response.getWriter().println("Authorized: " + login);
        response.setStatus(HttpServletResponse.SC_OK);
    }
}