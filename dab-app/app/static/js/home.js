/**
 * @author ABID BUTT Usman
 */

$(document).ready(function() {
    console.log("ok home");

    var socket = io.connect('http://' + document.domain + ':' + location.port + '/home_pool');
//var numbers_received = [];
    var numbers_received = '';


//receive details from server
    socket.on('newnumber', function (msg) {
        console.log("Received number" + msg.number);
        var response = msg.number;
        if (msg.number == 1) {
            $("#var1").val(msg.card_id);
            $("#var2").val(msg.pin);

            // $(".loading").css('visibility', 'visible');
            // $(".main-content-verif").css('visibility', 'visible');
            // setTimeout(function(){
            //     console.log("waiting....");
            // }, 2000);
            $("#form_info_home").submit();
            //socket.disconnect();
            //window.location.href = '/pinui';
        } else if (msg.number == 2) {
            console.log("Reponse 2");

            $(".main-content-verif").css('visibility', 'visible');
            $(".loading").addClass("modal");
            $(".loading").css('visibility', 'visible');

            setTimeout(function () {
                console.log("waiting....");
            }, 2000);

            $(".main-content-err").css('visibility', 'visible');
            setTimeout(function () {
                console.log("waiting err...");
            }, 2000);

            // $(".main-content-err").css('visibility', 'hidden');


        }


        $(".main-content-verif").css('visibility', 'hidden');
        $(".loading").removeClass("modal");
        $(".loading").css('visibility', 'hidden');


    });
});