package com.company;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {

        taskTerminal();
        //taskReadUrlContent();
    }

    private static void taskTerminal() {
        Scanner scanner = new Scanner(System.in);
        Terminal terminal = new TerminalImpl();
        int count = 0;

        while (true) {
            System.out.println("Введите PIN-код, затем код операции (1-баланс, 2-внести, 3-снять)");
            String input = scanner.nextLine();
            if (input == null || input.isEmpty()) {
                System.out.println("завершено");
                return;
            }
            try {
                terminal.inputPin(input);
            } catch (AccountIsLockedException e) {
                System.out.println(e.getMessage());
                continue;
            } catch (InvalidPinException e) {
                System.out.println(e.getMessage());
            }
            while (true) {
                boolean inputOperation = false;
                input = scanner.nextLine();
                int operation;
                try {
                    operation = Integer.parseUnsignedInt(input);
                } catch (NumberFormatException nfEx) {
                    System.out.println("Некорректный ввод (следует ввести код операции: 1-баланс, 2-внести, 3-снять)");
                    continue;
                }
                switch (operation) {
                    case 1:
                        try {
                            int value = terminal.getBalance();
                            System.out.println("Баланс = " + value);
                        } catch (PinNotInputedException e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    case 2:
                        try {
                            count++;
                            String amount = count % 2 != 0 ? "500" : "a123!";
                            terminal.putToBalance(amount);
                            System.out.println("Пополнено на " + amount);
                        } catch (PinNotInputedException e) {
                            System.out.println(e.getMessage());
                        } catch (InvalidAmountException e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    case 3:
                        try {
                            terminal.pullFromBalance("200");
                            System.out.println("Пополнено на 200");
                        } catch (PinNotInputedException e) {
                            System.out.println(e.getMessage());
                        } catch (InvalidAmountException e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    default:
                        System.out.println("Неверная операция (введите 1, 2 или 3)");
                        inputOperation = true;
                        break;
                }
                if (!inputOperation)
                    break;
            }
        }
    }

    private static void taskReadUrlContent() {
        while (true) {
            System.out.println("Введите URL или Enter чтобы закончить");
            Scanner scanner = new Scanner(System.in);
            String url = scanner.nextLine();
            if (url == null || url.isEmpty())
                break;

            try {
                readContent(url);
            } catch (MalformedURLException urlEx) {
                System.out.println("Введен некорректный URL");
                System.out.println(urlEx.toString());
            } catch (IOException ioEx) {
                System.out.println("Не удалось получить содержимое");
                System.out.println(ioEx.toString());
            }
        }
        System.out.println("завершено");
    }

    public static void readContent(String urlStr) throws IOException {
        URL url = new URL(urlStr);
        BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));

        String line;
        while ((line = in.readLine()) != null) {
            if (!line.isEmpty())
                System.out.println(line);
        }
        in.close();
    }
}
