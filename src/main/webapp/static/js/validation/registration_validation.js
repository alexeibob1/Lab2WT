document.addEventListener("DOMContentLoaded", function () {
    // Get the password fields and locale value
    const passwordField = document.getElementById("password");
    const confirmPasswordField = document.getElementById("confirmPassword");
    const locale = document.getElementById("locale").value;

    // Add event listener to form submission
    document.getElementById("reg-form").addEventListener("submit", function (event) {
        // Perform password validation
        if (passwordField.value !== confirmPasswordField.value) {
            // Display the validation message based on the session locale
            const validationMessage = document.getElementById("validationMessage");
            if (locale === "en") {
                validationMessage.innerHTML = "Passwords do not match!";
            } else if (locale === "ru") {
                validationMessage.innerHTML = "Пароли не совпадают!";
            } else {
                validationMessage.innerHTML = "Passwords do not match!";
            }

            event.preventDefault(); // Prevent form submission
        }
    });
});