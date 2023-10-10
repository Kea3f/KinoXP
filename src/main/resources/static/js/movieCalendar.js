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

function generate_year_range(start, end) {
    var years = "";
    for (var year = start; year <= end; year++) {
        years += "<option value='" + year + "'>" + year + "</option>";
    }
    return years;
}

today = new Date();
currentMonth = today.getMonth();
currentYear = today.getFullYear();
selectYear = document.getElementById("year");
selectMonth = document.getElementById("month");


createYear = generate_year_range(1990, 2050);


document.getElementById("year").innerHTML = createYear;

var calendar = document.getElementById("calendar");
var lang = calendar.getAttribute('data-lang');

var months = "";
var days = "";

var monthDefault = ["January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"];

var dayDefault = ["Mon", "Tue", "Wed", "Thu", "Fri", "Sat","Sun"];

var $dataHead = "<tr>";
for (dhead in days) {
    $dataHead += "<th data-days='" + days[dhead] + "'>" + days[dhead] + "</th>";
}
$dataHead += "</tr>";

//alert($dataHead);
document.getElementById("thead-month").innerHTML = $dataHead;

monthAndYear = document.getElementById("monthAndYear");
showCalendar(currentMonth, currentYear);

function next() {
    currentYear = (currentMonth === 11) ? currentYear + 1 : currentYear;
    currentMonth = (currentMonth + 1) % 12;
    showCalendar(currentMonth, currentYear);
}

function previous() {
    currentYear = (currentMonth === 0) ? currentYear - 1 : currentYear;
    currentMonth = (currentMonth === 0) ? 11 : currentMonth - 1;
    showCalendar(currentMonth, currentYear);
}

function jump() {
    currentYear = parseInt(selectYear.value);
    currentMonth = parseInt(selectMonth.value);
    showCalendar(currentMonth, currentYear);
}

function showCalendar(month, year) {
    var firstDay = new Date(year, month).getDay();

    tbl = document.getElementById("calendar-body");
    tbl.innerHTML = "";

    monthAndYear.innerHTML = months[month] + " " + year;
    selectYear.value = year;
    selectMonth.value = month;

    // Fetching data from the database
    fetch("/api/movies/events")
        .then(function (response) {
            return response.json();
        })
        .then(function (events) {
            // Creating all cells
            var date = 1;
            for (var i = 0; i < 6; i++) {
                var row = document.createElement("tr");

                for (var j = 0; j < 7; j++) {
                    if (i === 0 && j < firstDay) {
                        cell = document.createElement("td");
                        cellText = document.createTextNode("");
                        cell.appendChild(cellText);
                        row.appendChild(cell);
                    } else if (date > daysInMonth(month, year)) {
                        break;
                    } else {
                        cell = document.createElement("td");
                        cell.setAttribute("data-date", date);
                        cell.setAttribute("data-month", month + 1);
                        cell.setAttribute("data-year", year);
                        cell.setAttribute("data-month_name", months[month]);
                        cell.className = "date-picker";
                        cell.innerHTML = "<span>" + date + "</span>";

                        if (
                            date === today.getDate() &&
                            year === today.getFullYear() &&
                            month === today.getMonth()
                        ) {
                            cell.className = "date-picker selected";
                        }
// Inside the loop where you create calendar cells
                        cell.addEventListener("click", function () {
                            // Hide all other popups before showing this one
                            var allPopups = document.querySelectorAll(".movie-popup");
                            allPopups.forEach(function (otherPopup) {
                                otherPopup.style.display = "none";
                            });

                            // Populate the movie information here based on the clicked date
                            var selectedDate = this.getAttribute("data-date");
                            var selectedMonth = this.getAttribute("data-month");
                            var selectedYear = this.getAttribute("data-year");

                            // Find the event for the selected date, month, and year
                            var selectedEvent = events.find(function (event) {
                                var eventDate = new Date(event.showingDate);
                                return (
                                    eventDate.getDate() === parseInt(selectedDate) &&
                                    eventDate.getMonth() === parseInt(selectedMonth) &&
                                    eventDate.getFullYear() === parseInt(selectedYear)
                                );
                            });

                            // Create and append the movie popup to the "moviedatepopup" div
                            var popup = createMoviePopup(selectedEvent);
                            var moviedatepopup = document.getElementById("moviedatepopup");
                            moviedatepopup.innerHTML = ""; // Clear any existing popups
                            moviedatepopup.appendChild(popup);
                        });
                        row.appendChild(cell);
                        date++;
                    }
                }
                tbl.appendChild(row);
            }
        });
}
    function daysInMonth(iMonth, iYear) {
        return 32 - new Date(iYear, iMonth, 32).getDate();
    }

