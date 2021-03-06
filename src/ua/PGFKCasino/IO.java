package ua.PGFKCasino;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class IO {
    public String handleInput() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            return reader.readLine();
        }
        catch (Exception e) { }
        return "";
    }

    public static void clearConsole() {
        try {
            if (System.getProperty("os.name").contains("Windows"))
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        }
        catch (Exception ignored) { }
    }
}
