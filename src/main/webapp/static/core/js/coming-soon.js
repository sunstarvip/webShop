var ComingSoon = function () {

    return {
        //main function to initiate the module
        init: function () {

            $.backstretch([
                "../project/zuidemo/img/gallery/image1.jpg",
                "../project/zuidemo/img/gallery/image2.jpg",
                "../project/zuidemo/img/gallery/image3.jpg",
                "../project/zuidemo/img/gallery/image4.jpg"
    		        ], {
    		          fade: 1000,
    		          duration: 10000
    		    });

            var austDay = new Date();
            austDay = new Date(austDay.getFullYear() + 1, 1 - 1, 26);
            $('#defaultCountdown').countdown({until: austDay});
            $('#year').text(austDay.getFullYear());
        }

    };

}();