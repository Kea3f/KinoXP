$(document).ready(function () {
    const searchInput = $("#search-input");

    searchInput.on("input", function () {
        const query = $(this).val();

        if (query.length >= 2) {
            populateDropdownWithMovieTitles(query);
        }
    });
});

// Function to populate the dropdown with movie titles
function populateDropdownWithMovieTitles(query) {
    $.get("/api/movies/search?query=" + query, function (data) {
        const dropdown = $("#movieDropdown");
        dropdown.empty(); // Clear existing items

        data.forEach(title => {
            const a = $("<a>").text(title);
            dropdown.append(a);
        });
    });
}

// Handle movie selection from the dropdown using event delegation
$("#movieDropdown").on("click", "a", function () {
    const selectedTitle = $(this).text();
    displayMovieDetails(selectedTitle);
});

// Function to display movie details in the modal
function displayMovieDetails(title) {
    // Make an AJAX request to fetch movie details based on the selected title
    $.get("/api/movies/details?title=" + title, function (data) {
        // Populate the modal content with movie details
        const modalContent = $("#movieDetailsModal .modal-content");
        modalContent.empty();

        // Create HTML elements to display movie details
        const titleElement = $("<h2>").text(data.title);
        const runtimeElement = $("<p>").text("Runtime: " + data.runtime + " minutes");
        const ageLimitElement = $("<p>").text("Age Limit: " + data.ageLimit);
        const resumeElement = $("<p>").text("Summary: " + data.resume);
        const movieid = $("<p>").text("MovieId" + data.movieid);

        // Create the "Book" button
        const bookingButton = $('<button class="btn btn-primary">Book</button>');
        bookingButton.data("movieid", data.movieid);

        // Handle the click event of the "Book" button
            bookingButton.click(function () {
            const movieid = $(this).data("movieid");

            // Close the movie details modal
            $('#movieDetailsModal').modal('hide');


            // Open the booking modal
            openBookingModal(movieid);
        });

        modalContent.append(titleElement, runtimeElement, ageLimitElement, resumeElement, movieid, bookingButton);

        $('#movieDetailsModal').modal('show');
    });
}


// Define openBookingModal to handle the opening of the booking modal
function openBookingModal(movieid) {
    const bookingModal = document.getElementById('bookingModal');
    const movieidInput = document.getElementById('movieidInput');
    movieidInput.value = movieid; // Set the movieid in the input field
    bookingModal.style.display = 'block';
}

const bookingForm = document.getElementById('bookingForm');

bookingForm.addEventListener('submit', (event) => {
    event.preventDefault();

    // Get the form data
    const formData = new FormData(bookingForm);

    // Create an object from the form data
    const bookingData = {};
    formData.forEach((value, key) => {
        bookingData[key] = value;
    });

    // Send a POST request to the server
    fetch('/bookings/createbooking', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        },
        body: new URLSearchParams(bookingData)
    })
        .then(response => response.json())
        .then(data => {
            console.log('Booking created:', data);
            // You can close the modal or handle the response as needed
        })
        .catch(error => {
            console.error('Error creating booking:', error);
        });
});


// Wait for the DOM to be ready
document.addEventListener('DOMContentLoaded', function () {
    // Make an AJAX request to the "/events" endpoint
    fetch('/api/movies/events')
        .then(response => response.json())
        .then(data => {
            // Handle the data and populate the HTML
            displayCalendarEvents(data);
        })
        .catch(error => {
            console.error('Error fetching data:', error);
        });
});

function displayCalendarEvents(events) {
    const calendarEventsDiv = document.getElementById('calendar-events');
    if (events.length === 0) {
        calendarEventsDiv.textContent = 'No calendar events available.';
        return;
    }
    const eventList = document.createElement('ul');

    events.forEach(event => {
        const eventItem = document.createElement('li');
        eventItem.innerHTML = `
        <strong>${event.title}</strong><br>
        Date: ${event.showingDate}<br>
        Time: ${event.showingTime}
        <button onclick="displayMovieDetails('${event.title}')">View Details</button>
        `;
        eventList.appendChild(eventItem);
    });

    calendarEventsDiv.appendChild(eventList);

}