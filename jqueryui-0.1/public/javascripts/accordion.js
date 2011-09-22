/*
 * Accordion that loads content from the Ajax source specified by the data-url
 * attribute and heading names.
 */
$(function() {

   // Add a DIV for each section's content.
   $('div.accordion h3').each( function() {
      $(this).after('<div></div>');
   });

   // Apply the Accordion widget to each section.
   $('div.accordion').each( function() {
      var $accordion = $(this);

      // Get the Ajax URL from an HTML5 data attribute.
      var serverUrl = $accordion.data('url');

      $(this).accordion({
         autoHeight: false,
         active: false, // Initially collapse all sections.
         changestart: function(event, ui) {
            // Load content if the div is still empty.
            if (ui.newContent.is(':empty')) {
               $(ui.newContent).load(serverUrl + escape(ui.newHeader.text()));
            }
         },
         change: function() {
            // Resize the content DIV after populating it.
            $accordion.accordion('resize');
         }
      });
   });
});
