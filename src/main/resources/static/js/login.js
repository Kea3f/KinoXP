console.log("Login for employees");

//ensure that the JavaScript code runs only after the HTML document has been fully loaded and parsed.
document.addEventListener("DOMContentLoaded", function () {
    const loginForm = document.getElementById("login-form");
    const usernameInput = document.getElementById("username"); // Assuming the input has the id "username"
    const passwordInput = document.getElementById("password"); // Assuming the input has the id "password"

    loginForm.addEventListener('submit', function () {
        const username = usernameInput.value;
        const password = passwordInput.value;
        console.log(username)

        if (username && password) {
            fetch("http://localhost:2020/api/employees/login", {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({
                    username: username, // Use the variable value, not the string "username"
                    password: password // Use the variable value, not the string "password"
                })
            })
                .then(response => {
                    if (response.ok) {
                        return response.json();
                    }
                    throw new Error('Network response was not ok.');
                })
                .then(data => {
                    if (data) { // Tjek for et gyldigt brugerobjekt
                        window.sessionStorage.setItem('employee', JSON.stringify(data));
                        window.location.href = 'html/homepage.html';
                    } else {
                        alert('Login mislykkedes. Prøv igen.');
                    }
                })
                .catch(error => {
                    console.error('Fejl:', error); // Log den specifikke fejlmeddelelse
                    alert('Login mislykkedes. Prøv igen.'); // Vis en brugervenlig meddelelse
                });

        } else {
            alert('Please enter both username and password.');
        }
    });
});
