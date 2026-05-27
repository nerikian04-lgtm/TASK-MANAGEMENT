function loadRequest() {

    const table = document.querySelector("#requestTable tbody");
    if (!table) return;

    const data = JSON.parse(localStorage.getItem("adminHistoryQueue")) || [];

    table.innerHTML = "";

    const requests = data.filter(t => t.type === "Request");

    requests.forEach((t, i) => {

        const row = table.insertRow();

        row.insertCell(0).innerText = "R-0" + (i + 1);
        row.insertCell(1).innerText = t.task;
        row.insertCell(2).innerText = t.staff;

        row.insertCell(3).innerHTML =
            t.urgency === "High"
                ? "<span class='high-priority'>High</span>"
                : "<span class='low-priority'>Low</span>";

        row.insertCell(4).innerHTML = "<input type='file'>";
        row.insertCell(5).innerText = t.date;

        row.insertCell(6).innerHTML =
            "<span onclick='removeRequest(" + i + ")'>[Remove]</span>";
    });
}