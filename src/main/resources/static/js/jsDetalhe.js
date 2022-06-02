window.onload = function () {
      
    let max = document.getElementById("maxV").value;

    for (var i = 0; i < max; i++) {
        if (i === 0) {
            let firstButton = document.createElement('button');
            firstButton.setAttribute('type', 'button');
            firstButton.setAttribute("data-bs-target", "#carouselExampleCaptions");
            firstButton.setAttribute("data-bs-slide-to", "0");
            firstButton.setAttribute("class", "active");
            firstButton.setAttribute("aria-current", "true");
            firstButton.setAttribute("aria-label", "Slide 1");
            document.getElementById("t").appendChild(firstButton);
        } else {
            let button = document.createElement('button');
            button.setAttribute('type', 'button');
            button.setAttribute("data-bs-target", "#carouselExampleCaptions");
            if (i === max) {
                button.setAttribute("data-bs-slide-to", i);
                button.setAttribute("aria-label", "Slide 0");
            } else {
                button.setAttribute("data-bs-slide-to", i);
                button.setAttribute("aria-label", "Slide " + (i + 1));
            }

            document.getElementById("t").appendChild(button);
        }

    }
};



