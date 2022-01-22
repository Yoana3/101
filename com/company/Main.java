package com.company;

import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {
    public static final int INDEX_NAME = 0;
    public static final int INDEX_EMAIL = 1;
    public static final int INDEX_ID = 2;
    public static final int INDEX_FROM_DATE = 3;
    public static final int INDEX_TO_DATE = 4;
    public static final int INDEX_TYPE = 5;

    static String[][] applications = new String[1000][6];
    static int lastApplicationIndex = -1;
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        testTheProgram();
        boolean working = true;
        while (working) {
            printAllOptionsNames();
            int userOption = getUserInput();
            switch (userOption) {
                case 1:
                    startNewApplicationWriting();
                    break;
                case 2:
                    showAllApplications(applications, lastApplicationIndex);
                    break;
                case 3:
                    searchForExactEmployeeByName();
                    break;
                case 4:
                    break;
                case 5:
                    working = false;
                    break;
                default:
                    System.out.println("Въведете данните правилно!");
            }
        }
    }

    public static void testTheProgram() {
        makingApplication("Ива", "iva@gmail.com", "7029394911", "22.01.2022",
                "29.01.2022", "Платена");
        makingApplication("Петър", "pesho23@gamil.com", "758894911", "25.01.2022",
                "30.01.2022", "Платена");
        makingApplication("Никол", "nikol145@gamil.com", "7675394911", "30.01.2022",
                "02.02.2022", "Не платена");
        makingApplication("Виктор", "ivanov23@gamil.com", "756784911", "25.01.2022",
                "29.01.2022", "Не платена");
        makingApplication("Виктория", "ivan23@gamil.com", "755494911", "25.01.2022",
                "29.01.2022", "Не платена");
        makingApplication("Елена", "ivan23@gamil.com", "758894911", "25.01.2022",
                "29.01.2022", "Не платена");
    }

    private static void printAllOptionsNames() {
        System.out.println("Изберете опция:\n1.Заяви отпуска\n2.Виж всички отпуски \n3.Виж отпуска за служител" +
                " \n4.Промени статус на отпуска\n5.Изход\nВъведете опция:");

    }

    private static int getUserInput() {
        String optionInput = scanner.nextLine();
        while (!isNumeric(optionInput)) {
            System.out.println("Моля, въведете номера на желаната опция!");
            optionInput = scanner.nextLine();
        }
        return Integer.parseInt(optionInput);
    }

    private static void searchForExactEmployeeByName() {
        System.out.println("Въведи име:");
        String employeeName = scanner.nextLine();
        String[][] applicationsByEmployeeName = getApplicationsForEmployee(employeeName);
        showApplications(applicationsByEmployeeName);
    }

    private static void startNewApplicationWriting() {
        System.out.println("Въведете име:");
        String name = scanner.nextLine();
        System.out.println("Въведете имейл:");
        String email = scanner.nextLine();
        System.out.println("Въведете ЕГН:");
        String ID = scanner.nextLine();
        System.out.println("Въведете начална дата:");
        String fromDate = scanner.nextLine();
        System.out.println("Въведете крайна дата:");
        String toDate = scanner.nextLine();
        System.out.println("Въведете вида отпуск:");
        String type = scanner.nextLine();

        makingApplication(name, email, ID, fromDate, toDate, type);
    }


    private static void showAllApplications(String[][] applications, int lastApplicationIndex) {
        String pattern = "%s \t| %s \t |%s \t\t\t| %s \t\t\t| %s \t\t\t| %s \t\t ";
        System.out.println(String.format(pattern, "Име", "Имейл           ", "ЕГН        ",
                "Начална дата", "Крайна дата", "Вид"));
        for (int rowIndex = 0; rowIndex <= Main.lastApplicationIndex; rowIndex++) {
            System.out.println(String.format(pattern,
                    getApplicationName(rowIndex),
                    getApplicationEmail(rowIndex),
                    getApplicationID(rowIndex),
                    getApplicationFromDate(rowIndex),
                    getApplicationToDate(rowIndex),
                    getApplicationType(rowIndex)));
        }
    }

    private static int makingApplication(
            String name,
            String email,
            String ID,
            String fromDate,
            String toDate,
            String type) {

        lastApplicationIndex++;

        makeTheNameForApplication(lastApplicationIndex, name);
        makeEmailForApplication(lastApplicationIndex, email);
        makeTheIDForApplication(lastApplicationIndex, ID);
        makeFromDateForApplication(lastApplicationIndex, fromDate);
        makeToDateForApplication(lastApplicationIndex, toDate);
        makeTypeOfApplication(type);

        return lastApplicationIndex;
    }

    private static void showApplications(String[][] applications) {
        showAllApplications(applications, lastApplicationIndex);
    }

    private static String[][] getApplicationsForEmployee(String employeeName) {
        return getApplicationsByApplicationColumn(INDEX_NAME, employeeName);
    }


    private static String[][] getApplicationsByApplicationColumn(int columnIndex, String searchCondition) {
        int resultCount = getApplicationCount(columnIndex, searchCondition);
        return getApplicationsByColumn(columnIndex, searchCondition, resultCount);
    }

    private static String[][] getApplicationsByColumn(int columnIndex, String searchCondition, int expectedResultCount) {
        String[][] sortedApplications = new String[expectedResultCount][5];
        int resultIndex = 0;
        for (int i = 0; i <= lastApplicationIndex; i++) {
            if (applications[i][columnIndex].equals(searchCondition)) {
                sortedApplications[resultIndex] = applications[i];
                resultIndex++;
            }
        }
        return sortedApplications;
    }

    private static int getApplicationCount(int columnIndex, String searchCondition) {
        int resultCount = 0;
        for (int i = 0; i <= lastApplicationIndex; i++) {
            if (applications[i][columnIndex].equals(searchCondition)) {
                resultCount++;
            }
        }
        return resultCount;
    }


    public static boolean isNumeric(String string) {
        // Checks if the provided string
        // is a numeric by applying a regular
        // expression on it.
        String regex = "[0-9]+[\\.]?[0-9]*";
        return Pattern.matches(regex, string);
    }

    private static void makeTypeOfApplication(String type) {
        applications[lastApplicationIndex][5] = type;
    }

    private static void makeToDateForApplication(int lastApplicationIndex, String toDate) {
        applications[lastApplicationIndex][4] = toDate;
    }

    private static void makeFromDateForApplication(int lastApplicationIndex, String fromDate) {
        applications[lastApplicationIndex][3] = fromDate;
    }

    private static void makeTheIDForApplication(int lastApplicationIndex, String ID) {
        applications[lastApplicationIndex][2] = ID;
    }

    private static void makeEmailForApplication(int lastApplicationIndex, String email) {
        applications[lastApplicationIndex][1] = email;
    }

    public static void makeTheNameForApplication(int lastApplicationIndex, String name) {
        applications[lastApplicationIndex][0] = name;
    }

    private static String getApplicationType(int applicationIndex) {
        return applications[applicationIndex][5];
    }

    private static String getApplicationToDate(int applicationIndex) {
        return applications[applicationIndex][4];
    }

    private static String getApplicationFromDate(int applicationIndex) {
        return applications[applicationIndex][3];
    }

    private static String getApplicationID(int applicationIndex) {
        return applications[applicationIndex][2];
    }

    private static String getApplicationEmail(int applicationIndex) {
        return applications[applicationIndex][1];
    }

    private static String getApplicationName(int applicationIndex) {
        return applications[applicationIndex][0];
    }

}