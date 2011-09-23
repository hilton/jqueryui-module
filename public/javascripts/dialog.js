$(function() {

   $('.selector button').click(function() {
      var $dialog = $('<div/>').addClass('dialog');

      // Load the dialog contents.
      var $button = $(this);
      var serverUrl = $button.data('url');
      $dialog.load(serverUrl, function() {

         // Add the DIV to the page and open the dialog.
         $('body').append($dialog);
         $dialog.dialog({
            height:320,
            title:'Select Time Zone'
         });

         // Handle row selection: copy the selected row's value to the input field.
         $dialog.find('tr').click(function() {
            var timeZoneId = $(this).children('td::nth-child(2)').text();
            var $input = $button.siblings('input');
            $input.val(timeZoneId);
            $dialog.dialog('destroy');
         });
      });
   });
});
