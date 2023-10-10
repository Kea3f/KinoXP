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

var dayDefault = ["Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"];

var $dataHead = "<tr>";
for (dhead in days) {
    $dataHead += "<th data-days='" + days[dhead] + "'>" + days[dhead] + "</th>";
}
$dataHead += "</tr>";

// alert($dataHead);
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

                        // Create a dropdown element for displaying movie info
                        var dropdown = document.createElement("select");
                        dropdown.className = "movie-dropdown";
                        dropdown.style.display = "none"; // Initially hide the dropdown

                        // Populate the dropdown with movie information
                        events.forEach(function (event) {
                            var eventDate = new Date(event.showingDate);
                            if (
                                eventDate.getDate() === date &&
                                eventDate.getMonth() === month &&
                                eventDate.getFullYear() === year
                            ) {
                                var option = document.createElement("option");
                                option.text = event.title + " at " + event.showingTime;
                                dropdown.add(option);
                            }
                        });

                        // Add a click event listener to the cell
                        cell.addEventListener("click", function () {
                            // Hide all other dropdowns before showing this one
                            var allDropdowns = document.querySelectorAll(".movie-dropdown");
                            allDropdowns.forEach(function (otherDropdown) {
                                otherDropdown.style.display = "none";
                            });

                            // Show or hide the dropdown for this cell
                            var selectedDropdown = this.querySelector(".movie-dropdown");
                            if (selectedDropdown.style.display === "none") {
                                selectedDropdown.style.display = "block";
                            } else {
                                selectedDropdown.style.display = "none";
                            }
                        });

                        // Add a click event listener to the dropdown options
                        dropdown.addEventListener("change", function () {
                            // Get the selected option
                            var selectedOption = dropdown.options[dropdown.selectedIndex];
                            var selectedTitle = selectedOption.text;

                            // Make an AJAX request to fetch movie details based on the selected title
                            fetch("/api/movies/details?title=" + selectedTitle)
                                .then(function (response) {
                                    return response.json();
                                })
                                .then(function (data) {
                                    // Create a popup div element for displaying movie details
                                    var movieDetailsPopup = document.createElement("div");
                                    movieDetailsPopup.id = "movieDetailsPopup";
                                    movieDetailsPopup.className = "movie-details-popup";

                                    // Create HTML elements to display movie details
                                    var titleElement = document.createElement("h2");
                                    titleElement.textContent = data.title;

                                    var runtimeElement = document.createElement("p");
                                    runtimeElement.textContent = "Runtime: " + data.runtime + " minutes";

                                    var ageLimitElement = document.createElement("p");
                                    ageLimitElement.textContent = "Age Limit: " + data.ageLimit;

                                    var resumeElement = document.createElement("p");
                                    resumeElement.textContent = "Summary: " + data.resume;

                                    // Append elements to the popup
                                    movieDetailsPopup.appendChild(titleElement);
                                    movieDetailsPopup.appendChild(runtimeElement);
                                    movieDetailsPopup.appendChild(ageLimitElement);
                                    movieDetailsPopup.appendChild(resumeElement);

                                    // Add a close button to the popup
                                    var closeButton = document.createElement("button");
                                    closeButton.textContent = "Close";
                                    closeButton.addEventListener("click", function () {
                                        // Close the popup when the close button is clicked
                                        movieDetailsPopup.style.display = "none";
                                    });

                                    movieDetailsPopup.appendChild(closeButton);

                                    // Append the popup to the body
                                    document.body.appendChild(movieDetailsPopup);
                                });
                        });

                        cell.appendChild(dropdown);

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
