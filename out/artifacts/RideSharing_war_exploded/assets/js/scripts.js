$(document).ready(function () {
    var global_oid;
    //Display Modal dynamically
    $('.btn_triggerRequestorModal').click(populateRequestorInfoModal);
    // passing order_id to modal
    $('.btn_confirmOrderModal').click(function(){
        global_oid = $(this).data("id");
    });

    //confirm order
    $('#btn_confirmOrder').click(function(){
        var provComment = $("#provider-comment").val();
        $.get('order',{id: global_oid, providercomment:provComment, action:"confirm"})
            .done(function(data){
                alert(data);
            })
            .fail(function(e){
                $('#confirmModal').find('.modal-body').html("Error fetching data");
                $('#confirmModal').modal('show');
            })
    })

});


// btn_triggerRequestorModal event
function populateRequestorInfoModal(e){
    e.preventDefault();
    var orderid= $(this).data("id");
    $.get('order',{id: orderid, action:"orderinfo"})
        .done(changeModalBody)
        .fail(function(e){
            $('#userDetailModal').find('.modal-body').html("Error fetching data");
            $('#userDetailModal').modal('show');
        })

}

function changeModalBody(data){
     data = JSON.parse(data);
     console.log(data);
     var modal =  $('#userDetailModal');
    modal.find('.modal-body #req_name').text(data.requestorUser.name);
    modal.find('.modal-body #ride_date').text(data.requestorUser.birthday.year +" " + data.requestorUser.birthday.month +" " + data.requestorUser.birthday.dayOfMonth);
    modal.find('.modal-body #ride_address').text(data.requestorUser.address);
    modal.find('.modal-body #ride_phone').text(data.requestorUser.telNum);
    modal.find('.modal-body #ride_email').text(data.requestorUser.userName);
    modal.find('.modal-body #ride_departure').text(data.departure);
    modal.find('.modal-body #ride_destination').text(data.destination);
    modal.find('.modal-body #riderequest_date').text((data.date.year +" " + data.date.month +" " + data.date.dayOfMonth ));
    modal.find('.modal-body #req_comment').text(data.reqComment);
    modal.modal('show');
}

