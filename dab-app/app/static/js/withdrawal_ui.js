/**
 * @author ABID BUTT Usman
 */

var idleTime = 0;
var correctAmount = true;
var amount ="";

function timerIncrement() {
    idleTime = idleTime + 1;
    if (idleTime >= 1) { // 1 minute
        window.location = "/"
    }
}

function resetAmount(){
    $("#edit-val").text("");
    amount ="";
    $("#alert-amount").text("");
}

function checkAmountPattern(amount){
    if(parseInt(amount)%5!=0){
        console.log("Erreur !");
        $("#alert-amount").text("Merci de choisir un multiple de 5");
        return false;
    }
    else if(parseInt(amount)==0){
        $("#alert-amount").text("Le montant ne peut être nul !");
        return false;
    }
    else if(amount =="") {
        $("#alert-amount").text("Merci de sélectionner un montant valide");
        return false;
    }
    else{
        $("#alert-amount").text("");
        return true;

    }

}

$(document).ready(function(){


    //Increment the idle time counter every minute.
    var idleInterval = setInterval(timerIncrement, 60000); // 1 minute
    console.log(idleTime);
    //Zero the idle timer on mouse movement.
    $(this).mousemove(function (e) {
        idleTime = 0;
        console.log("IdleTime : "+idleTime);
    });
    $(this).keypress(function (e) {
        idleTime = 0;
        console.log("IdleTime : "+idleTime);

    });

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
        else if(response == 403 || response == 401){
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
    $(".btn-val-edit").click(function (e) {
        e.preventDefault();
        console.log("Montant : "+this.value);
        amount = amount + this.value;

        correctAmount  = checkAmountPattern(amount);

        $("#edit-val").text(amount);
    });

    $(".btn-val").click(function (e) {
        e.preventDefault();
        console.log("Montant : "+this.value);
         amount = amount + this.value;
        $("#edit-val").text(this.value);
    });

    $("#other-amount").click(function (e) {
        console.log("Click Ohter!");
        e.preventDefault();
        $(".select-amount").css("display","none");
        $(".edit-amount").css('display', 'initial');
        resetAmount();
        $(".edit-amount").css('text-align', 'center');
        correctAmount = checkAmountPattern(amount);
    });

    $("#correct-input").click(function (e) {
        console.log("Click correct!");
        e.preventDefault();
        resetAmount();

    });

    $("#return-input").click(function (e) {
        console.log("Click Ohter!");
        e.preventDefault();
        $(".edit-amount").css("display","none");
        $(".select-amount").css('display', 'initial');
        // $(".select-amount").css('text-align', 'center');
        resetAmount();
        correctAmount = true;
    });


    $("#cancel-input").click(function (e) {
        console.log("Click cancel !");
        e.preventDefault();
        window.location = "/"
    });

    $(".valid-input").click(function (e) {
        // amount = $('#edit-val').val();
        e.preventDefault();
        console.log("Montant2 : "+this.value);
        console.log("Amount value clicked : "+ amount);
        if(correctAmount){
            //        $(".main-content").hide();
            $(".main-content-select").css('visibility', 'hidden');
            $(".main-content-verif").css('visibility', 'visible');

            var r = $.getJSON("http://localhost:5000" + '/withdrawal_ui/confirme',  {
                card_id: $("#var1").val().split('/')[0],
                pin_id: $("#var2").val().split('/')[0],
                amount: amount.split('€')[0]
            }, function(data) {

                if(data.response == 200){
                    console.log("Mobile pour retirer...")
                    $(".main-content-verif").css('visibility', 'hidden');
                    $(".main-content-conf").css('visibility', 'visible');
                    $("#conf-amount").text(data.amount);
                }
                else if(data.response == 401){
                    console.log("Erreur...")
                    $(".main-content-conf").css('visibility', 'hidden');
                    $(".main-content-verif").css('visibility', 'visible');

                    setTimeout(function(){
                        $(".main-content-verif").css('visibility', 'hidden');
                        $(".main-content-forbidden").css('visibility', 'visible');
                    }, 3000);
                    setTimeout(function(){
                        window.location = "/"
                    }, 14000);

                }

                console.log(data.result);
            });
        }


    });
    console.log("clieck !!!")

});