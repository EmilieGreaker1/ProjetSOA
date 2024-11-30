document.addEventListener("DOMContentLoaded", () => {
    const user = "User"; // Replace this with the logged-in user's name dynamically
    const userID = 1;
    // TODO We need to get the dynamic username and volunteerID ... idk how, but maybe with the orchestator we can... idk
    // Right now it works, but it's a constant.

    document.getElementById("welcomeMessage").textContent = `Welcome, ${user}`;

    const requestsTableBody = document.querySelector("#requestsTable tbody");
    const missionsTableBody = document.querySelector("#missionsTable tbody");


    /**********************************************************************
     ************************ VIEW MY REQUEST **************************
     ***********************************************************************/

    const requestTitle = document.getElementById("requestTitle");
    const requestDate = document.getElementById("requestDate");
    const requestDescription = document.getElementById("requestDescription");
    const updateRequestButton = document.getElementById("updateRequestButton");
    const markRequestDoneButton = document.getElementById("markRequestDoneButton");

    let selectedRequest = null;

    // Load ALL HELP REQUEST into the left panel table
    async function loadHelpRequest() {
        try {

            const response = await fetch(`http://localhost:8093/request/user+${userID}`);
            if (!response.ok) {
                console.error("Failed to fetch missions:", response.status, response.statusText);
                return;
            }

            const helpRequests = await response.json();
            console.log("All Help Requests API Response:", helpRequests);

            // Clear existing rows
            requestsTableBody.innerHTML = "";

            // Populate table with new rows
            helpRequests.forEach(spontaneous => {
                const row = `<tr data-id="${spontaneous.id}" data-title="${spontaneous.title}" data-date="${spontaneous.date}" data-description="${spontaneous.description}" data-status="${spontaneous.status}">
              <td>${spontaneous.id}</td>
              <td>${spontaneous.title}</td>
              <td>${spontaneous.date}</td>
              <td>${spontaneous.description}</td>
              <td>${spontaneous.status}</td>
              <td>${spontaneous.volunteerId}</td>
            </tr>`;
                requestsTableBody.insertAdjacentHTML("beforeend", row);
            });

            // Agregar evento click a las filas para seleccionar solicitud
            requestsTableBody.querySelectorAll("tr").forEach((row) => {
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

                const response = await fetch(`http://localhost:8093/request/updateRequest`, {
                    method: "PUT",
                    headers: { "Content-Type": "application/json" },
                    body: JSON.stringify(updatedRequest),
                });

                if (response.ok) {
                    alert("Request updated successfully!");
                    loadHelpRequest(); // Recargar la tabla
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

                const response = await fetch(`http://localhost:8093/request/updateStatus`, {
                    method: "PUT",
                    headers: { "Content-Type": "application/json" },
                    body: JSON.stringify(updatedRequest),
                });

                if (response.ok) {
                    alert("Request cancelled successfully!");
                    loadHelpRequest(); // Recargar la tabla
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

    const createHelpRequestButton = document.querySelector("#btn-create-request");

    if (createHelpRequestButton){
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
                userId: userID, // Asegúrate de tener el userID disponible en el script
                volunteerId: 0, // Puedes asignar un valor predeterminado como 0
                title: title,
                description: description,
                status: "pendingAdmin", // Estado inicial
                date: formattedDate,
            };

            try {
                const response = await fetch("http://localhost:8093/request/postRequest", {
                    method: "POST",
                    headers: { "Content-Type": "application/json" },
                    body: JSON.stringify(spontaneousRequest),
                });

                if (response.ok) {
                    alert("Help Request created successfully!");
                    // Recargar las solicitudes para reflejar la nueva solicitud
                    loadHelpRequest();
                } else {
                    alert("Failed to create the Help Request request.");
                }
            } catch (error) {
                console.error("Error creating Help Request:", error);
                alert("Something went wrong. Please try again.");
            }
        });
    }

    if(missionsTableBody && requestsTableBody){
        loadVolunteerAcceptedMissions();
        loadPendingRequests();
    }else if(requestsTableBody){
        loadHelpRequest();
    }else if (requestsTableBody){

    }
});