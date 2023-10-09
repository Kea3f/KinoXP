let currentYear = new Date().getFullYear();
let currentMonth = new Date().getMonth();

// Function to populate the dropdown with movie titles
function populateDropdownWithMovieTitles(query) {
    $.get("/api/movies/search?query=" + query, function (data) { // Updated URL with the query
        const dropdown = $("#movieDropdown");
        dropdown.empty(); // Clear existing items

        data.forEach(title => {
            const a = $("<a>").text(title);
            dropdown.append(a);
        });
    });
}

// Search bar
$("#search-input").on("input", function () {
    var query = $(this).val().trim();
    var dropdown = $("#movieDropdown");

    if (query.length >= 2) { // if length is greater than or equal to 2
        // Populate the dropdown with movie titles
        populateDropdownWithMovieTitles(query);
    } else {
        dropdown.empty(); // If query length is less than 2, clear the dropdown
    }
});

// Handle movie selection from the dropdown using event delegation
$("#movieDropdown").on("click", "a", function () {
    const selectedTitle = $(this).text();
    displayMovieDetails(selectedTitle);
});

// Function to display movie details in the popup
function displayMovieDetails(title) {
    // Make an AJAX request to fetch movie details based on the selected title
    $.get("/api/movies/details?title=" + title, function (data) {
        // Populate the popup content with movie details
        const popup = $("#movieDetailsPopup");
        popup.empty();

        // Create HTML elements to display movie details
        const titleElement = $("<h2>").text(data.title);
        const runtimeElement = $("<p>").text("Runtime: " + data.runtime + " minutes");
        const ageLimitElement = $("<p>").text("Age Limit: " + data.ageLimit);
        const resumeElement = $("<p>").text("Summary: " + data.resume);

        // Append elements to the popup
        popup.append(titleElement, runtimeElement, ageLimitElement, resumeElement);

        // Show the popup
        popup.show();
    });
}

    // Function to generate the calendar HTML for a specific month and year
    function generateCalendarHTML(year, month) {
        // Create a Date object for the first day of the specified month and year
        const firstDay = new Date(year, month, 1);

        // Create a Date object for the last day of the specified month and year
        const lastDay = new Date(year, month + 1, 0);

        // Get the number of days in the month
        const numberOfDays = lastDay.getDate();

        // Create a table element to hold the calendar
        const table = document.createElement('table');

        // Create a table header row for the day names (Mon, Tue, Wed, etc.)
        const headerRow = document.createElement('tr');
        const dayNames = ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun'];
        for (const dayName of dayNames) {
            const th = document.createElement('th');
            th.textContent = dayName;
            headerRow.appendChild(th);
        }
        table.appendChild(headerRow);

        // Create a variable to keep track of the current day
        let currentDay = 1;

        // Create rows and cells for each day of the month
        for (let i = 0; i < 6; i++) { // Assuming a maximum of 6 rows for a month
            const row = document.createElement('tr');

            for (let j = 0; j < 7; j++) { // 7 days in a week
                const cell = document.createElement('td');

                if (i === 0 && j < firstDay.getDay()) {
                    // Empty cells before the first day of the month
                    cell.textContent = '';
                } else if (currentDay <= numberOfDays) {
                    // Fill cells with the day of the month
                    cell.textContent = currentDay;
                    currentDay++;
                } else {
                    // Empty cells after the last day of the month
                    cell.textContent = '';
                }

                row.appendChild(cell);
            }

            table.appendChild(row);
        }

        return table;
    }

    function Calendar() {
        const calendar = document.getElementById('calendar');
        if (!calendar) {
            console.error('Calendar element not found.');
            return;
        }

        // Create navigation arrow elements
        const prevMonthArrow = document.createElement('span');
        prevMonthArrow.className = 'arrow prev';
        prevMonthArrow.textContent = '&lt;';
        const nextMonthArrow = document.createElement('span');
        nextMonthArrow.className = 'arrow next';
        nextMonthArrow.textContent = '&gt;';

        // Attach event listeners to the navigation arrows
        prevMonthArrow.addEventListener('click', () => {
            currentMonth--;
            if (currentMonth < 0) {
                currentYear--;
                currentMonth = 11;
            }
            updateCalendar();
        });

        nextMonthArrow.addEventListener('click', () => {
            currentMonth++;
            if (currentMonth > 11) {
                currentYear++;
                currentMonth = 0;
            }
            updateCalendar();
        });

        function updateCalendarHeader() {
            const header = calendar.querySelector('.calendar-header');
            if (header) {
                header.innerHTML = `
                    <div class="header-wrapper">
                        <span class="month-year">${new Date(currentYear, currentMonth).toLocaleString('default', {month: 'long'})} ${currentYear}</span>
                    </div>
                `;

                header.appendChild(prevMonthArrow); // Append the prev arrow
                header.appendChild(nextMonthArrow); // Append the next arrow
            }
        }

        // Generate the initial calendar
        const calendarTable = generateCalendarHTML(currentYear, currentMonth);
        calendar.appendChild(calendarTable);

        // Add event listeners to date cells
        function addDateCellListeners() {
            const dateCells = document.querySelectorAll('.date-cell');
            dateCells.forEach((cell) => {
                cell.addEventListener('click', () => {
                    const date = cell.getAttribute('data-date');
                    showMovieShowings(date);
                });
            });
        }

        // Add initial date cell listeners
        addDateCellListeners();
    }

// Initialize the calendar
document.addEventListener('DOMContentLoaded', function () {
    // Call the Calendar function directly
    Calendar();
});


    // Function to display movie showings for a date
    async function showMovieShowings(date) {
        const dropdown = document.getElementById('showings-dropdown');

        try {
            // Fetch movie showings for the given date from your API
            const response = await fetch(`/api/movies/events`);

            if (response.ok) {
                const showings = await response.json();

                dropdown.innerHTML = '';

                if (showings.length === 0) {
                    dropdown.innerHTML = 'No movie showings for this date.';
                } else {
                    showings.forEach((showing) => {
                        const movieElement = document.createElement('div');
                        movieElement.textContent = showing.title;
                        movieElement.classList.add('showing-item');
                        movieElement.addEventListener('click', () => {
                            // Get movie details (replace with your actual movie data)
                            const movieTitle = showing.title;
                            const movieDescription = showing.description;

                            // Show movie details in the popup
                            showMovieDetails(movieTitle, movieDescription);
                        });
                        dropdown.appendChild(movieElement);
                    });
                }

                dropdown.style.display = 'block';
            } else {
                // Handle error response
                console.error('Failed to fetch movie showings');
            }
        } catch (error) {
            console.error('An error occurred while fetching movie showings:', error);
        }
    }

    // Function to display movie details in a popup
    function showMovieDetails(movieTitle, movieDescription) {
        const popup = document.getElementById('movie-details-popup');
        const titleElement = document.getElementById('movie-details-title');
        const descriptionElement = document.getElementById('movie-details-description');

        titleElement.textContent = movieTitle;
        descriptionElement.textContent = movieDescription;

        popup.style.display = 'block';
    }

    // Function to close the movie details popup
    function closeMovieDetailsPopup() {
        const popup = document.getElementById('movie-details-popup');
        popup.style.display = 'none';
}
