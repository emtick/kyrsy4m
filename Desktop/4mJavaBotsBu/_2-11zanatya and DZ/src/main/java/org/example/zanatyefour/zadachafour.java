package org.example.zanatyefour;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
class Employee {
    private String name;
    private String position;
    private double salary;

    public Employee(String name, String position, double salary) {
        this.name = name;
        this.position = position;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public String getPosition() {
        return position;
    }

    public double getSalary() {
        return salary;
    }
}

public class zadachafour {
    private List<Employee> employees;

    public zadachafour (String filename) {
        employees = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(", ");
                String name = parts[0];
                String position = parts[1];
                double salary = Double.parseDouble(parts[2]);
                employees.add(new Employee(name, position, salary));
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Employee> searchByName(String name) {
        List<Employee> result = new ArrayList<>();
        for (Employee employee : employees) {
            if (employee.getName().equalsIgnoreCase(name)) {
                result.add(employee);
            }
        }
        return result;
    }

    public List<Employee> searchByPosition(String position) {
        List<Employee> result = new ArrayList<>();
        for (Employee employee : employees) {
            if (employee.getPosition().equalsIgnoreCase(position)) {
                result.add(employee);
            }
        }
        return result;
    }

    public List<Employee> searchBySalary(double salary) {
        List<Employee> result = new ArrayList<>();
        for (Employee employee : employees) {
            if (employee.getSalary() == salary) {
                result.add(employee);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        String filename = "employees.txt";
        zadachafour management = new zadachafour(filename);

        // Поиск работников по имени
        List<Employee> employeesByName = management.searchByName("Иван");
        System.out.println("Работники с именем Иван:");
        for (Employee employee : employeesByName) {
            System.out.println(employee.getName() + " - " + employee.getPosition() + " - " + employee.getSalary());
        }

        // Поиск работников по должности
        List<Employee> employeesByPosition = management.searchByPosition("Менеджер");
        System.out.println("Работники с должностью Менеджер:");
        for (Employee employee : employeesByPosition) {
            System.out.println(employee.getName() + " - " + employee.getPosition() + " - " + employee.getSalary());
        }

        // Поиск работников с определенной зарплатой
        List<Employee> employeesBySalary = management.searchBySalary(50000.0);
        System.out.println("Работники с зарплатой 50000:");
        for (Employee employee : employeesBySalary) {
            System.out.println(employee.getName() + " - " + employee.getPosition() + " - " + employee.getSalary());
        }
    }

}
