// ACCOUNT.JS -- ENKEL OP DE ACCOUNT PAGINA GERENDERD // 


// Change hash for page-reload
$('.nav-tabs a').on('shown', function (e) {
    window.location.hash = e.target.hash.replace("#", "#" + prefix);
});

$(document).ready(function () {
    // Javascript to enable link to tab and open the correct one on load
    var hash = document.location.hash;
    var prefix = "tab_";
    if (hash) {
        $('.nav-tabs a[href="' + hash.replace(prefix, "") + '"]').tab('show');
    } else {
        $('a[href="#AccountInfoTab"]').tab('show');
    }
})
