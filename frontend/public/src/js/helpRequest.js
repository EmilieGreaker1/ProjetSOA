// Mock Data for Requests
const requests = [
    { id: 1, title: "Help with groceries", date: "2024-11-20", description: "I need someone to help me buy groceries.", status: "waiting", volunteer: "John Doe" },
    { id: 2, title: "Medical appointment", date: "2024-11-22", description: "I need a ride to my medical appointment.", status: "in progress", volunteer: "Jane Smith" }
];

// Populate Requests Table
function populateTable() {
    const tableBody = document.querySelector("#table-requests tbody");
    tableBody.innerHTML = ""; // Clear table content
    requests.forEach((request) => {
        const row = document.createElement("tr");
        row.innerHTML = `
            <td>${request.id}</td>
            <td>${request.title}</td>
            <td>${request.date}</td>
            <td>${request.description}</td>
            <td>${request.status}</td>
            <td>${request.volunteer}</td>
        `;
        tableBody.appendChild(row);
    });
}

// Event Listeners
document.getElementById("btn-show-data").addEventListener("click", populateTable);

document.getElementById("btn-create-request").addEventListener("click", (e) => {
    e.preventDefault();
    const title = document.getElementById("title").value;
    const description = document.getElementById("description").value;
    const date = document.getElementById("date").value;

    if (title && description && date) {
        requests.push({ id: requests.length + 1, title, date, description, status: "waiting", volunteer: "N/A" });
        alert("Request created successfully!");
        populateTable();
        document.getElementById("request-form").reset();
    } else {
        alert("Please fill in all fields.");
    }
});

// Set Welcome User
document.getElementById("user-name").textContent = "Alice"; // Replace with dynamic user name