package telran.view.test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.HashSet;
import java.util.function.Predicate;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.employees.dto.Employee;
import telran.view.*;

class InputOutputTest {
	InputOutput io = new SystemInputOutput();
	String myInput = "Peter#2000-01-01#devops#50000\n" + "123#Peter#2000-11-01#devops#50000\n"
			+ "123#Peter#2000-01-01#devops#50000\n";

	@BeforeEach
	void setUp() throws Exception {

		System.out.println("===>Redirect");
		InputStream myIn = new ByteArrayInputStream(myInput.getBytes());
		System.setIn(myIn);
		io = new SystemInputOutput();

	}

	@AfterEach
	void endTest() throws Exception {
		System.out.println("===>Restore");
		System.setIn(System.in);
		io = new SystemInputOutput();
	}

	@Test
	void testReadEmployeeString() throws Exception {
		Employee empl = io.readObject("Enter employee <id>#<name>#<iso birthdate>#<department>#<salary>",
				"Wrong Employee", str -> {
					String[] tokens = str.split("#");
					if (tokens.length != 5) {
						throw new RuntimeException("must be 5 tokens");
					}
					long id = Long.parseLong(tokens[0]);
					String name = tokens[1];
					String department = tokens[3];
					int salary = Integer.parseInt(tokens[4]);
					LocalDate birthDate = null;
					try {
						birthDate = LocalDate.parse(tokens[2]);
					} catch (DateTimeParseException e) {
						throw new RuntimeException("Invalid date format");
					}
					return new Employee(id, name, department, salary, birthDate);
				});
		io.writeObjectLine(empl);
	}

	@Test
	void testReadEmployeeBySeparateField() throws Exception {
		// TODO
		// id in range [100000-999999]
		// name - more than two letters where first one is a capital
		// birthDate - any localdate in range [1950-12-31 - 2003-12-31
		// department - one out of ["QA", "Development", "Audit", "Accounting",
		// "Management"
		// salary - integer number in range [7000 - 50000]
		Employee empl = io.readObject("Enter employee <id>#<name>#<iso birthdate>#<department>#<salary>",
				"Wrong Employee", str -> {
					String[] tokens = str.split("#");
					if (tokens.length != 5) {
						throw new RuntimeException("must be 5 tokens");
					};
				
					long id = io.readLong(tokens[0], "It is not a Long number", 100000, 999999);
					Predicate<String> pattern = input -> input.matches("[A-Z][a-zA-Z]+");
					String name = io.readString(tokens[1],
							"name must contain more than two letters where first one is a capital", pattern);
					String department = io.readString(tokens[3],
							"department must be one out of QA, Development, Audit, Accounting, Management",
							getHashSet());
					int salary = io.readInt(tokens[4], "salary must be integer number in range [7000 - 50000]", 7000,
							50000);					
					 LocalDate birthDate = io.readIsoDate(tokens[2], "birthDate must be in range [1950-12-31 - 2003-12-31]",
							LocalDate.of(1950, 12, 31), LocalDate.of(2003, 12, 31));

					return new Employee(id, name, department, salary, birthDate);
				});
		io.writeObjectLine(empl);
	}

	private HashSet<String> getHashSet() {
		HashSet<String> options = new HashSet<>();
		options.add("QA");
		options.add("Development");
		options.add("Audit");
		options.add("Accounting");
		options.add("Management");
		return options;
	}

//	@Test
	void testSimpleArithmeticCalculator() {
		// TODO
	}

}