
const inp_y = document.getElementById('input-y');
const tbody = document.getElementById('results');
const rButtons = document.querySelectorAll(".r-button");

//time
setInterval(() => {
    document.getElementById("clock").innerHTML = (new Date())
        .toLocaleString("ru-RU", { dateStyle: "full", timeStyle: "medium" });
}, 1000);

let error_message = "";
let x_value = null;
let y_value = null;
const r_value = () => {
    let value = null;
    rButtons.forEach(
        (button) => {
            if (button.checked) {value = button.value;}
        }
    );
    return value;
};

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

rButtons.forEach((btn) => {
    btn.addEventListener("click", (event) => {
        console.log(event.target.value);
    });
})

function checkR() {
    if (r_value() != null) {
        return true;
    }
    else {
        error_message = "R: Не выбрано значение.\n";
        return false;
    }
}


/*response manegment & dots function*/
function addDot(x, y, r, matched) {
  const ctx = document.getElementById("graph").getContext("2d");
  ctx.fillStyle = matched ? "green" : "red";
  ctx.fillRect(200 + (x * 120) / r, 140 - (y * 120) / r, 2, 2);
}
const Handler = function (request) {
    updateTable(JSON.parse(request.responseText));
    /*if(checkX()&&checkY()&&checkR()){
        updateTable(JSON.parse(request.responseText));
    }else {
        alert("Значения не соответствуют требованиям!");
    }*/
};

const restoreHandler = function (request) {
    const array = JSON.parse(request.responseText);
    array.forEach((data) => {
        updateTable(data);
    });
};


function updateTable(response) {
    const row = document.createElement("tr");
    const cell_x = document.createElement("td");
    const cell_y = document.createElement("td");
    const cell_R = document.createElement("td");
    const cell_hit = document.createElement("td");
    const cell_cur_time = document.createElement("td");
    const cell_work_time = document.createElement("td")

    cell_x.innerHTML = response.dataX;
    cell_y.innerHTML = response.dataY;
    cell_R.innerHTML = response.dataR;
    cell_hit.innerHTML = response.result ? "попадание" : "промах";
    cell_cur_time.innerHTML = (new Date(response.currentTime)).toLocaleTimeString("ru-RU");
    cell_work_time.innerHTML = response.workingTime + " нс";

    row.appendChild(cell_x);
    row.appendChild(cell_y);
    row.appendChild(cell_R);
    row.appendChild(cell_hit);
    row.appendChild(cell_cur_time);
    row.appendChild(cell_work_time);
    tbody.append(row, tbody.lastChild);

    addDot(response.dataX, response.dataY, response.dataR, response.result);
}


/*Sending data to server*/
function sendRequest(r_handler) {
    const r_path = './controller-servlet?x='
        + x_value + '&y='
        + y_value + '&R='
        + r_value();

    const request = new XMLHttpRequest();

    request.open("POST", r_path, true);
    request.responseType = 'text';
    request.setRequestHeader('Content-Type', 'application/x-www-form-url');

    request.addEventListener("readystatechange", () => {
        if (request.readyState === 4 && request.status === 200) {
            Handler(request);
        }
    });

    request.send();
}


/* "Send" button */

function sendData() {
    error_message = "Неправильно введены данные";
    getData();
    console.log(x_value + ' ' + y_value + ' ' + r_value());

    if (checkX() && checkY() && checkR())
    {
        sendRequest(Handler);
    }
    else
    {
        alert(error_message);
    }
}

/*
function addDots(x, y, r){
    let coordinateX = x > 5 || x < -3? x: 200 + (x * 120)/r
    let coordinateY = y > 5 || y < -5 ? y: 140 - (y * 120)/r
    drawCircle(coordinateX, coordinateY, 1, 0, 2*Math.PI)
}

function resetDots(request){
    if(request !== undefined) {
        request.forEach(function (data){
            let dot = JSON.parse(data)
            addDots(dot.x, dot.y, dot.r)
        })
    }
}*/

function reload(){
    const path="./controller-servlet?restore"
    const request = new XMLHttpRequest();
    request.open("POST", path, true);
    request.onreadystatechange = () =>{
        if(request.readyState === 4 && request.status === 200){
            restoreHandler(request);
        }
    }
    request.send()
}
reload();

document.getElementById('submit-button').addEventListener('click', sendData);
document.getElementById("graph").onmouseup = function (event){
    const r = r_value();
    console.log(r);
    if(r != null){
        let x = (r * (event.offsetX - 200)/120).toFixed(3)
        let y = (r * (140 - event.offsetY)/120).toFixed(3)
        x_value=x;
        y_value=y;
        console.log(x, y, r);
        sendRequest();
    }
    else{
        alert("Выберете число R")
    }
}
