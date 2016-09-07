
document.getElementById('reg-form').onsubmit = ValidateForm;

function ValidateForm() {

    frm = document.getElementById('reg-form');

    if (frm.Username.value == "") {
        alert('Username is required.');
        frm.Username.focus();
        return false;
    }
    if (frm.Password.value == "") {
        alert('Password is required.');
        frm.Password.focus();
        return false;
    }
    if (frm.Email.value == "") {
        alert('Email address is required.');
        frm.Email.focus();
        return false;
    }
    if (frm.Email.value.indexOf("@") < 1 || frm.Email.value.indexOf(".") < 1) {
        alert('Please enter a valid email address.');
        frm.Email.focus();
        return false;
    }
    return true;
}

