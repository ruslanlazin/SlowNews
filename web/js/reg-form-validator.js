/**
 * Created by Laz on 29.08.2016.
 */

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
    if (frm.FromEmailAddress.value == "") {
        alert('Email address is required.');
        frm.FromEmailAddress.focus();
        return false;
    }
    if (frm.FromEmailAddress.value.indexOf("@") < 1 || frm.FromEmailAddress.value.indexOf(".") < 1) {
        alert('Please enter a valid email address.');
        frm.FromEmailAddress.focus();
        return false;
    }
    return true;
}

