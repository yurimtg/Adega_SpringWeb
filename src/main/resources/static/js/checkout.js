
function metodoPagamento () {

    var credit = document.getElementById("boleto");
    var boleto = document.getElementById("credit");
    var metodoPagamento = document.getElementById("Pagamento");

    if (document.getElementById("rboCartao").checked) {
        credit.style.display = 'none';
        boleto.style.display = 'block';
        metodoPagamento.value = "cartao";
    } 
    if (document.getElementById("rboBoleto").checked) {
        credit.style.display = 'block';
        boleto.style.display = 'none';
        metodoPagamento.value = "boleto";
    }


};