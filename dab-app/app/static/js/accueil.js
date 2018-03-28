/**
 * @author ABID BUTT Usman
 */

var socket = io.connect('http://' +  document.domain + ':' + location.port + '/test');
//var numbers_received = [];
var numbers_received = '';


//receive details from server
socket.on('newnumber', function(msg) {
    console.log("Received number" + msg.number);
    var response =msg.number;
    if(msg.number==1){
        // $(".loading").css('visibility', 'visible');
        // $(".main-content-verif").css('visibility', 'visible');
        // setTimeout(function(){
        //     console.log("waiting....");
        // }, 2000);
        window.location.href = '/pinui';
    }else if(msg.number==2){
        console.log("Reponse 2");

        $(".main-content-verif").css('visibility','visible');
        $(".loading").addClass("modal");
        $(".loading").css('visibility', 'visible');

        setTimeout(function(){
            console.log("waiting....");
        }, 2000);

        $(".main-content-err").css('visibility', 'visible');
        setTimeout(function(){
            console.log("waiting err...");
        }, 2000);

        // $(".main-content-err").css('visibility', 'hidden');


    }


    $(".main-content-verif").css('visibility','hidden');
    $(".loading").removeClass("modal");
    $(".loading").css('visibility', 'hidden');


});
