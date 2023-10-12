document.addEventListener("DOMContentLoaded", function () {
    const phoneInput = document.getElementById("phone-input");
    const popup = document.getElementById("popup");
    const popupContentData = document.getElementById("popup-content-data");
    const closePopup = document.getElementById("close-popup");
    const printBookingButton = document.getElementById("print-booking-button");

    // Function to display the pop-up with booking information
    function showPopup(bookingData) {
        popupContentData.innerHTML = bookingData;
        popup.style.display = "block";
    }

    // Function to hide the pop-up
    function hidePopup() {
        popup.style.display = "none";
    }

    // Function to print booking information
    function printBookingInformation() {
        const bookingInfo = popupContentData.innerHTML;

        // Create a new window for printing
        const printWindow = window.open('', '', 'width=600,height=600');
        printWindow.document.open();
        printWindow.document.write('<html><head><title>Booking Information</title></head><body>');
        printWindow.document.write(bookingInfo);
        printWindow.document.write('</body></html>');
        printWindow.document.close();
        printWindow.print();
        printWindow.close();
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
                    const bookingData = ` Booking Number: ${data.bookingNumber}<br>
                                        <br>
                                        Customer Name: ${data.customerName}<br>
                                        <br>
                                         Email: ${data.email}<br>
                                         <br>
                                         Phone Number: ${data.phoneNo}<br>
                                         <br>
                                         Movie Title: ${data.movieTitle}<br>                                                                       
                                         <br>
                                          Seat Number: ${data.seatNumber}<br>
                                         <br>
                                         Aisle Number: ${data.aisle}<br>`;

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

    // Event listener to print booking information
    printBookingButton.addEventListener("click", printBookingInformation);
});
