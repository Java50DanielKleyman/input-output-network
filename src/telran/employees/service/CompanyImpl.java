package telran.employees.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import telran.employees.dto.DepartmentSalary;
import telran.employees.dto.Employee;
import telran.employees.dto.SalaryDistribution;

public class CompanyImpl implements Company {
	HashMap<Long, Employee> employees = new HashMap<>(); // most effective structure for the interface methods
	TreeMap<Integer, List<Employee>> employeesAge = new TreeMap<>();
	TreeMap<Integer, List<Employee>> employeesSalary = new TreeMap<>();
	TreeMap<String, List<Employee>> employeesDepartment = new TreeMap<>();

	@Override
	public boolean addEmployee(Employee empl) {
		boolean res = employees.putIfAbsent(empl.id(), empl) == null;
		if (res) {
			addEmployeesAge(empl);
			addEmployeesSalary(empl);
			addEmployeesDepartment(empl);
		}

		return res;
	}

	private void addEmployeesDepartment(Employee empl) {
		String department = empl.department();
		employeesDepartment.computeIfAbsent(department, k -> new LinkedList<>()).add(empl);

	}

	private void addEmployeesSalary(Employee empl) {
		int salary = empl.salary();
		employeesSalary.computeIfAbsent(salary, k -> new LinkedList<>()).add(empl);

	}

	private void addEmployeesAge(Employee empl) {
		int age = getAge(empl.birthdate());
		employeesAge.computeIfAbsent(age, k -> new LinkedList<>()).add(empl);

	}

	@Override
	public Employee removeEmployee(long id) {
		Employee empl = employees.remove(id);
		if (empl != null) {
			removeEmployeeAge(empl);
			removeEmployeeSalary(empl);
			removeEmployeeDepartment(empl);
		}

		return empl;
	}

	private void removeEmployeeDepartment(Employee empl) {
		String department = empl.department();
		List<Employee> list = employeesDepartment.get(department);
		list.remove(empl);
		if (list.isEmpty()) {
			employeesDepartment.remove(department);
		}

	}

	private void removeEmployeeSalary(Employee empl) {
		int salary = empl.salary();
		List<Employee> list = employeesSalary.get(salary);
		list.remove(empl);
		if (list.isEmpty()) {
			employeesSalary.remove(salary);
		}

	}

	private void removeEmployeeAge(Employee empl) {
		int age = getAge(empl.birthdate());
		List<Employee> list = employeesAge.get(age);
		list.remove(empl);
		if (list.isEmpty()) {
			employeesAge.remove(age);
		}

	}

	@Override
	public Employee getEmployee(long id) {

		return employees.get(id);
	}

	@Override
	public List<Employee> getEmployees() {
		return new ArrayList<>(employees.values());
	}

	@Override
	public List<DepartmentSalary> getDepartmentSalaryDistribution() {

		return employeesDepartment.entrySet().stream().map(entry -> getDepartmentSalary(entry))
				.sorted((ds1, ds2) -> Double.compare(ds1.salary(), ds2.salary())).toList();

	}

	private DepartmentSalary getDepartmentSalary(Entry<String, List<Employee>> entry) {
		int sum = entry.getValue().stream().mapToInt(Employee::salary).sum();
		int averageSalary = entry.getValue().isEmpty() ? 0 : sum / entry.getValue().size();

	    return new DepartmentSalary(entry.getKey(), averageSalary);
	}

	@Override
	public List<SalaryDistribution> getSalaryDistribution(int interval) {
		Map<Integer, Long> mapIntervalNumbers = employees.values().stream()
				.collect(Collectors.groupingBy(e -> e.salary() / interval, Collectors.counting()));
		return mapIntervalNumbers
				.entrySet().stream().map(e -> new SalaryDistribution(e.getKey() * interval,
						e.getKey() * interval + interval, e.getValue().intValue()))
				.sorted((sd1, sd2) -> Integer.compare(sd1.min(), sd2.min())).toList();
	}

	@Override
	public List<Employee> getEmployeesByDepartment(String department) {
		return employeesDepartment.get(department);
	}

	@Override
	public List<Employee> getEmployeesBySalary(int salaryFrom, int salaryTo) {

		return employeesSalary.subMap(salaryFrom, salaryTo).values().stream().flatMap(List::stream).toList();
	}

	@Override
	public List<Employee> getEmployeesByAge(int ageFrom, int ageTo) {

		return employeesAge.subMap(ageFrom, ageTo).values().stream().flatMap(List::stream).toList();
	}

	private int getAge(LocalDate birthDate) {

		return (int) ChronoUnit.YEARS.between(birthDate, LocalDate.now());
	}

	@Override
	public Employee updateSalary(long id, int newSalary) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Employee updateDepartment(long id, String department) {
		// TODO Auto-generated method stub
		return null;
	}

}