console.log("Employee deletion script");

const inpEmployeeIdToDelete = document.getElementById("inpEmployeeIdToDelete");
const deleteEmployeeUrl = "http://localhost:8080/employee";

async function deleteEmployee() {
    const employeeIdToDelete = inpEmployeeIdToDelete.value;
    const deleteUrl = `${deleteEmployeeUrl}/${employeeIdToDelete}`;

    const res = await deleteRequest(deleteUrl);

    if (res.ok) {
        alert(`Employee with ID ${employeeIdToDelete} deleted successfully`);
    } else {
        alert(`Failed to delete employee with ID ${employeeIdToDelete}`);
    }
}

async function deleteRequest(url) {
    const fetchOptions = {
        method: "DELETE",
    };

    const response = await fetch(url, fetchOptions);
    return response;
}

function actionDeleteEmployee() {
    deleteEmployee();
}

document.getElementById("pbDeleteEmployee").addEventListener("click", actionDeleteEmployee);
