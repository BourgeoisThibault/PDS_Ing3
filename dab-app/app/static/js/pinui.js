/**
 * @author ABID BUTT Usman
 */

$(document).ready(function(){
    console.log("ok pinui");
    console.log('Card : '+ $("#var1").val().split('/')[0]);
    console.log('Pin : '+ $("#var2").val().split('/')[0]);



    var socket = io.connect('http://localhost:' + location.port + '/confirm_transac');
//var numbers_received = [];
    var numbers_received = '';


//receive details from server
    socket.on('confirm_transac', function(msg) {
        console.log("Received conf" + msg.conf);
        var response =msg.conf;
        if(response=="VALID"){
            //socket.disconnect();
            //window.location.href = '/pinui';
            console.log('OK valid_transac !');
            $(".loading").css('visibility', 'visible');

            $.getJSON("http://localhost:5000" + '/pinui/_last_checking', {

            }, function(data) {
                if(data.result = "ok"){
                    console.log("Félicication :...")
                    $(".loading").css('visibility', 'hidden');
                    $(".main-content-conf").hide();
                    $(".main-content-validation").css('visibility', 'visible');
                    setTimeout(function(){
                        console.log("Boom!");
                    }, 2000);
                    $(".loading").css('visibility', 'visible');
                    window.location.href = '/';

                }
                else
                    console.log("Erreur...")

                console.log(data.result);
            });
        }
    });

    $(".btn-val").click(function (e) {
        e.preventDefault();
        console.log("Montant : "+this.value);
        $(".edit-val").text(this.value);
    });


    $("#valid-input").click(function (e) {
        e.preventDefault();
        console.log("Montant2 : "+this.value);
//        $(".main-content").hide();
        $(".main-content-verif").css('visibility', 'visible');
        $(".loading").addClass("modal");



        var r = $.getJSON("http://localhost:5000" + '/pinui/_check_data',  {
            card_id: $("#var1").val().split('/')[0],
            pin_id: $("#var2").val().split('/')[0],
            amount: $(".edit-val").val()
        }, function(data) {
            if(data.result = "ok"){
                console.log("Mobile pour retirer...")
                $(".amount_ui").hide();
                $(".loading").css('visibility', 'hidden');
                $(".main-content-verif").hide();
                $(".main-content-conf").css('visibility', 'visible');
                $(".contact-nfc").css('visibility', 'visible');
                $(".contact-nfc-gif").css('visibility', 'visible');

            }
            else
                console.log("Erreur...")

            console.log(data.result);
        });

        //setTimeout(function(){ r.abort(); }, 2000);


    });
    console.log("clieck !!!")

});