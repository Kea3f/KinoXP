const nameInput = document.getElementById('name-input');
const suggestionsContainer = document.getElementById('suggestions-container');

nameInput.addEventListener('input', () => {
    const inputValue = nameInput.value.trim();
    suggestionsContainer.innerHTML = '';

    if (inputValue.length === 0) {
        suggestionsContainer.style.display = 'none';
        return;
    }

    // Make an AJAX request to your RESTful web service
    fetch(`/api/suggestions?input=${inputValue}`)
        .then(response => response.json())
        .then(data => {
            if (data.length === 0) {
                suggestionsContainer.style.display = 'none';
                return;
            }

            data.forEach(suggestion => {
                const suggestionElement = document.createElement('div');
                suggestionElement.className = 'suggestion';
                suggestionElement.textContent = suggestion;

                suggestionElement.addEventListener('click', () => {
                    nameInput.value = suggestion;
                    suggestionsContainer.style.display = 'none';

                    // Redirect to another page, passing the selected suggestion as a query parameter
                    window.location.href = `/employee-details?name=${suggestion}`;
                });

                suggestionsContainer.appendChild(suggestionElement);
            });

            suggestionsContainer.style.display = 'block';
        })
        .catch(error => {
            console.error('Error fetching suggestions:', error);
        });
});

// Close suggestions when clicking outside the input field
document.addEventListener('click', event => {
    if (!nameInput.contains(event.target) && !suggestionsContainer.contains(event.target)) {
        suggestionsContainer.style.display = 'none';
    }
});
