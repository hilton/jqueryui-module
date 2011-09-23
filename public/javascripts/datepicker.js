$(function() {

   /**
    * Converts a java.text.SimpleDateFormat pattern to a Datepicker pattern:
    * http://docs.jquery.com/UI/Datepicker/formatDate
    */
   var convertDateFormat = function(format) {

      var replacements = [
         ['yyyy', 'yy'], // 2011
         ['yy', 'y'], // 11
         ['MMMM', 'MM'], // January
         ['MMM', 'M'], // Jan
         ['MM', 'mm'], // 01
         ['M', 'm'], // 1
      ];

      var result ='';
      while (format.length > 0) {
         for (var i=0; i < replacements.length; i++) {
            if (format.substr(0, replacements[i][0].length) == replacements[i][0]) {
               // The initial substring is a match, so copy the replacement to the result.
               result = result + replacements[i][1]

               // Removed the matched initial substring and continue.
               format = format.substr(replacements[i][0].length);
               break;
            }
         }

         // Move one character to the result and look for more matches.
         result = result + format.substr(0, 1);
         format = format.substr(1);
      }
      return result;
   };


   /**
    * Set date picker default options using the Java date format from the body tag.
    */
   var options = {
      dateFormat: convertDateFormat($('body').data('dateformat'))
   };

   // Select the localisation for the current Play language - play.i18n.Lang.get().
   var lang = $('html').attr('lang');
   lang = (lang == 'en' ? '' : lang);
   $.extend(options, $.datepicker.regional[lang]);
   $.datepicker.setDefaults(options);

   /**
    * Date picker, configured with server-side configuration options.
    */
   $('input.date').datepicker();
});
