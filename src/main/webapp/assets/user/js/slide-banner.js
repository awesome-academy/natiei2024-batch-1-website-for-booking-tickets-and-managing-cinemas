document.addEventListener("DOMContentLoaded", function(){
    var thoigian;
    var slides = document.querySelectorAll('.Slide');
    var dots = document.querySelectorAll('.quanlynutchuyen li');
    var currentSlideIndex = 0;


    function showSlide(index) {
        if (index >= slides.length) {
            currentSlideIndex = 0;
        } else if (index < 0) {
            currentSlideIndex = slides.length - 1;
        } else {
            currentSlideIndex = index;
        }

        slides.forEach(function(slide) {
            slide.classList.add('AnSlide');
            slide.classList.remove('cdSlide');
            slide.classList.remove('active');
        });

        dots.forEach(function(dot) {
            dot.classList.remove('kichhoat');
        });

        slides[currentSlideIndex].classList.remove('AnSlide');
        slides[currentSlideIndex].classList.add('cdSlide');
        slides[currentSlideIndex].classList.add('active');
        dots[currentSlideIndex].classList.add('kichhoat');
    }


    function autoSlide() {
        clearInterval(thoigian);
        thoigian = setInterval(function() {
            currentSlideIndex++;
            showSlide(currentSlideIndex);
        }, 4000);
    }


    for (var i = 0; i < dots.length; i++) {
        (function(index) {
            dots[index].addEventListener("click", function() {
                currentSlideIndex = index;
                showSlide(currentSlideIndex);
                clearInterval(thoigian);
                autoSlide();
            });
        })(i);
    }


    document.querySelector('.prev').addEventListener('click', function() {
        currentSlideIndex--;
        showSlide(currentSlideIndex);
        clearInterval(thoigian);
        autoSlide();
    });


    document.querySelector('.next').addEventListener('click', function() {
        currentSlideIndex++;
        showSlide(currentSlideIndex);
        clearInterval(thoigian);
        autoSlide();
    });


    autoSlide();
    showSlide(currentSlideIndex);
},false)
