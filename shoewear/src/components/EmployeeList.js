import {useNavigate} from "react-router-dom";
import {useEffect, useState} from "react";
import EmployeeService from "../services/EmployeeService";
import Employee from "./Employee";

const EmployeeList = () => {
    const navigate = useNavigate();
    const [loading, setLoading] = useState(true);
    const [employees, setEmployees] = useState(null);
    useEffect(() => {
        const fetchData = async () =>{
            setLoading(true);
            try {
                const response = await EmployeeService.getEmployees();
                setEmployees(response.data);
            }catch (e){
            console.log(e)
            }
            setLoading(false)
        };
        fetchData();
    }, []);
    const deleteEmployee = (e, id) =>{
        e.preventDefault();
        EmployeeService.deleteEmployee(id).then((response) => {
            if(employees){
                setEmployees((prevElement) =>
                prevElement.filter((employee) => employee.id !== id))
        }  
        });
    }
return (
    <div className="container mx-auto my-8">
    <div className="h-12">
        <button onClick={() => navigate("/addEmployee")} className="rounded bg-slate-600 text-white px-6 py-2">
            Add Employee
        </button>
    </div>
        <div className="flex shadow border-b">
            <table className="min-w-full">
                <thead className="bg-gray-50">
                <tr>
                    <th className="text-left font-medium uppercase tracking-wider py-3 px-6 text-grey-500">First Name</th>
                    <th className="text-left font-medium uppercase tracking-wider py-3 px-6 text-grey-500">Last Name</th>
                    <th className="text-left font-medium uppercase tracking-wider py-3 px-6 text-grey-500">Email Id</th>
                    <th className="text-right font-medium uppercase tracking-wider py-3 px-6 text-grey-500">Actions</th>
                </tr>
                </thead>
                {!loading && (
                <tbody className="bg-white">
                {employees.map((employee) => (
                    <Employee deleteEmployee={deleteEmployee} employee={employee} key={employee.id}/>
                ))}
                </tbody>
                )}

            </table>

        </div>
    </div>
);
}
export default EmployeeList;