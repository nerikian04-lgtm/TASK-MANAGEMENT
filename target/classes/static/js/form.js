function toggleCustomIssue() {

    var issueSelect = document.getElementById("issue");
    var customGroup = document.getElementById("custom-issue-group");

    if (!issueSelect || !customGroup) {
        console.log("Issue elements not found.");
        return;
    }

    if (issueSelect.value === "Others") {
        customGroup.style.display = "flex";
    } else {
        customGroup.style.display = "none";
    }
}

function submitTicket() {

    var firstName = document.getElementById("firstname").value.trim();
    var lastName = document.getElementById("lastname").value.trim();
    var email = document.getElementById("emailaddress").value.trim();

    var ticketType = document.getElementById("tickettype").value;
    var urgencyLevel = document.getElementById("urgency").value;
    var itPersonnel = document.getElementById("itpersonnel").value;

    var commonIssue = document.getElementById("issue").value;
    var customIssueText = document.getElementById("customissue").value.trim();

    var fileInput = document.getElementById("attachfile");

    if (firstName === "" || lastName === "" || email === "") {
        alert("Please fill out all required fields.");
        return;
    }

    var finalProblem = commonIssue;

    if (commonIssue === "Others") {

        if (customIssueText === "") {
            alert("Please describe your situation.");
            return;
        }

        finalProblem = customIssueText;
    }

    var now = new Date();
    var currentDate = now.toISOString().split('T')[0];

    fetch("http://localhost:8080/api/tickets", {

        method: "POST",

        headers: {
            "Content-Type": "application/json"
        },

        body: JSON.stringify({

            firstName: firstName,
            lastName: lastName,
            email: email,

            type: ticketType,
            urgency: urgencyLevel,
            staff: itPersonnel,

            task: finalProblem,
            date: currentDate
        })
    })

    .then(response => {

        if (!response.ok) {
            throw new Error("Failed to save ticket.");
        }

        return response.json();
    })

    .then(data => {

        alert("Your form has been submitted successfully!");

        document.getElementById("firstname").value = "";
        document.getElementById("lastname").value = "";
        document.getElementById("emailaddress").value = "";

        document.getElementById("tickettype").value = "Request";
        document.getElementById("urgency").value = "Low";
        document.getElementById("itpersonnel").value = "Support Desk";

        document.getElementById("issue").value = "System Bugging";

        document.getElementById("customissue").value = "";

        document.getElementById("custom-issue-group").style.display = "none";

        fileInput.value = "";
    })

    .catch(error => {

        console.error("Error:", error);

        alert("Failed to submit form.");
    });
}