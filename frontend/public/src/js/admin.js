document.addEventListener("DOMContentLoaded", () => {
    const user = "Admin"; // Replace this with the logged-in user's name dynamically
    document.getElementById("welcomeMessage").textContent = `Welcome, ${user}`;

    const requestsTableBody = document.querySelector("#requestsTable-admin tbody");
    const missionsTableBody = document.querySelector("#missionsTable tbody");

    const requestsButton = document.getElementById("showRequestsButton");
    const spontaneousButton = document.getElementById("showSpontaneousButton");

    let view;

    // Verificar si el botón de "Requests" existe y agregar el event listener
    if (requestsButton) {
        requestsButton.addEventListener("click", () => {
            loadWaitingItems("request");
        });
        view = "request"
    } else {
        console.log("Button with ID 'showRequestsButton' not found!");
    }

    // Verificar si el botón de "Spontaneous" existe y agregar el event listener

    if (spontaneousButton) {
        spontaneousButton.addEventListener("click", () => {
            loadWaitingItems("spontaneous");
        });
        view = "spontaneous"
    } else {
        console.log("Button with ID 'showSpontaneousButton' not found!");
    }

    // Load waiting requests into the left panel table
    async function loadWaitingItems(type) {
        try {
            let url;
            if (type === "request") {
                url = "http://localhost:8093/request/allWaitingRequests";
            } else if (type === "spontaneous") {
                url = "http://localhost:8092/spontHelp/allWaitingSpontaneousHelp";
            } else {
                console.error("Unknown type:", type);
                return;
            }

            const response = await fetch(url);
            const items = await response.json();
            console.log(`${type.toUpperCase()} API Response:`, items);

            // Clear existing rows
            requestsTableBody.innerHTML = "";

            // Populate table with new rows
            items.forEach(item => {
                const row = `<tr>
                <td>${item.id}</td>
                <td>${item.title}</td>
                <td>${item.date}</td>
                <td>${item.description}</td>
                <td>${item.status}</td>
                <td>${item.userId || "None"}</td>
                <td><button class="btn btn-success btn-accept" data-id="${item.id}">Accept</button></td>
                <td><button class="btn btn-danger btn-reject" data-id="${item.id}">Reject</button></td>
            </tr>`;
                requestsTableBody.insertAdjacentHTML("beforeend", row);
            });

            // Add event listeners for Accept and Reject buttons
            document.querySelectorAll(".btn-accept").forEach(button => {
                button.addEventListener("click", handleAccept);
            });

            document.querySelectorAll(".btn-reject").forEach(button => {
                button.addEventListener("click", handleReject);
            });
        } catch (error) {
            console.error(`Error loading ${type} items:`, error);
        }
    }

    // Handle Accept button click
    async function handleAccept(event) {
        const requestId = event.target.dataset.id; // Get ID Request
        try {
            // Body Request
            const updatedRequest = {
                id: requestId,
                status: "CONFIRMED",
                adminComment: "CONFIRMED"
            };

            let url; // Define URL según el tipo
            if (view === "request") {
                url = `http://localhost:8093/request/updateStatus`;
            } else if (view === "spontaneous") {
                url = `http://localhost:8092/spontHelp/updateStatus`;
            } else {
                console.error("Unknown type:", type);
                return;
            }

            // Realizar la petición PUT
            const putResponse = await fetch(url, {
                method: "PUT",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify(updatedRequest),
            });

            if (putResponse.ok) {
                console.log("Request accepted successfully!");
                document.getElementById(
                    view === "request" ? "showRequestsButton" : "showSpontaneousButton"
                ).click(); // Actualizar la tabla correspondiente
            } else {
                console.error("Failed to accept the request.");
            }
        } catch (error) {
            console.error("Error accepting request:", error);
        }

        loadVolunteerMissions(view); // Actualizar la tabla de solicitudes confirmadas
    }

    // Handle Reject button click
    async function handleReject(event) {
        const requestId = event.target.dataset.id; // Get the ID of the request

        // Create a popup to collect rejection reason
        const reason = prompt("Enter the reason for rejection:");
        if (!reason) {
            alert("Rejection cancelled.");
            return;
        }

        try {

            const updatedRequest = {
                id: requestId,
                status: "REJECTED",
                adminComment: reason.trim() // Puedes enviar un comentario vacío o personalizado
            };

            let url; // Define URL según el tipo
            if (view === "request") {
                url = `http://localhost:8093/request/updateStatus`;
            } else if (view === "spontaneous") {
                url = `http://localhost:8092/spontHelp/updateStatus`;
            } else {
                console.error("Unknown type:", type);
                return;
            }

            const response = await fetch(url, {
                method: "PUT",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify(updatedRequest),
            });

            if (response.ok) {
                console.log("Request rejected successfully!");
                // alert("Request rejected successfully!");
                if (view === "request") {
                    document.getElementById("showRequestsButton").click(); // Refresh table
                } else {
                    document.getElementById("showSpontaneousButton").click(); // Refresh table
                }
            } else {
                console.error("Failed to reject the request.");
                // alert("Failed to reject the request.");
            }
        } catch (error) {
            console.error("Error rejecting request:", error);
            // alert("Something went wrong. Please try again.");
        }
    }


    // Load volunteer missions into the right panel table
    async function loadVolunteerMissions(type) {
        try {

            let url;
            if (type === "request") {
                url = `http://localhost:8093/request/allAcceptedRequests`;
            } else if (type === "spontaneous") {
                url = `http://localhost:8092/spontHelp/allAcceptedSpontaneousHelp`;
            } else {
                console.error("Unknown type:", type);
                return;
            }

            const response = await fetch(url);
            if (!response.ok) {
                console.error("Failed to fetch missions:", response.status, response.statusText);
                return;
            }

            const missions = await response.json();
            console.log("Missions API Response:", missions);

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

    loadVolunteerMissions(view);
});