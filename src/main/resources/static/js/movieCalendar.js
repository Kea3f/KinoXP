// Function to create a date-specific popup
function createMoviePopup(selectedEvent) {
    // Create a div element for displaying movie info (date-specific popup)
    var popup = document.createElement("div");
    popup.className = "movie-popup";

    // Create a paragraph element for displaying movie information
    var movieInfo = document.createElement("p");

    // Display the movie information in the movieInfo paragraph
    if (selectedEvent) {
        movieInfo.textContent = selectedEvent.title + " at " + selectedEvent.showingTime;
    } else {
        movieInfo.textContent = "No movie available for this date.";
    }

    // Append the movieInfo to the popup
    popup.appendChild(movieInfo);

    return popup;
}

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
            const bookingButton = $("<button>").text("Booking");
                        // Append elements to the modal content
            modalContent.append(titleElement, runtimeElement, ageLimitElement, resumeElement, bookingButton);

            // Show the modal
            $('#movieDetailsModal').modal('show');
        });
    }
//redirecting to booking
function BookingButton() {
    window.location.href ="createbooking.html"
}

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