import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<String> tasksList = new ArrayList<>();
        short answer = -1;
        String inputDate;
        String textForUser = "Выберите операцию:\n 0. Выход из программы\n " +
                "1. Добавить дело\n 2. Показать дела\n 3. Удалить дело по номеру\n " +
                "4. Удалить дело по названию\n 5. Удалить дело по ключевому слову\n Ваш выбор: ";

        while (answer != 0) {
            try {
                System.out.print(textForUser);
                inputDate = scanner.nextLine();
                answer = Short.parseShort(inputDate);
            } catch (NumberFormatException e) {
                System.out.println("ERR#1: You input incorrect value!");
                continue;
            }

            if ((answer < -1) | (answer > 5)) {
                System.out.println("ERR#1: You input incorrect value!");
            } else if ((answer > 1) & tasksList.isEmpty()) {
                System.out.println("ERR#2: You don't have tasks today");
            }

            switch (answer) {
                case 0:
                    System.out.println("Bonne journée!");
                    break;
                case 1:
                    System.out.print("Input new task: ");
                    inputDate = scanner.nextLine();
                    tasksList.add(inputDate);
                    break;
                case 2:
                    showTasks(tasksList);
                    break;
                case 3:
                    System.out.print("Input number of task: ");
                    inputDate = scanner.nextLine();
                    int numTask = Integer.parseInt(inputDate);
                    if ((numTask >= 0) & (numTask < tasksList.size())) {
                        tasksList.remove(numTask - 1);
                        System.out.println("Ready!");
                        showTasks(tasksList);
                    } else {
                        System.out.println("ERR#3: Don't exist cell with this number!");
                    }
                    break;
                case 4:
                    System.out.print("Input name of task: ");
                    inputDate = scanner.nextLine();
                    if (!tasksList.remove(inputDate)) {
                        System.out.println("ERR#4: Don't exist cell with this name!");
                    } else {
                        System.out.println("Ready!");
                        showTasks(tasksList);
                    }
                    break;
                case 5:
                    System.out.print("Input special word: ");
                    inputDate = scanner.nextLine();
                    if (!dellCellsWithKey(tasksList, inputDate)) {
                        System.out.println("ERR#5: Don't exist cell that contains this word!");
                    } else {
                        System.out.println("Ready!");
                        showTasks(tasksList);
                    }
                    break;
            }
        }

    }

    public static void showTasks(List<String> tasksList) {
        Iterator<String> iterator = tasksList.iterator();
        int i = 1;

        System.out.println("Your tasks today:");
        while (iterator.hasNext()) {
            System.out.printf("%d. %s\n", i, iterator.next());
            ++i;
        }
        System.out.println();
    }

    public static boolean dellCellsWithKey(List<String> tasksList, String keyWord) {
        boolean token = false;
        Iterator<String> iterator = tasksList.iterator();
        String turCell;

        while (iterator.hasNext() & !tasksList.isEmpty()) {
            turCell = iterator.next();
            if (turCell.contains(keyWord)) {
                iterator.remove();
                token = true;
            }
        }
        return token;
    }
}