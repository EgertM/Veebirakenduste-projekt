$(document).ready(function myMap() {

    var myLatLng = {lat: 58.378249, lng: 26.714673};
    var mapProp= {
        center: myLatLng,
        zoom:10
    };
    var map = new google.maps.Map(document.getElementById("googleMap"),mapProp);
    var marker = new google.maps.Marker({
        position: myLatLng,
        map: map,
        title: 'Meie asukoht'
    });
    marker.setMap(map);

});