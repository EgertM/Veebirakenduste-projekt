$(".dropDownAd a").click(function(){
    var newVal = $(this).text();
    $(this).parents().find('.adButton').html(newVal);
});

$(document).ready(function() {
    setTimeout(function(){
        $("#imagesRow").show();
    }, 10000);
});
/*$.get("/user", function(data) {
    $("#user").html(data.userAuthentication.details.name);
    $(".unauthenticated").hide()
    $(".authenticated").show()
});*/

window.dataLayer = window.dataLayer || [];
function gtag(){dataLayer.push(arguments);}
gtag('js', new Date());

gtag('config', 'UA-128208629-1');

$(document).ready(function myMap() {
    var mapProp= {
        center:new google.maps.LatLng(58.379058,26.722544),
        zoom:10
    };
    var map = new google.maps.Map(document.getElementById("googleMap"),mapProp);
});