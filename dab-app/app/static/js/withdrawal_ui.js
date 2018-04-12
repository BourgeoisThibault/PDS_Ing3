/**
 * @author ABID BUTT Usman
 */

$(document).ready(function(){
    console.log("ok withdrawal_ui");
    console.log('Card : '+ $("#var1").val().split('/')[0]);
    console.log('Pin : '+ $("#var2").val().split('/')[0]);



    var socket = io.connect('http://localhost:' + location.port + '/confirm_transac');
    //receive details from server
    socket.on('confirm_transac', function(tcp_resp) {
        console.log("Received conf" + tcp_resp.code);
        var response =tcp_resp.code;
        if(response == 200){
            console.log('OK valid_transac !');

            $(".main-content-conf").css('visibility', 'hidden');
            $(".main-content-verif").css('visibility', 'visible');

            setTimeout(function(){
                $(".main-content-verif").css('visibility', 'hidden');
                $(".main-content-out").css('visibility', 'visible');
            }, 3000);
            setTimeout(function(){
                    window.location = "/"
            }, 15000);

        }
        else if(response == 403){
            $(".main-content-conf").css('visibility', 'hidden');
            $(".main-content-verif").css('visibility', 'visible');

            setTimeout(function(){
                $(".main-content-verif").css('visibility', 'hidden');
                $(".main-content-forbidden").css('visibility', 'visible');
            }, 3000);
            setTimeout(function(){
                window.location = "/"
            }, 15000);
            
            
            console.log("forbidden ! Information do not match !");
        }
        else{
            //console.log("Waiting.... DAB");
        }
    });
    var amount;
    $(".btn-val").click(function (e) {
        e.preventDefault();
        console.log("Montant : "+this.value);
        amount = this.value;
        $("#edit-val").text(this.value);
    });


    $("#valid-input").click(function (e) {
        var bla = $('#edit-val').val();
        e.preventDefault();
        console.log("Montant2 : "+this.value);
        console.log("Amount value clicked : "+ amount);

//        $(".main-content").hide();
        $(".main-content-select").css('visibility', 'hidden');
        $(".main-content-verif").css('visibility', 'visible');

        var r = $.getJSON("http://localhost:5000" + '/withdrawal_ui/_check_data',  {
            card_id: $("#var1").val().split('/')[0],
            pin_id: $("#var2").val().split('/')[0],
            amount: amount.split('€')[0]
        }, function(data) {
            if(data.response = 200){
                console.log("Mobile pour retirer...")
                $(".main-content-verif").css('visibility', 'hidden');
                $(".main-content-conf").css('visibility', 'visible');
                $("#conf-amount").text(data.amount);
            }
            else
                console.log("Erreur...")

            console.log(data.result);
        });

        //setTimeout(function(){ r.abort(); }, 2000);


    });
    console.log("clieck !!!")

});