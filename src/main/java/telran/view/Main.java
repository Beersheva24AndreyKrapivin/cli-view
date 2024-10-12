package telran.view;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.function.Predicate;
import java.util.Arrays;
import java.util.stream.Collectors;

record Employee(long id, String name, String department, int salary, LocalDate birthDate) {
}

public class Main {
    static InputOutput io = new StandartInputOutput();

    /*********************** */
    // For HW #35 constants
    final static int MIN_SALARY = 5000;
    final static int MAX_SALARY = 30000;
    final static String[] DEPARTMENTS = {"QA", "Audit", "Development", "Management"};
    // name should be at least 3 English letters; first - capital, others - lower
    // case
    final static long MIN_ID = 100000;
    final static long MAX_ID = 999999;
    final static int MIN_AGE = 18;
    final static int MAX_AGE = 70;

    /*********************************** */
    public static void main(String[] args) {
        readEmployeeAsObject();
        readEmployeeBySeparateFields();
    }

    static void readEmployeeAsObject() {
        Employee empl = io.readObject("Enter employee data in the format:" +
                "<id>#<name>#<department>#<salary>#<yyyy-MM-DD> ",
                "Wrong format for Employee data", str -> {
                    String[] tokens = str.split("#");
                    return new Employee(Long.parseLong(tokens[0]), tokens[1], tokens[2],
                            Integer.parseInt(tokens[3]), LocalDate.parse(tokens[4]));
                });
        io.writeLine("You are entered the folloing Employee data");
        io.writeLine(empl);
    }

    static void readEmployeeBySeparateFields() {
        // Enter ID, Enter name, Enter department, Enter salary, Enter birthdate
        long id = io.readNumberRange("Enter id (from " + MIN_ID + " to " + MAX_ID + "):", "Wrong format for id", MIN_ID, MAX_ID).longValue();
        
        Predicate<String> namePredicate = str -> str.matches("[A-Z][a-z]{2,}");
        String name = io.readStringPredicate("Enter name (should be at least 3 English letters; first - capital, others - lower): ",
            "Wrong format for name", namePredicate);
       
        HashSet<String> hashSet = (HashSet<String>) Arrays.stream(DEPARTMENTS).collect(Collectors.toSet());
        String department = io.readStringOptions("Enter department: " +
            "Allowed values: " + Arrays.toString(DEPARTMENTS), "Wrong format for department", hashSet);
        
        int salary = io.readNumberRange("Enter salary (from " + MIN_SALARY + " to " + MAX_SALARY + "):", "Wrong format for salary", MIN_SALARY, MAX_SALARY).intValue();
        
        LocalDate minBirhDate = LocalDate.now().minusYears(MAX_AGE);
        LocalDate maxBirhDate = LocalDate.now().minusYears(MIN_AGE);
        LocalDate birthDate = io.readIsoDateRange("Enter birthDate <yyyy-MM-DD> (from " + minBirhDate + " to " + maxBirhDate + ")", 
            "Wrong format for birh date", minBirhDate, maxBirhDate);
        
        Employee empl = new Employee(id, name, department, salary, birthDate);
        io.writeString("You are entered all fields for Employee");
        io.writeLine(empl);
    }
}
