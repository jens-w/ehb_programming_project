// ACCOUNT.JS -- ENKEL OP DE ACCOUNT PAGINA GERENDERD // 


$(document).ready(function(){
    GetJsonDummyDataAccount();
})


function GetJsonDummyDataAccount(){

// eerste ajax call, nu word deze data gevoed met dummy data
    // deze data gaat dan normaliter van de API komen 
    $.ajax( {
        type: 'GET',
        dataType: 'json',
        url: 'GetJsonDummyDataAccount',
        success: function(json){
            GetAccountInfo(json)
        }
    })
}

// Populate account info div based on retrieved Json
function GetAccountInfo(retrievedJson) {
   
    $.ajax({
        type: 'GET',
        url: 'GetAccountInfo',
        dataType: "html",
        data: { request : retrievedJson},
        success: function (data) {
                $("#AccountInfo").html(data);        
            
        }
    });
}


