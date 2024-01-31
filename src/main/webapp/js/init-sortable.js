function initSortable() {
    const sortableItems = document.querySelectorAll('[data-my-sortable]');
    for (const sortableItem of sortableItems) {
        if (window
            .location
            .search
            .includes(sortableItem.getAttribute('data-my-sortable'))
        ) {
            sortableItem.classList.toggle('invisible');
        }
    }
}

function initSortableSelect() {
    const selects = document.querySelectorAll('.sortable-select')
    selects.forEach((select) => {
        select.addEventListener('change', () => {
            const selectedOption = select.querySelector('option:checked');
            if (selectedOption) {
                window.location.href = selectedOption.getAttribute('data-filter-url');
            }
        });
        const options = select.querySelectorAll('option');
        options.forEach((option) => {
            if (window.location.search.includes(option.value)) {
                option.setAttribute('selected', 'selected');
            }
        });
    });
}

window.addEventListener('load', () => {
    initSortable();
    initSortableSelect();
});