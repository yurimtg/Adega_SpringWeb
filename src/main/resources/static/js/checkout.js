var check = document.querySelector('#show-or-hide');
var credit = document.querySelector('#credit');

check.addEventListener('click', function(){

    if(credit.style.display === 'block'){
    credit.style.display ='none';
    }else{
        credit.style.display = 'block';
    }


})

function onlynumber(evt) {
   var theEvent = evt || window.event;
   var key = theEvent.keyCode || theEvent.which;
   key = String.fromCharCode( key );
   //var regex = /^[0-9.,]+$/;
   var regex = /^[0-9.]+$/;
   if( !regex.test(key) ) {
      theEvent.returnValue = false;
      if(theEvent.preventDefault) theEvent.preventDefault();
   }
};