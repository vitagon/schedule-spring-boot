function changeLang(event) {
	event.preventDefault();
	eraseCookie("user_lang");
	setCookie("user_lang", event.target.dataset.lang, null, "/");
	window.location.reload();
}
function getCookie(cname) {
    let name = cname + "=";
    let cookies = decodeURIComponent(document.cookie).split(";");

    for (let cookie of cookies) {
        while(cookie.charAt(0) == ' ') {
            cookie = cookie.substr(1);
        }

        if (cookie.indexOf(name) == 0) {
            return cookie.substr(name.length, cookie.length);
        }
    }
    return "";
}

function setCookie(cname, cvalue, exdays, path) {
    let cookie = cname + "=" + cvalue + ";";
    if (path != null) {
    	cookie += " Path=" + path;
    }
    if (exdays) {
        let date = new Date();
        date.setTime(date.getTime() + (exdays*24*60*60*1000));
        let expires = " expires=" + date.toUTCString();
        cookie += expires;
    }
    document.cookie = cookie;
}

function eraseCookie(cname) {
    document.cookie = cname + '=; Max-Age=-99999999;';
}