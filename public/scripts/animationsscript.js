// script.js

// Function to navigate to a new page with smooth transition
function navigateTo(page) {
    // Fade out the content to transition away from
    var content = document.querySelectorAll('.fade-content');
    content.forEach(function(element) {
        element.style.transition = 'opacity 0.5s ease';
        element.style.opacity = 0;
    });

    // Redirect to the specified page after a short delay
    setTimeout(function() {
        window.location.href = page;
    }, 500); // 500 milliseconds for the transition duration
}
// Function to toggle drawer visibility
function toggleDrawer() {
    var drawer = document.getElementById('drawer');
    drawer.style.right = drawer.style.right === '0px' ? '-250px' : '0px';
}

