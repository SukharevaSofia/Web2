<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>

<head>
    <meta charset="utf-8">
    <title>✨🧼🥀👁️‍🗨️❤️‍🔥</title>
    <link rel="stylesheet" href="styles/index.css">
    <link rel="stylesheet" href="styles/button.css">
    <link rel="stylesheet" href="styles/input.css">
    <link rel="stylesheet" href="styles/radio.css">
    <link rel="icon" href="styles/icon.png">
    <script src="js/validator.js" defer></script>
</head>


<body>
    <div class="header-left">Сухарева Софья Р32131</div>
    <div class="header-middle"> <span id ="clock"></span> </div>
    <div class="header-right">Вариант 3167</div>
    <br>
    <br>
    <br>
    <div>
        <canvas id="graph" class="graph" height="280" width="400"> Что-то не так с вашим браузером :( </canvas>
        <script src="js/canvas.js"></script>
    </div>

    <div class="R-select">
        <div class="underlined-Rtext">Изменение R:</div>
        <br>
        <input type="radio" id="r1" name="r" value="1" class="r-button">
        <label for="r1"> 1</label><br>
        <input type="radio" id="r2" name="r" value="2" class="r-button">
        <label for="r2"> 2</label><br>
        <input type="radio" id="r3" name="r" value="3" class="r-button">
        <label for="r3"> 3</label><br>
        <input type="radio" id="r4" name="r" value="4" class="r-button">
        <label for="r4"> 4</label><br>
        <input type="radio" id="r5" name="r" value="5" class="r-button">
        <label for="r5"> 5</label><br>
    </div>

    <div>
        <div class="underlined-text">Изменение X:</div>
        <table>
            <tr class="x-select">
                <td><button class="x-button" role="button" id="x5" name="x" value="5">5</button></td>
                <td ><button class="x-button" role="button" id="x4" name="x" value="4">4</button></td>
                <td ><button class="x-button" role="button" id="x3" name="x" value="3">3</button></td>
            </tr>
            <tr class="x-select">
                <td ><button class="x-button" role="button" id="x2" name="x" value="2">2</button></td>
                <td ><button class="x-button" role="button" id="x1" name="x" value="1">1</button></td>
                <td ><button class="x-button" role="button" id="x0" name="x" value="0">0</button></td>
            </tr>
            <tr class="x-select">
                <td><button class="x-button" role="button" id="x-1" name="x" value="-1">-1</button></td>
                <td><button class="x-button" role="button" id="x-2" name="x" value="-2">-2</button></td>
                <td><button class="x-button" role="button" id="x-3" name="x" value="-3">-3</button></td>
            </tr>
            <tr>
                <td colspan="3" class="underlined-text">Изменение Y:</td>
            </tr>
            <tr class="y-select">
                <td class="Y-text" colspan="3">
                    <input id="input-y" class="Input-text" placeholder="(-3; 5)">
                </td>
            </tr>
        </table>
    </div>
    <br>
    <button id="submit-button" class="submit">Отправить</button>
    <br>
    <div class="result-char">
        <table id="check" class="table_check" align="center">
            <tr class="table_header">
                <th align="left">X</th>
                <th align="left">Y</th>
                <th align="left">R</th>
                <th align="left">Результат</th>
                <th align="left">Текущее время</th>
                <th align="left">работа скрипта</th>
            </tr>
            <div id="result_table_container">
                <tbody id="results">
                </tbody>
            </div>
        </table>
    </div>
</body>
</html>