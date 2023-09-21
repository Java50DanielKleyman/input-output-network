package telran.employees.test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import telran.employees.dto.Employee;

import telran.employees.service.CompanyImpl;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CompanyTests {

	final static String TEST_FILE_NAME = "test.data";
	CompanyImpl company;
	Employee newEmployee = new Employee(2568, "Sasha", "sales", 20000, LocalDate.of(1990, 11, 12));
	Employee[] emplArray = { new Employee(1234, "Vasya", "managment", 10500, LocalDate.of(1983, 7, 10)),
			new Employee(1235, "Pesya", "marketing", 15500, LocalDate.of(1973, 5, 15)),
			new Employee(2568, "Masha", "sales", 19000, LocalDate.of(1983, 1, 1)) };

	@BeforeEach
	void setUp() throws Exception {
		Arrays.stream(emplArray).forEach(employee -> company.addEmployee(employee));
	}

	@Test
	@Order(1)
	void testAddEmployee() {
		assertTrue(company.addEmployee(newEmployee));
		assertTrue(company.getEmployees().contains(newEmployee));
		assertFalse(company.addEmployee(newEmployee));
	}

	@Test
	@Order(2)
	void testRemoveEmployee() {
		assertEquals(newEmployee, company.removeEmployee(2568));
		assertFalse(company.getEmployees().contains(newEmployee));
		assertEquals(null, company.removeEmployee(2568));
	}

	@Test
	@Order(3)
	void testGetEmployee() {
		assertEquals(new Employee(1234, "Vasya", "managment", 10500, LocalDate.of(1983, 7, 10)),
				company.getEmployee(1234));
		assertEquals(null, company.getEmployee(123445));
	}

	@Test
	@Order(4)
	void testGetEmployees() {
		assertEquals(Arrays.stream(emplArray).collect(Collectors.toList()), company.getEmployees());
	}

//	@Test
//	@Order(6)
//	void testRestore() {
//		// TODO
//	}
//
//	@Test
//	@Order(5)
//	void testSave() {
//		// TODO
//	}

}