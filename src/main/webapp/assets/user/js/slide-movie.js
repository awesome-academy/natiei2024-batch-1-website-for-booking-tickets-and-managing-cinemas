document.addEventListener("DOMContentLoaded", function(){
    const bannersContainer = document.querySelector('.banners');
    const prevButton = document.getElementById('prev');
    const nextButton = document.getElementById('next');

    const bannerWidth = document.querySelector('.banner').offsetWidth + 10; // Width + margin
    const visibleBanners = 4;
    const totalBanners = document.querySelectorAll('.banner').length;

    let currentIndex = 0;

    function updateBannerPosition() {
        const offset = -currentIndex * bannerWidth;
        bannersContainer.style.transform = `translateX(${offset}px)`;
    }

    prevButton.addEventListener('click', () => {
        if (currentIndex > 0) {
            currentIndex--;
            updateBannerPosition();
        }
    });

    nextButton.addEventListener('click', () => {
        if (currentIndex < totalBanners - visibleBanners) {
            currentIndex++;
            updateBannerPosition();
        }
    });
    // Initial display
    updateBannerPosition();

},false)