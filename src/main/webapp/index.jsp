
<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.Collection" %>
<%@ page import="beans.TableRowBean" %>
<%@ page import="beans.TableBean" %>
<html>

<head>
    <meta charset="utf-8">
    <title>✨🧼🥀👁️‍🗨️❤️‍🔥</title>
    <link rel="stylesheet" href="styles/index.css">
    <link rel="icon" href="styles/icon.png">
    <script src="js/validator.js" defer></script>
</head>


<body>
        <div class="boxheaderl" colspan="2">Вариант 3167</div>
        <div class="boxheaderr" colspan="1">Сухарева Софья, Р32131</div>
        <div>
            <canvas id="graph" class="graph" height="280" width="400">Что-то не так с вашим браузером :(</canvas>
            <script src="js/canvas.js"></script>
        </div>
        <div class="x-select">
            Изменение X:
            <table class="x-select" id="x-select">
                <tr>
                    <td><button class="x-button" id="x5" name="x" value="5">5</button></td>
                    <td><button class="x-button" id="x4" name="x" value="4">4</button></td>
                    <td><button class="x-button" id="x3" name="x" value="3">3</button></td>
                </tr>
                <tr>
                    <td><button class="x-button" id="x2" name="x" value="2">2</button></td>
                    <td><button class="x-button" id="x1" name="x" value="1">1</button></td>
                    <td><button class="x-button" id="x0" name="x" value="0">0</button></td>
                </tr>
                <tr>
                    <td><button class="x-button" id="x-1" name="x" value="-1">-1</button></td>
                    <td><button class="x-button" id="x-2" name="x" value="-2">-2</button></td>
                    <td><button class="x-button" id="x-3" name="x" value="-3">-3</button></td>
                </tr>
            </table>
        </div>
    </tr>
    <div class="y-select">
            <label for="input-y">Изменение Y:</label>
            <div class="Y-text" id="Y">
                <input id="input-y" placeholder="(-3; 5)">
            </div>
    </div>
        <div class="inputz">Изменение R:<br>
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
        <button id="submit-button">Отправить</button>
    </div>
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