document.addEventListener("DOMContentLoaded", () => {
    const user = "User"; // Replace with dynamic user data
    document.getElementById("welcomeMessage").textContent = `Welcome, ${user}`;

    const requestsTableBody = document.querySelector("#requestsTable tbody");
    const requestTitleInput = document.getElementById("requestTitle");
    const requestDateInput = document.getElementById("requestDate");
    const requestDescriptionInput = document.getElementById("requestDescription");

    // Show all requests
    document.getElementById("showRequestsButton").addEventListener("click", async () => {
        try {
            const response = await fetch("http://localhost:8088/api/requests");
            const requests = await response.json();

            // Clear table
            requestsTableBody.innerHTML = "";

            // Populate with new data
            requests.forEach((request) => {
                const row = `<tr>
          <td>${request.id}</td>
          <td>${request.title}</td>
          <td>${request.date}</td>
          <td>${request.description}</td>
          <td>${request.status}</td>
          <td>${request.volunteer || "None"}</td>
        </tr>`;
                requestsTableBody.insertAdjacentHTML("beforeend", row);
            });
        } catch (error) {
            console.error("Error fetching requests:", error);
        }
    });

    // Handle form submission
    document.getElementById("helpRequestForm").addEventListener("submit", async (event) => {
        event.preventDefault();

        const newRequest = {
            title: requestTitleInput.value,
            date: requestDateInput.value,
            description: requestDescriptionInput.value,
        };

        try {
            await fetch("http://localhost:8088/api/requests", {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify(newRequest),
            });
            alert("Request created successfully!");
            requestTitleInput.value = "";
            requestDateInput.value = "";
            requestDescriptionInput.value = "";
        } catch (error) {
            console.error("Error creating request:", error);
        }
    });
});