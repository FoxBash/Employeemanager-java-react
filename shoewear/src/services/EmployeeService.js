import axios from "axios";

const EMPLOYEE_API_BASE_URL = "http://localhost:8080/api/v1/employees"
class EmployeeService {
   static saveEmployee(employee){
    return axios.post(EMPLOYEE_API_BASE_URL, employee);
   };
   static getEmployees(){
       return axios.get(EMPLOYEE_API_BASE_URL);
   }
   static deleteEmployee(id){
    return axios.delete(EMPLOYEE_API_BASE_URL + "/"+id);
   }
   static getEmployeeById(id){
    return axios.get(EMPLOYEE_API_BASE_URL + "/"+id);
   }
    static updateEmployee(employee, id){
       return axios.put(EMPLOYEE_API_BASE_URL + "/"+id, employee);
    }


}

export default EmployeeService;