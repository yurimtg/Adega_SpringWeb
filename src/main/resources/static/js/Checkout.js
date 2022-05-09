var check = document.querySelector('#show-or-hide');
var credit = document.querySelector('#credit');

check.addEventListener('click', function(){

    if(credit.style.display === 'block'){
    credit.style.display ='none';
    }else{
        credit.style.display = 'block';
    }


});