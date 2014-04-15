// JavaScript Document
$(function(){
    $('#comment1,#comment2,#comment3,#comment4,#comment5,#comment6,#comment7,#comment8,#comment9,#comment10').bind('keyup',function(){
        for ( num=1; num<=10; num++ ) {
//            var max = 30;//色変え
//            var min = 5;//色変え
//            var thisValueLength = $("#comment" + num).val().replace(/\s+/g,'').length;
			var thisValueLength = $('#comment' + num).val().length;
 
//            if (thisValueLength <= min || thisValueLength >= max) {//色変え
//                $(".count" + num).addClass('red');//色変え
//            } else {//色変え
//                $(".count" + num).removeClass('red');//色変え
//            }
            $(".count" + num).html(thisValueLength);
			
        }
    });
});