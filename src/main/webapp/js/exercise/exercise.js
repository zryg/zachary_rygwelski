$(document).foundation()
$( document ).ready(function() {
    console.log("ok");
    $('.js-addSet').click(function() {
        console.log("clicked");
        $.ajax({
            url: '/exercises/set',
            type: 'POST',
            data: $('#myform').serializeArray()
            success: function() {
                alert('PUT completed');
            }
        });
   });
});
