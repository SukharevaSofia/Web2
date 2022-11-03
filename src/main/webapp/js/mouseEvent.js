document.getElementById("graph").onmouseup = function (event){
    let r = r_value;
    console.log(r_value)
    if((r == 1)||(r==2)||(r==3)||(r==4)||(r==5)){
        let x = (r * (event.offsetX - 200)/120).toFixed(3)
        let y = (r * (140 - event.offsetY)/120).toFixed(3)
        sendRequest(x, y, r)
    }
    else{
        alert("Выберете число R")
    }

}