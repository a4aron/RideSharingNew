$(function () {
    $('#update_profile').on('click', doRequest);

    $('#form_neworder').on('submit', makeOrder);
});

function makeOrder() {
    event.preventDefault();
    $.ajax("/order", { "type": "POST",
        "data": {
            "departure": $('#departure').val(),
            "destination": $('#destination').val(),
            "date": $('#date_input').val(),
            "comment": $('#comment').val()
        }
    }).done(updateProfileSucess)
        .fail(updateProfileFail);
}

function doRequest() {
    $.ajax("/updateInfo", { "type": "POST",
        "data": {
            "name": $('#user_name').val(),
            "address": $('#user_address').val(),
            "email": $('#user_email').val(),
            "tel": $('#user_tel').val()
        }
    }).done(updateProfileSucess)
        .fail(updateProfileFail);
}

function updateProfileSucess() {
    $.notify({
        icon: 'pe-7s-gift',
        message: "Update Successful!"

    }, {
        type: 'info',
        timer: 500
    });
}

function updateProfileFail() {

}