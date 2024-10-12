package telran.view;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

//record Employee(long id, String name, String department, int salary, LocalDate birthDate){}

public class InputOutputTest {
    /***** */
    //For HW #35 constants
    final static int MIN_SALARY = 5000;
    final static int MAX_SALARY = 30000;
    final static String[] DEPARTMENTS = {"QA", "Audit", "Development", "Management"};
    //name should be at least 3 English letters; first - capital, others - lower case
    final static long MIN_ID = 100_000;
    final static long MAX_ID = 999_999;
    final static int MIN_AGE = 18;
    final static int MAX_AGE = 70;
    /******* */
    
    InputOutput io = new StandartInputOutput();

    // @Test
    // void readEmployeeAsObject() {
    //     Employee empl = io.readObject("Enter employee data in the format:" +
    //         "<id>#<name>#<department>#<salary>#<yyyy-MM-DD> ",
    //         "Wrong format for Employee data", str -> {
    //             String[] tokens = str.split("#");
    //             return new Employee(Long.parseLong(tokens[0]), tokens[1], tokens[2], 
    //             Integer.parseInt(tokens[3]), LocalDate.parse(tokens[4]));
    //         });
    //     io.writeLine("You are entered the folloing Employtt data");
    //     io.writeLine(empl);
    // }

    // @Test
    // void readEmployeeBySeparateFields() {
    //     //TODO
    //     //Enter ID, Enter name, Enter department, Enter salary, Enter birthdate
    // }
}
