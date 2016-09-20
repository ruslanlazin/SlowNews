(function () {

    addAll = function () {
        var messageBox = document.createElement('div');
        messageBox.id = "messages";
        if (document.body.firstChild) document.body.insertBefore(messageBox, document.body.firstChild);
        else document.body.appendChild(messageBox);
    }

    if (document.body == null) return addLoadEvent(function () {
        addAll();
    });
    addAll();
})();

message = function (mtext, mtype, howlong) {

    var mtype = mtype || 'message';
    var howlong = howlong || 5000;

    if (document.getElementById('messages') == null) {
        setTimeout(function () {
            message(mtext, mtype, howlong)
        }, 100);
        return;
    }

    var alarm = document.createElement('div');
    alarm.className = 'curved ' + mtype;
    alarm.innerHTML = mtext;

    alarm.onclick = function () {
        alarm.style.display = "none";
    };

    alarm.del = function () {
        document.getElementById('messages').removeChild(alarm);
    };

    document.getElementById('messages').appendChild(alarm);
    setTimeout(alarm.del, howlong);
}

error = function (mtext, howlong) {
    var howlong = howlong || 10000;
    message(mtext, "error", howlong);
}

warning = function (mtext, howlong) {
    var howlong = howlong || 7000;
    message(mtext, "warning", howlong);
}


function changeStatus(obj) {

    if (obj.name === "remove") {

        var xmlhttp = getXmlHttp(); // Создаём объект XMLHTTP
        xmlhttp.open('POST', '/api/removePersonalNews', true); // Открываем асинхронное соединение
        xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded'); // Отправляем кодировку
        xmlhttp.send("newsUri=" + encodeURIComponent(obj.id)); // Отправляем PUT-запрос
        xmlhttp.onreadystatechange = function () { // Ждём ответа от сервера
            if (xmlhttp.readyState == 4) { // Ответ пришёл
                if (xmlhttp.status == 200) { // Сервер вернул код 200 (что хорошо)
                    obj.src = '/images/favorites-add.jpg';
                    obj.name = 'add';
                    obj.title = 'add this news to your personal archive';
                    var s = xmlhttp.responseText;
                    location.reload(true);
                    setTimeout (message(s), 100);

                }
            } else if (xmlhttp.status == 401) { // Сервер вернул код 401 (неавторизован)
                warning(xmlhttp.responseText);
            } else if (xmlhttp.status == 404) { // Сервер вернул код 404 (нет такой новости)
                error(xmlhttp.responseText);

            }
        };


    } else if (obj.name === "add") {

        var xmlhttp = getXmlHttp(); // Создаём объект XMLHTTP
        xmlhttp.open('POST', '/api/addPersonalNews', true); // Открываем асинхронное соединение
        xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded'); // Отправляем кодировку
        xmlhttp.send("newsUri=" + encodeURIComponent(obj.id)); // Отправляем PUT-запрос
        xmlhttp.onreadystatechange = function () { // Ждём ответа от сервера
            if (xmlhttp.readyState == 4) { // Ответ пришёл
                if (xmlhttp.status == 200) { // Сервер вернул код 200 (что хорошо)
                    message(xmlhttp.responseText);
                    obj.src = '/images/favorites-remove.jpg';
                    obj.name = 'remove';
                    obj.title = 'remove this news from your personal archive';
                } else if (xmlhttp.status == 401) { // Сервер вернул код 401 (неавторизован)
                    warning(xmlhttp.responseText);
                } else if (xmlhttp.status == 404) { // Сервер вернул код 404 (нет такой новости)
                    error(xmlhttp.responseText);
                }
            }
        };
    }
}

/* Данная функция создаёт кроссбраузерный объект XMLHTTP */
function getXmlHttp() {
    var xmlhttp;
    try {
        xmlhttp = new ActiveXObject("Msxml2.XMLHTTP");
    } catch (e) {
        try {
            xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
        } catch (E) {
            xmlhttp = false;
        }
    }
    if (!xmlhttp && typeof XMLHttpRequest != 'undefined') {
        xmlhttp = new XMLHttpRequest();
    }
    return xmlhttp;
}