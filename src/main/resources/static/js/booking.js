document.addEventListener("DOMContentLoaded", function () {
    const phoneInput = document.getElementById("phone-input");
    const popup = document.getElementById("popup");
    const popupContentData = document.getElementById("popup-content-data");
    const closePopup = document.getElementById("close-popup");

    // Function to display the pop-up with booking information
    function showPopup(bookingData) {
        popupContentData.innerHTML = bookingData;
        popup.style.display = "block";
    }

    // Function to hide the pop-up
    function hidePopup() {
        popup.style.display = "none";
    }

    // Event listener for the phone input field
    phoneInput.addEventListener("keypress", function(event) {
        if (event.key !== "Enter") {
            return;
        }

        // If the user presses the "Enter" key on the keyboard
        event.preventDefault();

        // Ensure there is at least 8 characters in the value of the phoneInput
        const phoneNumber = phoneInput.value;

        // Send an AJAX request to your Spring Boot controller
        fetch(`/bookings/findByPhoneNo?phoneNo=${phoneNumber}`)
            .then(response => response.json())
            .then(data => {
                if (data.id) { // Assuming that 'id' is present in the response when a booking is found
                    const bookingData = `Customer Name: ${data.customerName}<br>
                                         Email: ${data.email}<br>
                                         Phone Number: ${data.phoneNo}<br>
                                         Aisle Number: ${data.aisle}<br>
                                         Seat Number: ${data.seatNumber}<br>
                                         Booking Number: ${data.bookingNumber}<br>`;
                    showPopup(bookingData);
                } else {
                    hidePopup();
                }
            })
            .catch(error => {
                console.error(error);
            });
    });

    // Event listener to close the pop-up
    closePopup.addEventListener("click", hidePopup);
});
