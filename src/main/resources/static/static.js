$(".dropDownAd a").click(function(){
    var newVal = $(this).text();
    $(this).parents().find('.adButton').html(newVal);
});

$(document).ready(function() {
    setTimeout(function(){
        $("#imagesRow").show();
    }, 10000);
});
var logout = function() {
    $.post("/logout", function() {
        $("#user").html('');
        $(".unauthenticated").show();
        $(".authenticated").hide();
    })
    return true;
}
$.get("/user", function(data) {
    $("#user").html(data.userAuthentication.details.name);
    $(".unauthenticated").hide()
    $(".authenticated").show()
});