"use strict;"
const inp_y = document.getElementById('input-y');

const inp_r1 = document.getElementById('r1');
const inp_r2 = document.getElementById('r2');
const inp_r3 = document.getElementById('r3');
const inp_r4 = document.getElementById('r4');
const inp_r5 = document.getElementById('r5');

const submitButton = document.getElementById('submit-button');
const current_time = document.getElementById('current_time');
const working_time = document.getElementById('working_time');
const table = document.getElementById('check');
const tbody = document.getElementById('results');
var pred_btn = null;


var error_message = "";
var x_value = null;
var y_value = null;
var r_value = null;

/* getting the X value */

const btns = document.querySelectorAll('button[id^=x]')

btns.forEach((btn) => {
    btn.addEventListener("click", (event) => {
        x_value = event.target.value;
        console.log(event.target.value);
        btns.forEach(
            (btnToClean) => {
                btnToClean.classList.remove("active_btn");
            },
        );
        event.target.classList.add("active_btn");
    });
});


/* getting the Y and R values */

function getData() {

    y_value = inp_y.value;
    if (inp_r1.checked) {
        r_value = inp_r1.value;
    }
    if (inp_r2.checked) {
        r_value = inp_r2.value;
    }
    if (inp_r3.checked) {
        r_value = inp_r3.value;
    }
    if (inp_r4.checked) {
        r_value = inp_r4.value;
    }
    if (inp_r5.checked) {
        r_value = inp_r5.value;
    }
}


/* value check*/

function checkX() {
    if (x_value != null) {
        return true;
    }
    else {
        error_message = "X: Не выбрано значение.\n";
        return false;
    }
}


function checkY() {
    if (String(y_value.replace('-', '')).length > 5){
        error_message = "Введите число Y с точностью до 3 знака после запятой";
        return false;
    }
    const value_string = y_value.replace('\,', '.');
    if (!isNaN(value_string)) {
        const value = Number.parseFloat(value_string);
        if (value <= -3 || value >= 5) {
            error_message = "Число Y не попадает в указанный диапазон\n";
            return false;
        }
        else return true;
    }
    else {
        error_message = "Y не число :(";
        return false;
    }
}
function checkR() {
    if (r_value != null) {
        return true;
    }
    else {
        error_message = "R: Не выбрано значение.\n";
        return false;
    }
}


/*response manegment function*/

var Handler = function (request) {
    console.log(request.responseText);
    var response = JSON.parse(request.responseText);
    if (response.valid == "true") {
        updateTable(response);
    }
    else {
        alert("Неправильно введены данные");
    }

}

var restoreHandler = function (request) {
    const array = JSON.parse(request.responseText);
    array.forEach((data) => {
        updateTable(JSON.parse(data));
    });
};


function updateTable(response) {
    var row = document.createElement("tr");
    var cell_x = document.createElement("td");
    var cell_y = document.createElement("td");
    var cell_R = document.createElement("td");
    var cell_hit = document.createElement("td");
    var cell_cur_time = document.createElement("td");
    var cell_work_time = document.createElement("td")

    cell_x.innerHTML = response.x;
    cell_y.innerHTML = response.y;
    cell_R.innerHTML = response.R;
    cell_hit.innerHTML = response.res ? "Попадание" : "Нет попадания";

    datems = (response.current_time * 1000);
    obj = new Date(response.current_time * 1000)
    hours = obj.getHours();
    mins = obj.getMinutes();
    sec = obj.getSeconds();
    cell_cur_time.innerHTML =  hours + ":" + mins + ":" + sec;
    cell_work_time.innerHTML = response.working_time + ' нс'
    row.appendChild(cell_x);
    row.appendChild(cell_y);
    row.appendChild(cell_R);
    row.appendChild(cell_hit);
    row.appendChild(cell_cur_time);
    row.appendChild(cell_work_time);
    tbody.insertBefore(row, tbody.firstChild);
}


/*Sending data to server*/

function sendRequest(r_handler) {
    var r_path = './controller-servlet?x='
        + x_value + '&y='
        + y_value + '&R='
        + r_value;

    var request = new XMLHttpRequest();

    request.open("POST", r_path, true);
    request.responseType = 'text';
    request.setRequestHeader('Content-Type', 'application/x-www-form-url');

    request.addEventListener("readystatechange", () => {
        if (request.readyState === 4 && request.status === 200) {
            r_handler(request);
        }
    });

    request.send();
}


/* "Send" button */

function sendData() {
    error_message = "Неправильно введены данные";
    getData();
    console.log(x_value + ' ' + y_value + ' ' + r_value);

    if (checkX() && checkY() && checkR())
    {
        sendRequest(Handler);
    }
    else
    {
        alert(error_message);
    }
}

submitButton.addEventListener('click', sendData);

function addDots(x, y, r){
    let coordinateX = x > 5 || x < -3? x: x >= 0? 200 + (x * 120)/r: 200 + (x * 120)/r
    let coordinateY = y > 5 || y < -5 ? y:  y >= 0? 140 - (y * 120)/r: 140 - (y * 120)/r
    drawCircle(coordinateX, coordinateY, 1, 0, 2*Math.PI)
}

function resetDots(request){
    if(request !== undefined) {
        request.forEach(function (data){
            let dot = JSON.parse(data)
            addDots(dot.x, dot.y, dot.R)
        })
    }
}

function reload(){
    const path="./controller-servlet?restore"
    const request = new XMLHttpRequest();
    request.open("POST", path, true);
    request.onreadystatechange = () =>{
        if(request.readyState === 4 && request.status === 200){
            console.log("reboot")
        }
    }
    request.send()
}

reload()