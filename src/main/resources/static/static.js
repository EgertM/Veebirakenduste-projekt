$(".dropDownAd a").click(function(){
    var newVal = $(this).text();
    $(this).parents().find('.adButton').html(newVal);
});

$(document).ready(function() {
    setTimeout(function(){
        $(".imagesFront").show();
    }, 1000);
    refreshPics();
});
/*$.get("/user", function(data) {
    $("#user").html(data.userAuthentication.details.name);
    $(".unauthenticated").hide()
    $(".authenticated").show()
});*/
function refreshPics() {
    setTimeout(function () {
        $('#indexImages').fadeOut('slow').load('index.html').fadeIn('slow').alert("ding-dong");
        refreshPics();
    },500);
}
window.dataLayer = window.dataLayer || [];
function gtag(){dataLayer.push(arguments);}
gtag('js', new Date());

gtag('config', 'UA-128208629-1');
