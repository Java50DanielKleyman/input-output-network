package telran.employees;

import java.io.Serializable;
import java.util.ArrayList;

import telran.employees.dto.*;
import telran.employees.service.Company;
import telran.net.ApplProtocol;
import telran.net.Request;
import telran.net.Response;
import telran.net.ResponseCode;

public class CompanyProtocol implements ApplProtocol {
	private Company company;

	public CompanyProtocol(Company company) {
		this.company = company;
	}

	@Override
	public Response getResponse(Request request) {
		Serializable requestData = request.requestData();
		String requestType = request.requestType();
		Response response = null;
		Serializable responseData = 0;
		try {
			responseData = switch (requestType) {
			case "employee/add" -> employee_add(requestData);
			case "employee/get" -> employee_get(requestData);
			case "employees/all" -> employees_all(requestData);
			case "employee/salary/update" -> employee_salary_update(requestData);
			case "employee/remove" -> employee_remove(requestData);
			case "employees/getDepartmentSalaryDistribution" -> getDepartmentSalaryDistribution();
			case "employees/getEmployeesByDepartment" -> getEmployeesByDepartment(requestData);
			case "employee/department/update" -> employee_department_update(requestData);
			case "employees/getEmployeesByAge" -> getEmployeesByAge(requestData);
			case "employees/getEmployeesBySalary" -> getEmployeesBySalary(requestData);
			case "employees/getSalaryDistribution" -> getSalaryDistribution(requestData);
			default -> 0;
			};
			response = responseData == (Integer) 0 ? new Response(ResponseCode.WRONG_TYPE, requestType)
					: new Response(ResponseCode.OK, responseData);
		} catch (Exception e) {
			response = new Response(ResponseCode.WRONG_DATA, e.getMessage());
		}

		return response;
	}

	private Serializable getSalaryDistribution(Serializable requestData) {
		int interval = (int) requestData;
		return (Serializable) company.getSalaryDistribution(interval);
	}

	private Serializable getEmployeesBySalary(Serializable requestData) {
		EmployeesSalaryInt data = (EmployeesSalaryInt) requestData;
		return (Serializable) company.getEmployeesBySalary(data.salaryFrom(), data.salaryTo());
	}

	private Serializable getEmployeesByAge(Serializable requestData) {
		EmployeesAgeInterval data = (EmployeesAgeInterval) requestData;
		return (Serializable) company.getEmployeesByAge(data.ageFrom(), data.ageTo());
	}

	private Serializable employee_department_update(Serializable requestData) {
		UpdateDepartmentData data = (UpdateDepartmentData) requestData;
		return company.updateDepartment(data.id(), data.department());
	}

	private Serializable getEmployeesByDepartment(Serializable requestData) {
		String department = (String) requestData;
		return (Serializable) company.getEmployeesByDepartment(department);
	}

	private Serializable getDepartmentSalaryDistribution() {

		return (Serializable) company.getDepartmentSalaryDistribution();
	}

	private Serializable employee_remove(Serializable requestData) {
		long id = (long) requestData;
		return company.removeEmployee(id);
	}

	private Serializable employee_salary_update(Serializable requestData) {
		UpdateSalaryData data = (UpdateSalaryData) requestData;
		long id = data.id();
		int newSalary = data.newSalary();
		return company.updateSalary(id, newSalary);
	}

	private Serializable employees_all(Serializable requestData) {

		return new ArrayList<>(company.getEmployees());
	}

	private Serializable employee_get(Serializable requestData) {
		long id = (long) requestData;
		return company.getEmployee(id);
	}

	private Serializable employee_add(Serializable requestData) {
		Employee empl = (Employee) requestData;
		return company.addEmployee(empl);
	}

}