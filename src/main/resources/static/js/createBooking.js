document.getElementById("bookingForm").addEventListener("submit", function (event) {
    event.preventDefault();

    const apiUrl = '/createbooking';

    const newBooking = {
        customerName: document.getElementById("customerName").value,
        phoneNo: document.getElementById("phoneNo").value,
        email: document.getElementById("email").value,
        bookingNumber: document.getElementById("bookingNumber").value,
        seatNumber: document.getElementById("seatNumber").value,
        aisle: document.getElementById("aisle").value,
        movie: {
            movieTitle: document.getElementById("movieTitle").value
        }
    };

    fetch(apiUrl, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(newBooking),
    })
        .then(response => {
            if (response.status === 201) {
                return response.json();
            } else {
                throw new Error(`Failed to create booking. Status: ${response.status}`);
            }
        })
        .then(data => {
            // Handle the response data (the newly created booking)
            console.log('New booking created:', data);
        })
        .catch(error => {
            // Handle any errors that occurred during the POST request
            console.error('Error:', error);
        });
});