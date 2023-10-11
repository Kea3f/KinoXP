console.log("Employee editing script");

const inpEmployeeId = document.getElementById("inpEmployeeId"); // Input field for employee ID
const inpUsername = document.getElementById("inpUsername");
const inpPassword = document.getElementById("inpPassword");
const inpEmployeeName = document.getElementById("inpEmployeeName");
const inpPhoneNo = document.getElementById("inpPhoneNo");
const inpEmail = document.getElementById("inpEmail");

const employeeUrl = "http://localhost:2020/employee";

function getEmployee() {
    const employee = {
        employeeId: parseInt(inpEmployeeId.value), // Include the employee ID for updating
        username: inpUsername.value,
        password: inpPassword.value,
        employee_name: inpEmployeeName.value,
        employee_phoneNo: parseInt(inpPhoneNo.value),
        employee_mail: inpEmail.value,
    };
    console.log(employee);
    return employee;
}

async function putEmployee() {
    const employee = getEmployee();
    const putUrl = `${employeeUrl}/${employee.employeeId}`;
    const res = await postObjectAsJson(putUrl, employee, "PUT");
    if (res.ok) {
        alert("Employee updated");
    } else {
        alert("Failed to update employee");
    }
}

async function postObjectAsJson(url, object, httpVerb) {
    const objectAsJsonString = JSON.stringify(object);
    const fetchOptions = {
        method: httpVerb,
        headers: {
            "Content-Type": "application/json",
        },
        body: objectAsJsonString,
    };
    const response = await fetch(url, fetchOptions);
    return response;
}

function actionPutEmployee() {
    putEmployee();
}

document.getElementById("pbPutEmployee").addEventListener("click", actionPutEmployee);
