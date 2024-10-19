document.addEventListener("DOMContentLoaded", function () {
    const carouselItems = document.querySelectorAll('.carousel-item');

    // Add zoom-in effect on hover
    carouselItems.forEach(item => {
        item.addEventListener('mouseover', function () {
            const img = this.querySelector('.custom-carousel-img');
            img.style.transform = 'scale(1.05)';
        });

        item.addEventListener('mouseout', function () {
            const img = this.querySelector('.custom-carousel-img');
            img.style.transform = 'scale(1)';
        });
    });
});
