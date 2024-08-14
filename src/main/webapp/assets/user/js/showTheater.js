document.addEventListener("DOMContentLoaded", function(){
    function showInfo(cityId) {
        document.querySelectorAll('.locations li').forEach(function(elm) {
            elm.classList.remove('selected');
        });

        let selectedElement = document.querySelector(`.locations li[data-city-id="${cityId}"]`);
        if (selectedElement) {
            selectedElement.classList.add('selected');
        }

        fetch(`/cities/${cityId}`)
            .then(response => {
                if (!response.ok) {
                    throw new Error('Network response was not ok');
                }
                return response.json();
            })
            .then(data => {
                let info = document.getElementById('info');
                if (data && data.theaters && data.theaters.length > 0) {
                    let theatersHtml = '<ul>';
                    data.theaters.forEach(theater => {
                        theatersHtml += `<li>${theater.name}</li>`;
                    });
                    theatersHtml += '</ul>';
                    info.innerHTML = theatersHtml;
                } else if (data && (!data.theaters || data.theaters.length === 0)) {
                    info.innerHTML = '<p>No theaters available in this city.</p>';
                } else {
                    info.innerHTML = '<p>City not found.</p>';
                }
            })
            .catch(error => {
                console.error('There was a problem with the fetch operation:', error);
                let info = document.getElementById('info');
                info.innerHTML = '<p>Error fetching city information.</p>';
            });
    }

    document.querySelectorAll('.locations li').forEach(function(li) {
        li.addEventListener('click', function() {
            let cityId = this.getAttribute('data-city-id');
            showInfo(cityId);
        });
    });

}, false);
