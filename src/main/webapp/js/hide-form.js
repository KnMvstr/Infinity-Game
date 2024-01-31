
function initFormProfile() {
    const button = document.querySelector("[data-hide-show-button]");
    if (button) {
        const dataAttr = button.getAttribute("data-hide-show-button");
        const container = document.querySelector("[data-hide-show-container='"+dataAttr+"']");
        if (container) {
            button.addEventListener('click', () => {
                container.classList.toggle("d-none");
            });
        }
    }
}

window.addEventListener('load', () => {
    initFormProfile();
});