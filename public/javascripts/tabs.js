$(function() {

   /**
    * Tabs that load content from the Ajax source specified by each tab’s link.
    */
   $('div.tabs').tabs({
      ajaxOptions: {
         error: function(xhr, status, index, anchor) {
            $(anchor.hash).html('Error loading tab');
         }
      }
   });
});
