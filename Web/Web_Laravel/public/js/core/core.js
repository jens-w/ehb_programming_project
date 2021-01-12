// CORE JAVASCRIPT // 

// ****** ENABLED ON EVERY PAGE ******* //


function base64_url_encode($input) {
    return strtr(base64_encode($input), '+/=', '._-');
   }
   
   function base64_url_decode($input) {
    return base64_decode(strtr($input, '._-', '+/='));
   }
   $("button[type='update']").click(function(){showLoading()});
   $("input[type='submit']").click(function(){showLoading()});

   $(document).ready(function(){hideLoading()})

   function showLoading() {
    document.querySelector('#loading').classList.add('loader');
    document.querySelector('#loading-content').classList.add('loading-content');
  }
  
  function hideLoading() {
    document.querySelector('#loading').classList.remove('loader');
    document.querySelector('#loading-content').classList.remove('loading-content');
  }