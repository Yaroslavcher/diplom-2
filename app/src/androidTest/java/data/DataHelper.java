package data;

public class DataHelper {

    public static class AuthInfo {
        private final String login;
        private final String password;

        public AuthInfo(String login, String password) {
            this.login = login;
            this.password = password;
        }

/*        public static AuthInfo getAuthInfo() {
            return new AuthInfo("login2", "password2");
        }*/

        public String getLogin() {
            return "login2";
        }

        public String getPassword() {
            return "password2";
        }
    }
}
