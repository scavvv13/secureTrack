const email = document.getElementById("email");
const password = document.getElementById("password");
const success = document.getElementById("success");
const error = document.getElementById("error");

form.addEventListener("submit", (event) => {
    event.preventDefault(); // Prevent default form submission

    const loginData = {
        email: email.value,
        password: password.value
    };

    fetch("/api/login", {
        method: "POST",
        body: JSON.stringify(loginData),
        headers: {
            "Content-Type": "application/json"
        }
    })
    .then(res => {
        if (!res.ok) {
            throw new Error("Network response was not ok");
        }
        return res.json();
    })
    .then(data => {
        if (data.status === "error") {
            success.style.display = "none";
            error.style.display = "block";
            error.innerText = data.error;
        } else {
            error.style.display = "none";
            success.style.display = "block";
            success.innerText = data.success;
        }
    })
    .catch(error => {
        console.error("Error during fetch:", error);
        error.style.display = "block";
        error.innerText = "An error occurred during login. Please try again later.";
    });
});
