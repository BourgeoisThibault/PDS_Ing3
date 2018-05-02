/**
 * @author ABID BUTT Usman
 */

$(document).ready(function() {
    console.log("ok home");


    var socket = io.connect('http://' + document.domain + ':' + location.port + '/home_pool');

//receive details from server
    socket.on('response_card_checking', function (tcp_res) {
        console.log("Received number" + tcp_res.code);
        var response = tcp_res.code;
        if (response == 200) {

            console.log("Reponse error : "+tcp_res.code);
            // $(".main-content-home").css('visibility', 'hidden');
            // $("#verif").css('visibility', 'visible');

            $("#var1").val(tcp_res.card_id);
            $("#var2").val(tcp_res.pin);

            setTimeout(function(){
                console.log("waiting checking is ok....");
                $("#verif").css('visibility', 'hidden');
                $("#check_div").css('visibility', 'visible');
            }, 3000);
            setTimeout(function(){
                console.log("submitting form....");
                $("#form_info_home").submit();
            }, 8000);

            //socket.disconnect();
            //window.location.href = '/pinui';
        } else if (tcp_res.code == 401) {


            console.log("Reponse error : " + tcp_res.code);
            // $(".main-content-home").css('visibility', 'hidden');
            // $("#verif").css('visibility', 'visible');

            //executes instructions after 8 s
            setTimeout(function () {
                $("#verif").css('visibility', 'hidden');
                $("#err_div").css('visibility', 'visible');
                console.log("waiting ok");
            }, 8000);
            console.log("waiting error is ok");
            setTimeout(function () {
                console.log("waiting err...");
                $("#err_div").css('visibility', 'hidden');
                $(".main-content-home").css('visibility', 'visible');
            }, 12000);
        }
        else if (tcp_res.code == 444) {
            $(".main-content-home").css('visibility', 'hidden');
            $("#verif").css('visibility', 'visible');
        }


        // $(".main-content-verif").css('visibility', 'hidden');
        // $(".loading").removeClass("modal");
        // $(".loading").css('visibility', 'hidden');


    });
});