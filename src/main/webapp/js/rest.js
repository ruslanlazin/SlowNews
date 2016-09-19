function imgchange(obj) {

    if (obj.name === "remove") {


        var xmlhttp = getXmlHttp(); // Создаём объект XMLHTTP
        xmlhttp.open('POST', '/api/removePersonalNews', true); // Открываем асинхронное соединение
        xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded'); // Отправляем кодировку
        xmlhttp.send("newsUri=" + encodeURIComponent(obj.id)); // Отправляем PUT-запрос
        xmlhttp.onreadystatechange = function () { // Ждём ответа от сервера
            if (xmlhttp.readyState == 4) { // Ответ пришёл
                alert(xmlhttp.responseText);
                if (xmlhttp.status == 200) { // Сервер вернул код 200 (что хорошо)
                    obj.src = "/images/favorites-add.jpg";
                    obj.name = "add";
                    obj.title = "add this news to your personal archive";
                }
            }
            xmlhttp.close();
        };


    } else if (obj.name === "add") {

        var xmlhttp = getXmlHttp(); // Создаём объект XMLHTTP
        xmlhttp.open('POST', '/api/addPersonalNews', true); // Открываем асинхронное соединение
        xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded'); // Отправляем кодировку
        xmlhttp.send("newsUri=" + encodeURIComponent(obj.id)); // Отправляем PUT-запрос
        xmlhttp.onreadystatechange = function () { // Ждём ответа от сервера
            if (xmlhttp.readyState == 4) { // Ответ пришёл
                alert(xmlhttp.responseText);
                if (xmlhttp.status == 200) { // Сервер вернул код 200 (что хорошо)
                    obj.src = "/images/favorites-remove.jpg";
                    obj.name = "remove";
                    obj.title = "remove this news from your personal archive";
                }
            }
            xmlhttp.close();
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
