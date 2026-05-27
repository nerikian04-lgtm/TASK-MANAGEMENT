document.addEventListener("DOMContentLoaded", function () {

    // ================= SIDEBAR =================
    const menuToggle = document.getElementById("menu-toggle");
    const sidebar = document.getElementById("sidebar");
    const closeBtn = document.getElementById("close-btn");

    if (menuToggle && sidebar) {
        menuToggle.addEventListener("click", () => {
            sidebar.classList.add("active");
        });
    }

    if (closeBtn && sidebar) {
        closeBtn.addEventListener("click", () => {
            sidebar.classList.remove("active");
        });
    }

    // ================= TAB SYSTEM (FIXED) =================
    const buttons = document.querySelectorAll(".tab-menu__button");
    const panels = document.querySelectorAll(".tab-panel");

    function activateTab(index) {

        // safety check (IMPORTANT)
        if (!buttons.length || !panels.length) return;

        buttons.forEach(b => b.classList.remove("is-active"));
        panels.forEach(p => p.classList.remove("is-active"));

        if (buttons[index]) buttons[index].classList.add("is-active");
        if (panels[index]) panels[index].classList.add("is-active");

        // optional loaders (safe calls)
        if (index === 0 && typeof loadHistory === "function") loadHistory();
        if (index === 1 && typeof loadRequest === "function") loadRequest();
        if (index === 2 && typeof loadComplaint === "function") loadComplaint();
    }

    // attach tab clicks safely
    buttons.forEach((btn, index) => {
        btn.addEventListener("click", () => {
            activateTab(index);
        });
    });

    // FORCE FIRST TAB ACTIVE (IMPORTANT FIX)
    activateTab(0);

});