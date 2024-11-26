document.addEventListener("DOMContentLoaded", () => {
    const user = "User"; // Replace this with the logged-in user's name dynamically
    document.getElementById("welcomeMessage").textContent = `Welcome, ${user}`;

    const requestsTableBody = document.querySelector("#requestsTable tbody");
    const missionsTableBody = document.querySelector("#missionsTable tbody");

    // Load waiting requests into the left panel table
    document.getElementById("showRequestsButton").addEventListener("click", async () => {
        try {
            const response = await fetch("http://localhost:8088/api/requests/waiting");
            const requests = await response.json();

            // Clear existing rows
            requestsTableBody.innerHTML = "";

            // Populate table with new rows
            requests.forEach(request => {
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
            console.error("Error loading requests:", error);
        }
    });

    // Postulate as a volunteer
    document.getElementById("postulateButton").addEventListener("click", async () => {
        const selectedRow = requestsTableBody.querySelector("tr.selected");
        if (!selectedRow) {
            alert("Please select a request to postulate.");
            return;
        }

        const requestId = selectedRow.cells[0].textContent;
        try {
            await fetch(`http://localhost:8088/api/requests/${requestId}/postulate`, {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify({ volunteer: user }),
            });
            alert("You have been assigned as a volunteer!");
        } catch (error) {
            console.error("Error posting as a volunteer:", error);
            alert("Something went wrong. Please try again.");
        }
    });

    // Load volunteer missions into the right panel table
    async function loadVolunteerMissions() {
        try {
            const response = await fetch(`http://localhost:8088/api/volunteer/missions?user=${user}`);
            const missions = await response.json();

            // Clear existing rows
            missionsTableBody.innerHTML = "";

            // Populate table with new rows
            missions.forEach(mission => {
                const row = `<tr>
          <td>${mission.id}</td>
          <td>${mission.title}</td>
          <td>${mission.date}</td>
          <td>${mission.description}</td>
          <td>${mission.status}</td>
        </tr>`;
                missionsTableBody.insertAdjacentHTML("beforeend", row);
            });
        } catch (error) {
            console.error("Error loading missions:", error);
        }
    }

    loadVolunteerMissions();
});