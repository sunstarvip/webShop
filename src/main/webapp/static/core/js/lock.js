var Lock = function () {

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
		          duration: 8000
		      });
        }

    };

}();