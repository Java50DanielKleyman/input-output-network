package telran.employees.service;

import java.util.*;

import telran.employees.dto.Employee;

public class CompanyImpl implements Company {
	HashMap<Long, Employee> employees = new HashMap<>(); // most effective structure for the interface methods

	@Override
	public boolean addEmployee(Employee empl) {
		if (employees.containsKey(empl.id())) {
			return false;
		}
		employees.put(empl.id(), empl);
		return true;
	}

	@Override
	public Employee removeEmployee(long id) {
		return employees.remove(id);
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
	public void removeAll() {
		employees.clear();
			}
}