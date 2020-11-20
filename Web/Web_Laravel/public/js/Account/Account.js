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
        success: function(result){
            GetAccountInfo(result)
        }
    })
}

// Populate Account Info int div
function GetAccountInfo(retrievedJson) {
   
    $.ajax({
        type: 'GET',
        url: 'GetAccountInfo',
        dataType: "json",
        data: { request : retrievedJson},
        success: function (json) {
            // Just use json.data so we can add more details if we want to.
            if (json.data) {
                $("#AccountInfo").html(json.data).show();
            }
            // Let's give feedback to user that they have gone off route shall we? ;) 
            if (json.error) {
                alert(json.error);
            }
        }
    });
}