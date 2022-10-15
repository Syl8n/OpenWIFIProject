function onSuccess({coords, timeStamp}){
    const lat = coords.latitude;
    const lnt = coords.longitude;

    document.getElementById("lat").value = lat;
    document.getElementById("lnt").value = lnt;
}

function getUserLocation(){
    if(!navigator.geolocation){
        throw "위치 정보를 받을 수 없습니다";
    }
    navigator.geolocation.getCurrentPosition(onSuccess);
}

function onDelete(){

}