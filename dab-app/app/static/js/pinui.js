/**
 * @author ABID BUTT Usman
 */




$(document).ready(function(){
    console.log("ok");


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

        $.getJSON("http://localhost:5000" + '/pinui/_check_data', {

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
        return false;


    });
        console.log("clieck !!!")

});