document.addEventListener("DOMContentLoaded", function() {
    $('#exampleModalLong').on('show.bs.modal', function (event) {
        var button = $(event.relatedTarget);
        movieId = button.attr('data-movie-id');
        $('#modalMovieId').val(movieId);
        $.ajax({
            url: 'tickets/modal',
            type: 'GET',
            data: { movieId: movieId },
            success: function(response) {
                $('#exampleModalLong .cboxContent').html(response);
            },
            error: function(xhr, status, error) {
                console.error('Error loading ticket information:', error);
            }
        });
    });
    $(document).on('click', '.toggle-tabs.date li', function() {
        $('.toggle-tabs.date li').removeClass('current');
        $(this).addClass('current');
        var selectedDate = $(this);
        var day = selectedDate.find('strong').text();
        var month = selectedDate.find('span').text();
        var year = (new Date()).getFullYear();

        var dateStr = year + '-' + month + '-' + day;
        var selectedCityId = $('.toggle-tabs.address li.current').data('id');

        loadShowtime(dateStr, selectedCityId);
    });

    $(document).on('click', '.toggle-tabs.address li', function() {
        $('.toggle-tabs.address li').removeClass('current');
        $(this).addClass('current');
        var selectedCityId = $(this).data('id');

        var selectedDate = $('.toggle-tabs.date li.current');
        var day = selectedDate.find('strong').text();
        var month = selectedDate.find('span').text();
        var year = (new Date()).getFullYear();

        var dateStr = year + '-' + month + '-' + day;

        loadShowtime(dateStr, selectedCityId);
    });

    function loadShowtime(dateObj, cityId) {
        var movieId = parseInt($('#modalMovieId').val(), 10);
        console.log($('#modalMovieId').val());
        $.ajax({
            type: 'GET',
            url: 'tickets/modal/showtime',
            data: {
                movieId: movieId,
                date: dateObj,
                cityId: cityId
            },
            success: function(response) {
                $('.toggle-content .select-showtime').html(response);
            },
            error: function() {
                alert('Có lỗi xảy ra. Vui lòng thử lại sau.');
            }
        });
    }
});