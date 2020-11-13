function generateKey(){
$('#userkey').val(makeid(10));
}

$(document).ready(function() {generateKey();})

function makeid(length) {
    var result           = '';
    var characters       = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789';
    var charactersLength = characters.length;
    for ( var i = 0; i < length; i++ ) {
       result += characters.charAt(Math.floor(Math.random() * charactersLength));
    }
    return result;
 }

 function ViewPassword() {
    var x = document.getElementById("userkey");
    if (x.type === "password") {
      x.type = "text";
    } else {
      x.type = "password";
    }
  }
 
 