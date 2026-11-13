package com.revature.studentRecordManager;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StudentPlayground {
    private static final List<Student> students = new ArrayList<>();
    private static final String fileName = "Students.csv";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        loadStudents();

        while (true) {
            printMenu();

            System.out.print("Enter Choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    viewAllStudents();
                    break;
                case 3:
                    findStudentWithHighestGrade();
                    break;
                case 4:
                    computeClassAverage();
                    break;
                case 5:
                    removeStudent();
                    break;
                case 6:
                    System.out.println("Exiting...");

                    return;
                default:
                    System.out.println("Invalid Choice");
                    break;
            }
        }
    }

    private static void printMenu() {
        System.out.println();
        System.out.println("=== Student Record Manager ===");
        System.out.println("1. Add Student");
        System.out.println("2. View All Students");
        System.out.println("3. Select Student with Highest Grade");
        System.out.println("4. Compute Class Average");
        System.out.println("5. Remove Student");
        System.out.println("6. Exit");
        System.out.println();
    }

    private static void addStudent() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Student Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Student Grade: ");
        int grade = scanner.nextInt();

        Student student = new Student(name, grade);
        students.add(student);

        saveStudents();
    }

    private static void viewAllStudents() {
        for (Student student : students) {
            System.out.printf("%s - %d%n", student.getName(), student.getGrade());
        }
    }

    private static void findStudentWithHighestGrade() {
        Student highestGrade = students.getFirst();

        for (Student student : students) {
            if (student.getGrade() > highestGrade.getGrade()) {
                highestGrade = student;
            }
        }

        System.out.println("Student with Highest Grade: ");
        System.out.printf("%s - %d%n", highestGrade.getName(), highestGrade.getGrade());
    }

    private static void computeClassAverage() {
        double average = 0;

        for (Student student : students) {
            average += student.getGrade();
        }

        average /= students.size();

        System.out.printf("Class Average: %.2f%n", average);
    }

    private static void removeStudent() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Student Name to Remove: ");
        String targetName = scanner.nextLine();

        int foundIndex = -1;
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getName().equalsIgnoreCase(targetName)) {
                foundIndex = i;
                break;
            }
        }

        if (foundIndex != -1) {
            students.remove(foundIndex);
            saveStudents();
            System.out.println("Student Removed");
        } else {
            System.out.println("Student Not Found");
        }
    }

    private static void saveStudents() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Student student : students) {
                writer.write(student.getName() + "," + student.getGrade());
                writer.newLine();
            }
        } catch (IOException exception) {
            System.out.println("Error Saving Student Records: " + exception.getMessage());
        }
    }

    private static void loadStudents() {
        File file = new File(fileName);
        if (!file.exists()) {
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    String name = parts[0];
                    int grade = Integer.parseInt(parts[1]);

                    students.add(new Student(name, grade));
                }
            }

            System.out.printf("Loaded %d Student(s) from %s%n", students.size(), fileName);
        } catch (IOException | NumberFormatException exception) {
            System.out.println("Error Loading Student Records: " + exception.getMessage());
        }
    }
}
