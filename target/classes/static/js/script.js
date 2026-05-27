function showForm(formId) {
    document.querySelectorAll(".form-box").forEach(form => {
        form.classList.remove("active");
    });

    document.getElementById(formId).classList.add("active");
}

function toggleSpecialization() {

    let role = document.getElementById("role").value;
    let box = document.getElementById("specialization-box");

    if(role === "admin") {
        box.style.display = "block";
    } else {
        box.style.display = "none";
    }
}
