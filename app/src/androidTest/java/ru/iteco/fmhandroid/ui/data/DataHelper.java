package ru.iteco.fmhandroid.ui.data;

import java.util.Random;

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

    public static String generateString() {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();


        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        return generatedString;
    }
}
