package telran.employees.test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import telran.employees.dto.Employee;
import telran.employees.service.Company;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CompanyTests {
//TODO
	final static String TEST_FILE_NAME = "test.data";
	Company company;
	Employee [] emplArray = {new Employee(1234, "Vasya", "managment", 10500, LocalDate.of(1983, 7, 10)),
			new Employee(1235, "Pesya", "marketing", 15500, LocalDate.of(1973, 5, 15)),
			new Employee(2568, "Masha", "sales", 19000, LocalDate.of(1983, 1, 1))};
	@BeforeEach
	void setUp() throws Exception {
		Arrays.stream(emplArray).forEach(employee -> company.addEmployee(employee));
	}

	@Test
	void testAddEmployee() {
		Employee newEmployee = new Employee(2568, "Sasha", "sales", 20000, LocalDate.of(1990, 11, 12));	
		assertEquals(true, company.addEmployee(newEmployee));
		assertEquals(true, company.getEmployees().contains(newEmployee));
	} 

	@Test
	void testRemoveEmployee() {
		//TODO
	}

	@Test
	void testGetEmployee() {
		//TODO
	}

	@Test
	void testGetEmployees() {
		//TODO
	}
	@Test
	@Order(2)
	void testRestore() {
		//TODO
	}
	@Test
	@Order(1)
	void testSave() {
		//TODO
	}

}