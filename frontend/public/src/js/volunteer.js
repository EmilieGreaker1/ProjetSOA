document.addEventListener("DOMContentLoaded", () => {
    const userData = JSON.parse(localStorage.getItem('user'));
    const user = userData.nickName;
    const volID = userData.userID;

    document.getElementById("welcomeMessage").textContent = `Welcome, ${user}`;

    const requestsTableBody = document.querySelector("#requestsTable tbody");
    const missionsTableBody = document.querySelector("#missionsTable tbody");
    const spontaneousTableBody = document.querySelector("#spontaneousTable tbody");


    /************************************************************************
     **************************** MY MISSIONS *******************************
     ***********************************************************************/

    // Load waiting requests into the left panel table
    async function loadPendingRequests() {
        try {
            const response = await fetch("http://localhost:8095/orc/allAcceptedSpontaneousHelp");
            // TODO PROBLEM - we need to show all accepted HelpRequests and Spontaneous Requests cuz Volunteers can be a volunteer as well
            const items = await response.json();
            // console.log(`API Response:`, items);

            // Clear existing rows
            requestsTableBody.innerHTML = "";

            // Populate table with new rows
            items.forEach(item => {
                const row = `<tr>
                <td>${item.id}</td>
                <td>${item.title}</td>
                <td>${item.date}</td>
                <td>${item.description}</td>
                <td><button class="btn btn-success btn-postulate" data-id="${item.id}">Postulate</button></td>
            </tr>`;
                requestsTableBody.insertAdjacentHTML("beforeend", row);
            });
            console.log("PARTE 1 - id request: " + "volunteerId: " + volID);

            // Add event listeners for Accept and Reject buttons
            document.querySelectorAll(".btn-postulate").forEach(button => {
                button.addEventListener("click", handlePostulate);
            });

        } catch (error) {
            console.error("Error loading request items:", error);
        }
    }


    // Handle Postulate button click
    async function handlePostulate(event) {
        const requestId = event.target.dataset.id; // Get ID Request
        try {
            // Body Request
            const updatedRequest = {
                id: requestId,
                volunteerId: volID
            };
            console.log("PARTE 2 - id request: " + requestId + "volunteerId: " + volID);

            // Send request PUT
            const putResponse = await fetch(`http://localhost:8095/orc/updateVolunteerSpontHelp`, {
                method: "PUT",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify(updatedRequest),
            });

            if (putResponse.ok) {
                console.log("Postulated successfully!");
                document.getElementById("showRequestsButton").click(); // Refresh table
            } else {
                console.error("Failed to postulate.");
            }
        } catch (error) {
            console.error("Error postulating request:", error);
        }

        loadVolunteerAcceptedMissions(); // update accepted requests
    }

    // Load Accepted volunteer missions into the right panel table
    async function loadVolunteerAcceptedMissions() {
        try {

            const response = await fetch(`http://localhost:8095/orc/allSpontHelpOfVolunteer+${volID}`);
            if (!response.ok) {
                console.error("Failed to fetch missions:", response.status, response.statusText);
                return;
            }

            const missions = await response.json();
            console.log("Accepted Requests API Response:", missions);

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
              <td><button class="btn btn-warning markRequestDoneButton" data-id="${mission.id}">Done</button></td>
            </tr>`;
                missionsTableBody.insertAdjacentHTML("beforeend", row);
            });

            // Add event listeners for Accept and Reject buttons
            document.querySelectorAll(".markRequestDoneButton").forEach(button => {
                button.addEventListener("click", handleMarkDone);
            });

        } catch (error) {
            console.error("Error loading missions:", error);
        }
    }

    // Handle Mark as DONE button click
    async function handleMarkDone(event) {
        const requestId = event.target.dataset.id; // Get ID Request
        try {
            // Body Request
            const updatedRequest = {
                id: requestId,
                status: 'DONE'
            };
            // console.log("PARTE 2 - id request: " + requestId + "volunteerId: " + volID);

            // Send request PUT
            const putResponse = await fetch(`http://localhost:8095/orc/updateStatusSpontHelp`, {
                method: "PUT",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify(updatedRequest),
            });

            if (putResponse.ok) {
                console.log("Marked as DONE successfully!");
                document.getElementById("showRequestsButton").click(); // Refresh table
            } else {
                console.error("Failed to make as DONE.");
            }
        } catch (error) {
            console.error("Error making as DONE request:", error);
        }

        loadVolunteerAcceptedMissions(); // update accepted requests
    }



    /**********************************************************************
    ************************ VIEW MY SPONTANEOUS **************************
    ***********************************************************************/

    const requestTitle = document.getElementById("requestTitle");
    const requestDate = document.getElementById("requestDate");
    const requestDescription = document.getElementById("requestDescription");
    const updateRequestButton = document.getElementById("updateRequestButton");
    const markRequestDoneButton = document.getElementById("markRequestDoneButton");

    let selectedRequest = null;

    // Load ALL Spontaneous volunteer request into the left panel table
    async function loadVolunteerSpontaneousRequest() {
        try {

            const response = await fetch(`http://localhost:8095/orc/allSpontHelpOfVolunteer+${volID}`);
            if (!response.ok) {
                console.error("Failed to fetch missions:", response.status, response.statusText);
                return;
            }

            const spontaneousRequests = await response.json();
            console.log("All Spontaneous Requests API Response:", spontaneousRequests);

            // Clear existing rows
            spontaneousTableBody.innerHTML = "";

            // Populate table with new rows
            spontaneousRequests.forEach(spontaneous => {
                const row = `<tr data-id="${spontaneous.id}" data-title="${spontaneous.title}" data-date="${spontaneous.date}" data-description="${spontaneous.description}" data-status="${spontaneous.status}">
              <td>${spontaneous.id}</td>
              <td>${spontaneous.title}</td>
              <td>${spontaneous.date}</td>
              <td>${spontaneous.description}</td>
              <td>${spontaneous.status}</td>
              <td>${spontaneous.volunteerId}</td>
            </tr>`;
                spontaneousTableBody.insertAdjacentHTML("beforeend", row);
            });

            // Agregar evento click a las filas para seleccionar solicitud
            spontaneousTableBody.querySelectorAll("tr").forEach((row) => {
                row.addEventListener("click", () => {
                    selectRequest(row);
                });
            });
        } catch (error) {
            console.error("Error loading missions:", error);
        }
    }


    // Función para seleccionar una solicitud y cargar sus datos en el panel derecho
    function selectRequest(row) {
        selectedRequest = {
            id: row.dataset.id,
            title: row.dataset.title,
            date: row.dataset.date,
            description: row.dataset.description,
            status: row.dataset.status,
        };

        // Cargar datos en los campos de la derecha
        requestTitle.value = selectedRequest.title;
        requestDate.value = selectedRequest.date;
        requestDescription.value = selectedRequest.description;
    }

    // UPDATE SPONTANEOUS SELECTED
    if(updateRequestButton){
    updateRequestButton.addEventListener("click", async () => {
        if (!selectedRequest) {
            alert("Please select a request to update.");
            return;
        }

        try {
            const updatedRequest = {
                id: selectedRequest.id,
                title: requestTitle.value,
                date: requestDate.value,
                description: requestDescription.value,
                status: "pendingAdmin" // Cambiar a pendingAdmin si está confirmado
            };

            const response = await fetch(`http://localhost:8095/orc/updateSpontHelp`, {
                method: "PUT",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify(updatedRequest),
            });

            if (response.ok) {
                alert("Request updated successfully!");
                loadVolunteerSpontaneousRequest(); // Recargar la tabla
            } else {
                alert("Failed to update the request.");
            }
        } catch (error) {
            console.error("Error updating request:", error);
            alert("Something went wrong. Please try again.");
        }
    });
    }

    // CANCEL SPONTANEOUS SELECTED
    if (markRequestDoneButton){
    markRequestDoneButton.addEventListener("click", async () => {
        if (!selectedRequest) {
            alert("Please select a request to cancel.");
            return;
        }

        const reason = prompt("Enter the reason for cancelling the request:");
        if (!reason) {
            alert("Cancellation cancelled.");
            return;
        }

        try {
            const updatedRequest = {
                id: selectedRequest.id,
                status: "CANCELLED",
                adminComment: reason.trim(),
            };

            const response = await fetch(`http://localhost:8095/orc/updateStatusSpontHelp`, {
                method: "PUT",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify(updatedRequest),
            });

            if (response.ok) {
                alert("Request cancelled successfully!");
                loadVolunteerSpontaneousRequest(); // Recargar la tabla
            } else {
                alert("Failed to cancel the request.");
            }
        } catch (error) {
            console.error("Error cancelling request:", error);
            alert("Something went wrong. Please try again.");
        }
    });
    }




    /************************************************************************
     ************************* CREATE SPONTANEOUS ***************************
     ***********************************************************************/

    const createSpontaneousButton = document.querySelector("#btn-create-request");

    if (createSpontaneousButton){
    document.getElementById("btn-create-request").addEventListener("click", async () => {
        // Obtener valores de los campos del formulario
        const title = document.getElementById("requestTitle").value.trim();
        const dateInput = document.getElementById("requestDate").value;
        const formattedDate = new Date(dateInput).toISOString().split("T")[0];
        const description = document.getElementById("requestDescription").value.trim();

        // Validar campos obligatorios
        if (!title || !formattedDate || !description) {
            alert("All fields are required.");
            return;
        }

        // Construir el objeto para enviar al servidor
        const spontaneousRequest = {
            userId: 0,
            volunteerId: volID,
            title: title,
            description: description,
            status: "pendingAdmin", // Estado inicial
            date: formattedDate,
        };

        try {
            const response = await fetch("http://localhost:8095/orc/postSpontHelp", {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify(spontaneousRequest),
            });

            if (response.ok) {
                alert("Spontaneous request created successfully!");
                // Recargar las solicitudes para reflejar la nueva solicitud
                loadVolunteerSpontaneousRequest();
            } else {
                alert("Failed to create the spontaneous request.");
            }
        } catch (error) {
            console.error("Error creating spontaneous request:", error);
            alert("Something went wrong. Please try again.");
        }
    });
    }

    if(missionsTableBody && requestsTableBody){
        loadVolunteerAcceptedMissions();
        loadPendingRequests();
    }else if(spontaneousTableBody){
        loadVolunteerSpontaneousRequest();
    }else if (requestsTableBody){

    }
});