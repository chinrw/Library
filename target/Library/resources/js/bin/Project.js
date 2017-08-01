/**
 * Created by chin39 on 2017/6/16.
 **/

(function ($) {
    $(function () {
        //       $('.main-content').load('/pages/main-content.html');
        $('.carousel.carousel-slider').carousel({fullWidth: true});
        $('.parallax').parallax();
        $('.button-collapse').sideNav();
        $('.collapsible').collapsible();
        $('.modal').modal({
            inDuration: 200
        });
        $('select').material_select();
        $('.dropdown-button').dropdown({
                inDuration: 300,
                outDuration: 225,
                constrainWidth: false, // Does not change width of dropdown to that of the activator
                hover: false, // Activate on hover
                belowOrigin: true,
                // gutter: 0, // Spacing from edge
                alignment: 'right', // Displays dropdown with edge aligned to the left of button
                stopPropagation: false // Stops event propagation
            }
        );

        $('#help-click').click(function () {
            $('.tap-target').tapTarget('open');
        });
        $('.header-search-input').focus(function () {
            $("#search-icon").addClass('header-search-wrapper-focus');
        }).focusout(
            function () {
                $("#search-icon").removeClass('header-search-wrapper-focus');
            }
        );
        $('.datepicker').pickadate({
            selectMonths: true, // Creates a dropdown to control month
            selectYears: 100, // Creates a dropdown of 15 years to control year
            format: 'yyyy-mm-dd',
            container: 'body'
        });
        $('.tooltipped').tooltip({delay: 50});
    }); // end of document ready
})(jQuery);