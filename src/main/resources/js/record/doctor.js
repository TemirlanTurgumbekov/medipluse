function openDropdown() {
    const list = document.querySelector('.list');
    list.style.display = list.style.display === 'none' ? 'block' : 'none';
}

function selectDoctor(element) {
    const doctorId = element.getAttribute('data-value');
    const doctorName = element.textContent;
    document.getElementById('doctorId').value = doctorId; // Устанавливаем значение скрытого input
    document.querySelector('.current').textContent = doctorName; // Отображаем выбранное имя в заголовке
    document.querySelector('.list').style.display = 'none'; // Закрываем список после выбора
}