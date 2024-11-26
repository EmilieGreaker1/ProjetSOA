document.addEventListener("DOMContentLoaded", () => {
    const user = "User"; // Replace with dynamic user data
    document.getElementById("welcomeMessage").textContent = `Welcome, ${user}`;

    const requestsTableBody = document.querySelector("#requestsTable tbody");
    const requestTitleInput = document.getElementById("requestTitle");
    const requestDateInput = document.getElementById("requestDate");
    const requestDescriptionInput = document.getElementById("requestDescription");
    let selectedRequestId = null;
    let selectedRequestStatus = null;

    // Load user requests
    document.getElementById("showRequestsButton").addEventListener("click", async () => {
        try {
            const response = await fetch(`http://localhost:8088/api/requests?user=${user}`);
            const requests = await response.json();

            // Clear existing rows
            requestsTableBody.innerHTML = "";

            // Populate table with new rows
            requests.forEach(request => {
                const row = `<tr data-id="${request.id}" data-status="${request.status}">
          <td>${request.id}</td>
          <td>${request.title}</td>
          <td>${request.date}</td>
          <td>${request.description}</td>
          <td>${request.status}</td>
          <td>${request.volunteer || "None"}</td>
        </tr>`;
                requestsTableBody.insertAdjacentHTML("beforeend", row);
            });

            // Add row click event
            document.querySelectorAll("#requestsTable tbody tr").forEach(row => {
                row.addEventListener("click", () => {
                    selectedRequestId = row.getAttribute("data-id");
                    selectedRequestStatus = row.getAttribute("data-status");
                    requestTitleInput.value = row.children[1].textContent;
                    requestDateInput.value = row.children[2].textContent;
                    requestDescriptionInput.value = row.children[3].textContent;
                });
            });
        } catch (error) {
            console.error("Error loading requests:", error);
        }
    });

    // Update request
    document.getElementById("updateRequestButton").addEventListener("click", async () => {
        if (!selectedRequestId) {
            alert("Please select a request to update.");
            return;
        }

        const updatedRequest = {
            id: selectedRequestId,
            title: requestTitleInput.value,
            description: requestDescriptionInput.value,
        };

        try {
            await fetch(`http://localhost:8088/api/requests/${selectedRequestId}`, {
                method: "PUT",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify(updatedRequest),
            });
            alert("Request updated successfully!");
        } catch (error) {
            console.error("Error updating request:", error);
        }
    });

    // Mark request as done
    document.getElementById("markRequestDoneButton").addEventListener("click", async () => {
        if (!selectedRequestId) {
            alert("Please select a request to mark as done.");
            return;
        }

        if (selectedRequestStatus === "done") {
            alert("This request is already marked as done.");
            return;
        }

        try {
            await fetch(`http://localhost:8088/api/requests/${selectedRequestId}/done`, {
                method: "POST",
            });
            alert("Request marked as done!");
        } catch (error) {
            console.error("Error marking request as done:", error);
        }
    });
});