var Login = function () {

	var handleLogin = function() {
		$('.login-form').validate({
	            errorElement: 'label', //default input error message container
	            errorClass: 'help-inline', // default input error message class
	            focusInvalid: false, // do not focus the last invalid input
	            rules: {
	                username: {
	                    required: true
	                },
	                password: {
	                    required: true
	                },
	                remember: {
	                    required: false
	                }
	            },

	            messages: {
	                username: {
	                    required: "Username is required1."
	                },
	                password: {
	                    required: "Password is required2."
	                }
	            },

	            invalidHandler: function (event, validator) { //display error alert on form submit   
	                //$('.alert-error', $('.login-form')).show();
					$('#aLogin').click();
	            },

	            highlight: function (element) { // hightlight error inputs
	                $(element)
	                   // .closest('.control-group').addClass('error'); // set error class to the control group
	            },

	            success: function (label) {
	                label.closest('.control-group').removeClass('error');
	                label.remove();
	            },

	            errorPlacement: function (error, element) {
	                error.addClass('help-small no-left-padding').insertAfter(element.closest('.input-icon'));
	            },

	            submitHandler: function (form) {
	                //form.submit();
					var username = $('#usernameLogin').val();
					var password = $('#passwordLogin').val();
					var remember = $('[name="remember"]:checked').val();
						// remember if checked value = 1
					var branch_name = $('#branch').text();
					
					$.post("AJAXcheckLogin.php", {username:username, password:password, remember:remember, branch:branch_name},
						function(data){
							if(Number(data) == 1){
								form.submit();
							}else{
								$('#aLoginError').click();
								$('#passwordLogin').val('');
							}
						}, 'json')
					.fail(function() { alert("ERROR : AJAXcheckLogin.php"); });
					
	            }
	        });

	        $('.login-form input').keypress(function (e) {
	            if (e.which == 13) {
	                if ($('.login-form').validate().form()) {
	                    $('.login-form').submit();
	                }
	                return false;
	            }
	        });
	}

	var handleForgetPassword = function () {
		$('.forget-form').validate({
	            errorElement: 'label', //default input error message container
	            errorClass: 'help-inline', // default input error message class
	            focusInvalid: false, // do not focus the last invalid input
	            ignore: "",
	            rules: {
	                email: {
	                    required: true,
	                    email: true
	                }
	            },

	            messages: {
	                email: {
	                    required: "Email is required."
	                }
	            },

	            invalidHandler: function (event, validator) { //display error alert on form submit   

	            },

	            highlight: function (element) { // hightlight error inputs
	                $(element)
	                    .closest('.control-group').addClass('error'); // set error class to the control group
	            },

	            success: function (label) {
	                label.closest('.control-group').removeClass('error');
	                label.remove();
	            },

	            errorPlacement: function (error, element) {
	                error.addClass('help-small no-left-padding').insertAfter(element.closest('.input-icon'));
	            },

	            submitHandler: function (form) {
	                form.submit();
	            }
	        });

	        $('.forget-form input').keypress(function (e) {
	            if (e.which == 13) {
	                if ($('.forget-form').validate().form()) {
	                    $('.forget-form').submit();
	                }
	                return false;
	            }
	        });

	        jQuery('#forget-password').click(function () {
	            jQuery('.login-form').hide();
	            jQuery('.forget-form').show();
	        });

	        jQuery('#back-btn').click(function () {
	            jQuery('.login-form').show();
	            jQuery('.forget-form').hide();
	        });

	}

	var handleRegister = function () {

		function format(state) {
            if (!state.id) return state.text; // optgroup
            return "<img class='flag' src='project/img/flags/" + state.id.toLowerCase() + ".png'/>&nbsp;&nbsp;" + state.text;
        }


		$("#select2_sample4").select2({
		  	placeholder: '<i class="fa fa-map-marker"></i>&nbsp;Select a Country',
            allowClear: true,
            formatResult: format,
            formatSelection: format,
            escapeMarkup: function (m) {
                return m;
            }
        });


			$('#select2_sample4').change(function () {
                $('.register-form').validate().element($(this)); //revalidate the chosen dropdown value and show error or success message for the input
            });



         $('.register-form').validate({
	            errorElement: 'label', //default input error message container
	            errorClass: 'help-inline', // default input error message class
	            focusInvalid: false, // do not focus the last invalid input
	            ignore: "",
	            rules: {
	                
	                first_name: {
	                    required: true
	                },
					last_name: {
	                    required: true
	                },
					phone: {
	                    required: true
	                },
	                email: {
	                    required: true,
	                    email: true
	                },
	                address_1: {
	                    required: true
	                },
	                address_2: {
	                    required: true
	                },
	                address_3: {
	                    required: true
	                },

	                username: {
	                    required: true
	                },
	                password: {
	                    required: true
	                },
	                rpassword: {
	                    equalTo: "#register_password"
	                },

	                accept: {
	                    required: true
	                }
	            },

	            messages: { // custom messages for radio buttons and checkboxes
	                accept: {
	                    required: "Please accept first."
	                }
	            },

	            invalidHandler: function (event, validator) { //display error alert on form submit   

	            },

	            highlight: function (element) { // hightlight error inputs
	                $(element)
	                // .closest('.control-group').addClass('error'); // set error class to the control group
	            },

	            success: function (label) {
	                // label.closest('.control-group').removeClass('error');
	                label.remove();
	            },

	            errorPlacement: function (error, element) {
	                if (element.attr("name") == "accept") { // insert checkbox errors after the container                  
	                    error.addClass('help-small no-left-padding').insertAfter($('#register_accept_error'));
	                } else if (element.closest('.input-icon').size() === 1) {
	                    error.addClass('help-small no-left-padding').insertAfter(element.closest('.input-icon'));
	                } else {
	                	error.addClass('help-small no-left-padding').insertAfter(element);
	                }
	            },

	            submitHandler: function (form) {
					if(String($('#hidLength').val()) == 'error'){
						$('#aLength').click();
					}else{		
						var status, address, address_1, address_2, address_3; 
							status 		= 'addUser';
							address_1 	= $(':input[name="address_1"]').val();
							address_2 	= $(':input[name="address_2"]').val();
							address_3 	= $(':input[name="address_3"]').val();
							switch(address_3){
								case 'AF' : address_3 = 'Afghanistan'; break;
								case 'AL' : address_3 = 'Albania'; break;
								case 'DZ' : address_3 = 'Algeria'; break;
								case 'AS' : address_3 = 'American Samoa'; break;
								case 'AD' : address_3 = 'Andorra'; break;
								case 'AO' : address_3 = 'Angola'; break;
								case 'AI' : address_3 = 'Anguilla'; break;
								case 'AQ' : address_3 = 'Antarctica'; break;
								case 'AR' : address_3 = 'Argentina'; break;
								case 'AM' : address_3 = 'Armenia'; break;
								case 'AW' : address_3 = 'Aruba'; break;
								case 'AU' : address_3 = 'Australia'; break;
								case 'AT' : address_3 = 'Austria'; break;
								case 'AZ' : address_3 = 'Azerbaijan'; break;
								case 'BS' : address_3 = 'Bahamas'; break;
								case 'BH' : address_3 = 'Bahrain'; break;
								case 'BD' : address_3 = 'Bangladesh'; break;
								case 'BB' : address_3 = 'Barbados'; break;
								case 'BY' : address_3 = 'Belarus'; break;
								case 'BE' : address_3 = 'Belgium'; break;
								case 'BZ' : address_3 = 'Belize'; break;
								case 'BJ' : address_3 = 'Benin'; break;
								case 'BM' : address_3 = 'Bermuda'; break;
								case 'BT' : address_3 = 'Bhutan'; break;
								case 'BO' : address_3 = 'Bolivia'; break;
								case 'BA' : address_3 = 'Bosnia and Herzegowina'; break;
								case 'BW' : address_3 = 'Botswana'; break;
								case 'BV' : address_3 = 'Bouvet Island'; break;
								case 'BR' : address_3 = 'Brazil'; break;
								case 'IO' : address_3 = 'British Indian Ocean Territory'; break;
								case 'BN' : address_3 = 'Brunei Darussalam'; break;
								case 'BG' : address_3 = 'Bulgaria'; break;
								case 'BF' : address_3 = 'Burkina Faso'; break;
								case 'BI' : address_3 = 'Burundi'; break;
								case 'KH' : address_3 = 'Cambodia'; break;
								case 'CM' : address_3 = 'Cameroon'; break;
								case 'CA' : address_3 = 'Canada'; break;
								case 'CV' : address_3 = 'Cape Verde'; break;
								case 'KY' : address_3 = 'Cayman Islands'; break;
								case 'CF' : address_3 = 'Central African Republic'; break;
								case 'TD' : address_3 = 'Chad'; break;
								case 'CL' : address_3 = 'Chile'; break;
								case 'CN' : address_3 = 'China'; break;
								case 'CX' : address_3 = 'Christmas Island'; break;
								case 'CC' : address_3 = 'Cocos (Keeling) Islands'; break;
								case 'CO' : address_3 = 'Colombia'; break;
								case 'KM' : address_3 = 'Comoros'; break;
								case 'CG' : address_3 = 'Congo'; break;
								case 'CD' : address_3 = 'Congo, the Democratic Republic of the'; break;
								case 'CK' : address_3 = 'Cook Islands'; break;
								case 'CR' : address_3 = 'Costa Rica'; break;
								case 'CI' : address_3 = 'Cote Ivoire'; break;
								case 'HR' : address_3 = 'Croatia (Hrvatska)'; break;
								case 'CU' : address_3 = 'Cuba'; break;
								case 'CY' : address_3 = 'Cyprus'; break;
								case 'CZ' : address_3 = 'Czech Republic'; break;
								case 'DK' : address_3 = 'Denmark'; break;
								case 'DJ' : address_3 = 'Djibouti'; break;
								case 'DM' : address_3 = 'Dominica'; break;
								case 'DO' : address_3 = 'Dominican Republic'; break;
								case 'EC' : address_3 = 'Ecuador'; break;
								case 'EG' : address_3 = 'Egypt'; break;
								case 'SV' : address_3 = 'El Salvador'; break;
								case 'GQ' : address_3 = 'Equatorial Guinea'; break;
								case 'ER' : address_3 = 'Eritrea'; break;
								case 'EE' : address_3 = 'Estonia'; break;
								case 'ET' : address_3 = 'Ethiopia'; break;
								case 'FK' : address_3 = 'Falkland Islands (Malvinas)'; break;
								case 'FO' : address_3 = 'Faroe Islands'; break;
								case 'FJ' : address_3 = 'Fiji'; break;
								case 'FI' : address_3 = 'Finland'; break;
								case 'FR' : address_3 = 'France'; break;
								case 'GF' : address_3 = 'French Guiana'; break;
								case 'PF' : address_3 = 'French Polynesia'; break;
								case 'TF' : address_3 = 'French Southern Territories'; break;
								case 'GA' : address_3 = 'Gabon'; break;
								case 'GM' : address_3 = 'Gambia'; break;
								case 'GE' : address_3 = 'Georgia'; break;
								case 'DE' : address_3 = 'Germany'; break;
								case 'GH' : address_3 = 'Ghana'; break;
								case 'GI' : address_3 = 'Gibraltar'; break;
								case 'GR' : address_3 = 'Greece'; break;
								case 'GL' : address_3 = 'Greenland'; break;
								case 'GD' : address_3 = 'Grenada'; break;
								case 'GP' : address_3 = 'Guadeloupe'; break;
								case 'GU' : address_3 = 'Guam'; break;
								case 'GT' : address_3 = 'Guatemala'; break;
								case 'GN' : address_3 = 'Guinea'; break;
								case 'GW' : address_3 = 'Guinea-Bissau'; break;
								case 'GY' : address_3 = 'Guyana'; break;
								case 'HT' : address_3 = 'Haiti'; break;
								case 'HM' : address_3 = 'Heard and Mc Donald Islands'; break;
								case 'VA' : address_3 = 'Holy See (Vatican City State)'; break;
								case 'HN' : address_3 = 'Honduras'; break;
								case 'HK' : address_3 = 'Hong Kong'; break;
								case 'HU' : address_3 = 'Hungary'; break;
								case 'IS' : address_3 = 'Iceland'; break;
								case 'IN' : address_3 = 'India'; break;
								case 'ID' : address_3 = 'Indonesia'; break;
								case 'IR' : address_3 = 'Iran (Islamic Republic of)'; break;
								case 'IQ' : address_3 = 'Iraq'; break;
								case 'IE' : address_3 = 'Ireland'; break;
								case 'IL' : address_3 = 'Israel'; break;
								case 'IT' : address_3 = 'Italy'; break;
								case 'JM' : address_3 = 'Jamaica'; break;
								case 'JP' : address_3 = 'Japan'; break;
								case 'JO' : address_3 = 'Jordan'; break;
								case 'KZ' : address_3 = 'Kazakhstan'; break;
								case 'KE' : address_3 = 'Kenya'; break;
								case 'KI' : address_3 = 'Kiribati'; break;
								case 'KP' : address_3 = 'Korea, Democratic People Republic of'; break;
								case 'KR' : address_3 = 'Korea, Republic of'; break;
								case 'KW' : address_3 = 'Kuwait'; break;
								case 'KG' : address_3 = 'Kyrgyzstan'; break;
								case 'LA' : address_3 = 'Lao People Democratic Republic'; break;
								case 'LV' : address_3 = 'Latvia'; break;
								case 'LB' : address_3 = 'Lebanon'; break;
								case 'LS' : address_3 = 'Lesotho'; break;
								case 'LR' : address_3 = 'Liberia'; break;
								case 'LY' : address_3 = 'Libyan Arab Jamahiriya'; break;
								case 'LI' : address_3 = 'Liechtenstein'; break;
								case 'LT' : address_3 = 'Lithuania'; break;
								case 'LU' : address_3 = 'Luxembourg'; break;
								case 'MO' : address_3 = 'Macau'; break;
								case 'MK' : address_3 = 'Macedonia, The Former Yugoslav Republic of'; break;
								case 'MG' : address_3 = 'Madagascar'; break;
								case 'MW' : address_3 = 'Malawi'; break;
								case 'MY' : address_3 = 'Malaysia'; break;
								case 'MV' : address_3 = 'Maldives'; break;
								case 'ML' : address_3 = 'Mali'; break;
								case 'MT' : address_3 = 'Malta'; break;
								case 'MH' : address_3 = 'Marshall Islands'; break;
								case 'MQ' : address_3 = 'Martinique'; break;
								case 'MR' : address_3 = 'Mauritania'; break;
								case 'MU' : address_3 = 'Mauritius'; break;
								case 'YT' : address_3 = 'Mayotte'; break;
								case 'MX' : address_3 = 'Mexico'; break;
								case 'FM' : address_3 = 'Micronesia, Federated States of'; break;
								case 'MD' : address_3 = 'Moldova, Republic of'; break;
								case 'MC' : address_3 = 'Monaco'; break;
								case 'MN' : address_3 = 'Mongolia'; break;
								case 'MS' : address_3 = 'Montserrat'; break;
								case 'MA' : address_3 = 'Morocco'; break;
								case 'MZ' : address_3 = 'Mozambique'; break;
								case 'MM' : address_3 = 'Myanmar'; break;
								case 'NA' : address_3 = 'Namibia'; break;
								case 'NR' : address_3 = 'Nauru'; break;
								case 'NP' : address_3 = 'Nepal'; break;
								case 'NL' : address_3 = 'Netherlands'; break;
								case 'AN' : address_3 = 'Netherlands Antilles'; break;
								case 'NC' : address_3 = 'New Caledonia'; break;
								case 'NZ' : address_3 = 'New Zealand'; break;
								case 'NI' : address_3 = 'Nicaragua'; break;
								case 'NE' : address_3 = 'Niger'; break;
								case 'NG' : address_3 = 'Nigeria'; break;
								case 'NU' : address_3 = 'Niue'; break;
								case 'NF' : address_3 = 'Norfolk Island'; break;
								case 'MP' : address_3 = 'Northern Mariana Islands'; break;
								case 'NO' : address_3 = 'Norway'; break;
								case 'OM' : address_3 = 'Oman'; break;
								case 'PK' : address_3 = 'Pakistan'; break;
								case 'PW' : address_3 = 'Palau'; break;
								case 'PA' : address_3 = 'Panama'; break;
								case 'PG' : address_3 = 'Papua New Guinea'; break;
								case 'PY' : address_3 = 'Paraguay'; break;
								case 'PE' : address_3 = 'Peru'; break;
								case 'PH' : address_3 = 'Philippines'; break;
								case 'PN' : address_3 = 'Pitcairn'; break;
								case 'PL' : address_3 = 'Poland'; break;
								case 'PT' : address_3 = 'Portugal'; break;
								case 'PR' : address_3 = 'Puerto Rico'; break;
								case 'QA' : address_3 = 'Qatar'; break;
								case 'RE' : address_3 = 'Reunion'; break;
								case 'RO' : address_3 = 'Romania'; break;
								case 'RU' : address_3 = 'Russian Federation'; break;
								case 'RW' : address_3 = 'Rwanda'; break;
								case 'KN' : address_3 = 'Saint Kitts and Nevis'; break;
								case 'LC' : address_3 = 'Saint LUCIA'; break;
								case 'VC' : address_3 = 'Saint Vincent and the Grenadines'; break;
								case 'WS' : address_3 = 'Samoa'; break;
								case 'SM' : address_3 = 'San Marino'; break;
								case 'ST' : address_3 = 'Sao Tome and Principe'; break;
								case 'SA' : address_3 = 'Saudi Arabia'; break;
								case 'SN' : address_3 = 'Senegal'; break;
								case 'SC' : address_3 = 'Seychelles'; break;
								case 'SL' : address_3 = 'Sierra Leone'; break;
								case 'SG' : address_3 = 'Singapore'; break;
								case 'SK' : address_3 = 'Slovakia (Slovak Republic)'; break;
								case 'SI' : address_3 = 'Slovenia'; break;
								case 'SB' : address_3 = 'Solomon Islands'; break;
								case 'SO' : address_3 = 'Somalia'; break;
								case 'ZA' : address_3 = 'South Africa'; break;
								case 'GS' : address_3 = 'South Georgia and the South Sandwich Islands'; break;
								case 'ES' : address_3 = 'Spain'; break;
								case 'LK' : address_3 = 'Sri Lanka'; break;
								case 'SH' : address_3 = 'St. Helena'; break;
								case 'PM' : address_3 = 'St. Pierre and Miquelon'; break;
								case 'SD' : address_3 = 'Sudan'; break;
								case 'SR' : address_3 = 'Suriname'; break;
								case 'SJ' : address_3 = 'Svalbard and Jan Mayen Islands'; break;
								case 'SZ' : address_3 = 'Swaziland'; break;
								case 'SE' : address_3 = 'Sweden'; break;
								case 'CH' : address_3 = 'Switzerland'; break;
								case 'SY' : address_3 = 'Syrian Arab Republic'; break;
								case 'TW' : address_3 = 'Taiwan, Province of China'; break;
								case 'TJ' : address_3 = 'Tajikistan'; break;
								case 'TZ' : address_3 = 'Tanzania, United Republic of'; break;
								case 'TH' : address_3 = 'Thailand'; break;
								case 'TG' : address_3 = 'Togo'; break;
								case 'TK' : address_3 = 'Tokelau'; break;
								case 'TO' : address_3 = 'Tonga'; break;
								case 'TT' : address_3 = 'Trinidad and Tobago'; break;
								case 'TN' : address_3 = 'Tunisia'; break;
								case 'TR' : address_3 = 'Turkey'; break;
								case 'TM' : address_3 = 'Turkmenistan'; break;
								case 'TC' : address_3 = 'Turks and Caicos Islands'; break;
								case 'TV' : address_3 = 'Tuvalu'; break;
								case 'UG' : address_3 = 'Uganda'; break;
								case 'UA' : address_3 = 'Ukraine'; break;
								case 'AE' : address_3 = 'United Arab Emirates'; break;
								case 'GB' : address_3 = 'United Kingdom'; break;
								case 'US' : address_3 = 'United States'; break;
								case 'UM' : address_3 = 'United States Minor Outlying Islands'; break;
								case 'UY' : address_3 = 'Uruguay'; break;
								case 'UZ' : address_3 = 'Uzbekistan'; break;
								case 'VU' : address_3 = 'Vanuatu'; break;
								case 'VE' : address_3 = 'Venezuela'; break;
								case 'VN' : address_3 = 'Viet Nam'; break;
								case 'VG' : address_3 = 'Virgin Islands (British)'; break;
								case 'VI' : address_3 = 'Virgin Islands (U.S.)'; break;
								case 'WF' : address_3 = 'Wallis and Futuna Islands'; break;
								case 'EH' : address_3 = 'Western Sahara'; break;
								case 'YE' : address_3 = 'Yemen'; break;
								case 'ZM' : address_3 = 'Zambia'; break;
								case 'ZW' : address_3 = 'Zimbabwe'; break;
							}
							address		= address_1+' '+address_2+' '+address_3;
						
						var username 	= $('#username').val();
						var password 	= $('#register_password').val();
						var first_name 	= $(':input[name="first_name"]').val();
						var last_name 	= $(':input[name="last_name"]').val();
						var phone		= $(':input[name="phone"]').val();
						var email		= $('#email').val();
						
						$.post("AJAXaddUser.php", {status:status, first_name:first_name, last_name:last_name, phone:phone, email:email, address:address, username:username, password:password},
							function(data){
								if(String(data) == 'true'){
									//form.submit();
									$.post("AJAXcheckLogin.php", {username:username, password:password, remember:'0', branch:'Web Master'},
										function(data){
											if(Number(data) == 1){
												form.submit();
											}
										}, 'json')
									.fail(function() { alert("ERROR : AJAXcheckLogin.php"); });
					
								}else if(String(data) == 'false'){
									$('#aDuplicates').click();
								}
								
							}, 'json')
						.fail(function() { alert("error"); });
					}
					
	            }
	        });

			$('.register-form input').keypress(function (e) {
	            if (e.which == 13) {
	                if ($('.register-form').validate().form()) {
	                    $('.register-form').submit();
	                }
	                return false;
	            }
	        });

	        jQuery('#register-btn').click(function () {
	            jQuery('.login-form').hide();
	            jQuery('.register-form').show();
	        });

	        jQuery('#register-back-btn').click(function () {
	            jQuery('.login-form').show();
	            jQuery('.register-form').hide();
	        });
	}
    
    return {
        //main function to initiate the module
        init: function () {
        	
            handleLogin();
            handleForgetPassword();
            handleRegister();        

            $.backstretch([
		        "project/img/bg/1.jpg",
		        "project/img/bg/2.jpg",
		        "project/img/bg/3.jpg",
		        "project/img/bg/4.jpg"
		        ], {
		          fade: 1000,
		          duration: 8000
		    });
	       
        }

    };

}();