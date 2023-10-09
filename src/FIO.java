
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FIO {

    public static void main(String[] args) {
        try {
            String[] strings = getUserDataFromConsole();
            saveToFile(strings);
        } catch (FormatException e) {
            System.out.println(e.getMessage());
        }
    }

    public static String[] getUserDataFromConsole() throws FormatException {
        System.out.println("Введите следующие данные через пробел: \n" +
                "Фамилия\n" +
                "Имя\n" +
                "Отчество\n" +
                "Номер телефона\n");
        try (Scanner scanner = new Scanner(System.in, "Cp866")) {
            String input = scanner.nextLine();
            String[] strings = input.split(" ");
            if (strings.length != 4) {
                System.out.println("Проверьте введенные данные, введено неверное кол-во");
            } else {
                String lastName = strings[0];
                String firstName = strings[1];
                String patronymic = strings[2];
                long phone = Long.parseLong(strings[3]);
                return strings;
            }
        } catch (NumberFormatException e) {
            throw new FormatException("При вводе номера телефона допущена ошибка");
        }

        return new String[0];
    }

    private static void saveToFile(String[] filename) {
        try (FileWriter fw = new FileWriter(filename[0], true)) {
            for (String s : filename) {
                fw.write("<" + s + ">");
            }
            fw.write("\n");
            System.out.println(filename[0] + ": данные успешно сохранены");
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Произошла ошибка при введении данных");
        }
    }
}

class FormatException extends Exception {
    public FormatException(String s) {
        super(s);
    }


}
