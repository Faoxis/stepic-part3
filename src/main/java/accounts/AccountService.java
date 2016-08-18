package accounts;


import dbService.DBException;
import dbService.DBService;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by sergei on 8/17/16.
 */
public class AccountService {

    DBService dbService = new DBService();

    private static AccountService accountService;

    private final Map<String, UserProfile> loginToProfile;
    private final Map<String, UserProfile> sessionIdToProfile;

    public AccountService() {
        loginToProfile = new HashMap<>();
        sessionIdToProfile = new HashMap<>();
    }

    public void addNewUser(UserProfile userProfile) throws DBException {
        long userId = dbService.addUser(userProfile.getLogin(), userProfile.getPass());
        loginToProfile.put(userProfile.getLogin(), userProfile);
    }

    public UserProfile getUserByLogin(String login) {
        return loginToProfile.get(login);
    }

    public UserProfile getUserBySessionId(String sessionId) {
        return sessionIdToProfile.get(sessionId);
    }

    public void addSession(String sessionId, UserProfile userProfile) {
        sessionIdToProfile.put(sessionId, userProfile);
    }

    public void deleteSession(String sessionId) {
        sessionIdToProfile.remove(sessionId);
    }

}
