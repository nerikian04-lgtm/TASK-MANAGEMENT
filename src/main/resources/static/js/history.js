function loadHistory() {

    const table = document.querySelector("#historyTable tbody");
    if (!table) return;

    const data = JSON.parse(localStorage.getItem("adminHistoryQueue")) || [];

    table.innerHTML = "";

    data.forEach((ticket, index) => {

        const row = table.insertRow();

        row.insertCell(0).innerText =
            (ticket.type === "Complaint" ? "C-0" : "R-0") + (index + 1);

        row.insertCell(1).innerText = ticket.task;
        row.insertCell(2).innerText = ticket.staff;

        row.insertCell(3).innerHTML =
            ticket.urgency === "High"
                ? "<span class='high-priority'>High</span>"
                : "<span class='low-priority'>Low</span>";

        row.insertCell(4).innerHTML = `
            <select class="status-select">
                <option>Not Started</option>
                <option>In Progress</option>
                <option>Completed</option>
            </select>
        `;

        row.insertCell(5).innerText = ticket.date;

        row.insertCell(6).innerHTML =
            "<span onclick='removeHistory(" + index + ")'>[Remove]</span>";
    });
}