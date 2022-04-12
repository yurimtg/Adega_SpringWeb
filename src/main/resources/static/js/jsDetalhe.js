window.onload = function () {

    var lblFile = document.getElementById("lblFile");
    var input = document.getElementById("inputFile");

    lblFile.addEventListener("click", function () {
        input.click();
    });
    input.addEventListener("change", function () {
        var nome = "Adicionar Imagem";
        if (input.files.length > 0)
            nome = input.files[0].name;
        lblFile.innerHTML = "<img class='imgAdd' src='/imagem/Add.jpg'>" + nome;
    });

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



