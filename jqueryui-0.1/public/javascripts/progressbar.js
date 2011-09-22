$(function() {

   // Initialise the progress bar.
   var $progressbar = $('#progressbar').progressbar();

   // Open the web socket connection.
   var serverUrl = $progressbar.data('url');
   var socket = new WebSocket(serverUrl);

   // Use web socket messages to update the progress bar.
   socket.onmessage = function(event) {
      $progressbar.progressbar('value', parseInt(event.data));
   };
});
