package ua.PGFKCasino.handlers;


import ua.PGFKCasino.slots.SlotMachine;
import ua.PGFKCasino.dice.Dice;
import ua.PGFKCasino.lottery.Lottery;
import ua.PGFKCasino.menu.Menu;
import ua.PGFKCasino.profile.Profile;
import ua.PGFKCasino.roulette.Roulette;

import java.util.List;

import static org.fusesource.jansi.Ansi.Color.RED;
import static org.fusesource.jansi.Ansi.ansi;
import static ua.PGFKCasino.handlers.IOHandler.handleInput;

public class MenuHandler extends Menu {

    public static void handleMain() {
        System.out.print(ansi().fg(RED).a("Введiть команду: ").reset());
        switch (handleInput().trim()) {
            case "1":
                printGames();
                handleGames();
                break;
            case "2":
                printProfile();
                handleProfile();
                break;
            case "3":
                printAuthors();
                handleAuthors();
                break;
            case "4":
                System.exit(0);
                break;
            default:
                System.out.println("Незрозумiла команда. Введiть ще раз");
                System.out.print(ansi().fg(RED).a("Введiть команду: ").reset());
                handleMain();
        }
    }

    public static void handleGames() {
        System.out.print(ansi().fg(RED).a("Введiть команду: ").reset());
        switch (handleInput().trim()) {
            case "0":
                printMain();
                handleMain();
                break;
            case "1":
                new Roulette(new Profile(IOHandler.readFile("defaultProfile")));
                printMain();
                handleMain();
                break;
            case "2":
                new SlotMachine(new Profile(IOHandler.readFile("defaultProfile")));
                printMain();
                handleMain();
                break;
            case "3":
                new Dice(new Profile(IOHandler.readFile("defaultProfile")));
                printMain();
                handleMain();
                break;
            case "4":
                new Lottery(new Profile(IOHandler.readFile("defaultProfile")));
                printMain();
                handleMain();
                break;
            default:
                System.out.println("Незрозумiла команда. Введiть ще раз");
                System.out.print(ansi().fg(RED).a("Введiть команду: ").reset());
                handleGames();
        }
    }

    public static void handleProfile() {
        switch (handleInput().trim()) {
            case "0":
                printMain();
                handleMain();
                break;
            case "1":
                printProfileList();
                handleProfileList();
                break;
            case "2":
                handleProfileCreation();
                break;
        }
    }

        public static void handleProfileList() {
        try {
            Profile profileInstance = null;
            boolean found = false;
            String input = IOHandler.handleInput().trim();
            List<String> profiles = IOHandler.getFiles("profiles");
            if (input.equals("0")) {
                Menu.printMain();
                handleMain();
                return;
            }

            for (String profile : profiles) {
                if (input.equals(profile.substring(0, input.length()))) {
                    found = true;
                    profileInstance = new Profile(profile.replace(input + ". ", ""));
                }
            }
            if (!found) {
                System.out.println("Такого профiля нема. Виберiть iнший профiль");
                handleProfileList();
            }
            System.out.println("Вибраний вами профiль: ");
            profileInstance.getProfile();
            System.out.println("Що бажаєте зробити з даним профiлем?");
            System.out.println("0. Повернутися в головне меню");
            System.out.println("1. Назначити стандартним");
            System.out.println("2. Видалити профiль");
            handleProfileSelection(profileInstance);
        }
        catch (Exception ignored) { }
    }

    public static void handleProfileCreation() {
        System.out.print("Введiть iм'я гравця: ");
        String name = handleInput().trim();
        IOHandler.writeJSON("profiles/" + name + ".json", name, 250);
        Menu.printMain();
        handleMain();
    }

    public static void handleProfileSelection(Profile profile) {
        System.out.print(ansi().fg(RED).a("Введiть команду: ").reset());
        switch (handleInput().trim()) {
            case "0":
                Menu.printMain();
                handleMain();
                break;
            case "1":
                profile.setDefaultProfile();
                printMain();
                handleMain();
                break;
            case "2":
                IOHandler.deleteFile("profiles/" + profile.getName() + ".json");
                printMain();
                handleMain();
                break;
            default:
                handleProfileSelection(profile);
        }
    }

    public static void handleAuthors() {
        System.out.print(ansi().fg(RED));
        System.out.println("0. Повернутися в головне меню");
        System.out.print("Введiть команду: ");
        handleInput();
        printMain();
        handleMain();
    }
}
