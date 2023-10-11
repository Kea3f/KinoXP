console.log("Employee creation script");

const inpUsername = document.getElementById("inpUsername");
const inpPassword = document.getElementById("inpPassword");
const inpEmployeeName = document.getElementById("inpEmployeeName");
const inpPhoneNo = document.getElementById("inpPhoneNo");
const inpEmail = document.getElementById("inpEmail");

const employeeUrl = "http://localhost:2020/employee";

function getEmployee() {
    const employee = {
        username: inpUsername.value,
        password: inpPassword.value,
        employee_name: inpEmployeeName.value,
        employee_phoneNo: parseInt(inpPhoneNo.value),
        employee_mail: inpEmail.value,
    };
    console.log(employee);
    return employee;
}

async function postEmployee() {
    const employee = getEmployee();
    const res = await postObjectAsJson(employeeUrl, employee, "POST");
    if (res.ok) {
        const createdEmployee = await res.json(); // Parse the response JSON
        alert(`Employee saved with ID: ${createdEmployee.employeeId}`);
    } else {
        alert("Failed to save employee");
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

function actionPostEmployee() {
    postEmployee();
}

document.getElementById("pbPostEmployee").addEventListener("click", actionPostEmployee);
