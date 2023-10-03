const phoneInput = document.getElementById('phone-input');
const suggestionsContainer = document.getElementById('suggestions-container');

// Sample suggestion data (replace with your own data)
const suggestions = [
    'Booking 123456',
    'Booking 789012',
    'Booking 345678',
    'Booking 901234',
];

phoneInput.addEventListener('input', () => {
    const inputValue = phoneInput.value.trim();
    suggestionsContainer.innerHTML = '';

    if (inputValue.length === 0) {
        suggestionsContainer.style.display = 'none';
        return;
    }

    const filteredSuggestions = suggestions.filter(suggestion =>
        suggestion.toLowerCase().includes(inputValue.toLowerCase())
    );

    if (filteredSuggestions.length === 0) {
        suggestionsContainer.style.display = 'none';
        return;
    }

    filteredSuggestions.forEach(suggestion => {
        const suggestionElement = document.createElement('div');
        suggestionElement.className = 'suggestion';
        suggestionElement.textContent = suggestion;

        suggestionElement.addEventListener('click', () => {
            phoneInput.value = suggestion;
            suggestionsContainer.style.display = 'none';
        });

        suggestionsContainer.appendChild(suggestionElement);
    });

    suggestionsContainer.style.display = 'block';
});

// Close suggestions when clicking outside the input field
document.addEventListener('click', event => {
    if (!phoneInput.contains(event.target) && !suggestionsContainer.contains(event.target)) {
        suggestionsContainer.style.display = 'none';
    }
});