$(".dropDownAd a").click(function(){
    var newVal = $(this).text();
    $(this).parents().find('.adButton').html(newVal);
});
$()