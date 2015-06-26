// JavaScript Document
$('[data-toggle="popover"]').popover();
  $("#activeAvatarPop").popover({
        html : true, 
        content: function() {
          return $('#popOverAvatarContent').html();
        }
    });
  $('[data-toggle=offcanvas]').click(function() {
    $('.row-offcanvas').toggleClass('active');
  });
  
  $.fn.raty.defaults.path = 'asset/library/raty/images';

  $(function() {
	  $('header').affix({
		offset: {
		  top: 100
		}
	  });
    $('.rate').raty({
			score: function() {
				return $(this).attr('data-score');
			  },
  readOnly: function(){
	  	return $(this).attr('data-readOnly');
	  }
	});
	
	var heights = $(".itemWrapper").map(function() {
        return $(this).height();
    }).get(),
    maxHeight = Math.max.apply(null, heights);
    $(".listItem").height(maxHeight);
	
	var timer;
	$(window).bind('resize', function() {
	  clearTimeout(timer);
	  timer = setTimeout(function(){  
	  	var heights = $(".listItem").map(function() {
        return $(this).height();
		}).get(),
		maxHeight = Math.max.apply(null, heights);
		$(".listItem").height(0);
		$(".listItem").height(maxHeight);
	  }, 1000);
	});	
  });
  $(window).load(function () {
        $(".navigation > a").click(function(e) {
			e.preventDefault();
          if (!$(this).hasClass("active")) {
            $(".navigation").unbind('mouseleave');
            $(".navigation .second_level").removeClass("animated fadeInDown").hide();
            var el = $(this);
            el.addClass("hover");
            $(".navigation > a.active").fadeOut("fast", function() {
              var prev = $(this)
              prev.removeClass('active');
              container_pos = $(".navigation").offset()
              button_pos = el.offset()
              el.animate({ top: container_pos.top - button_pos.top }, 500, function() {
                el.addClass("active").removeClass("hover").css("top", 0);
                if (el.attr("href").length > 1 && el.attr("href") != "#") {
                  $(".navigation > a:not(.active)").removeClass("fadeInLeft animated").hide();
                  $(el.attr("href")).addClass("fadeInDown animated").show();
                } else {
                  prev.addClass("fadeInLeft animated").fadeIn("fast");
                }
              });
            });
          }

        });

        $(".navigation .back").hover(
          function () {
            var el = $(this)
            $(".navigation .second_level").removeClass("animated fadeInDown").hide();
            $(".navigation > a:not(.active)").addClass("fadeInLeft animated").show()
            $(".navigation").hover(function() {}, function() {
              $(this).unbind('mouseleave');
              $(".navigation > a:visible:not(.active)").hide().removeClass("fadeInLeft animated");
              el.closest(".second_level").addClass("animated fadeInDown").show();
            });
          });
		  
		   $(".navigation").unbind('mouseleave');
           $(".navigation .second_level").removeClass("animated fadeInDown").hide();
		  var el = $(".navigation > a.active");
            el.addClass("hover");
			
            $(".navigation > a.active").fadeOut("fast", function() {
              var prev = $(this)
              prev.removeClass('active');
              container_pos = $(".navigation").offset()
              button_pos = el.offset()
              el.animate({ top: container_pos.top - button_pos.top }, 500, function() {
                el.addClass("active").removeClass("hover").css("top", 0);
                if (el.attr("href").length > 1 && el.attr("href") != "#") {
                  $(".navigation > a:not(.active)").removeClass("fadeInLeft animated").hide();
                  $(el.attr("href")).addClass("fadeInDown animated").show();
                } else {
                  prev.addClass("fadeInLeft animated").fadeIn("fast");
                }
              });
			  el.css("display","table");
            });
      });