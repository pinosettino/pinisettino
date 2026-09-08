
/****************** $(document).ready(function()*****************************/

$(document).ready(function(){

			/******************* variazione dim font *****************************/
			 var multiplex = 1.0;
			 var fact=1.2;
             var xp = parseInt($("body").css("font-size"));
var isTouchDevice = 'ontouchstart' in document.documentElement;
function is_touch_device() {
  return !!('ontouchstart' in window);
}
if (is_touch_device())
$("#bars").css("left","-9999px");

            $('#normal').click(function(){
             	$("body").css("font-size", xp+'px');
			 	multiplex = 1.0;
             	return false;             
            });

            $('#more').click(function(){
        		multiplex = multiplex*fact;
        		px = parseInt(xp)*multiplex;
             	$("body").css("font-size", px+'px');
             	return false;
            });
            $('#bars').click(function(){
            	$(".Amain").css("padding-right","0px")
				$("#bars").css("left","-9999px");
				$("#nobars").css("left","10px");
				$('#scroll').html("si");
             	return false;
            });
            $('#nobars').click(function(){
            	$(".Amain").css("padding-right","16px")
				$("#bars").css("left","10px");
				$("#nobars").css("left","-9999px");
				$('#scroll').html("no");
             	return false;
            });
 
            $('#less').click(function(){
        		multiplex = multiplex/fact;
        		px = parseInt(xp)*multiplex;
            	$("body").css("font-size", px+'px');
            	return false;             
            });
            $('#down').click(function(){
				$('#topdiv').animate({top:'+0px'},400);            	
            	return false;             
            });
            $('#up').click(function(){
            	if(!Pclose) $('#openmenu').click();
				$('#topdiv').animate({top:'-76px'},400);            	
            	return false;             
            });
            
            
			/**********************************************************************/
			/******************************* menu **********************************/
			$("#A1").css("margin-left","0px");
			$("#tic").css("width","200px");
			
			$("#ticm").css("width","44px");
			$("#ticm").css("border-bottom-right-radius","0");
			$("#ticm").css("margin-right","-1px");
			$("#ticp").css("z-index","160");			
						
			$("#ticp").css("width","44px");			
			$("#ticp").css("border-bottom-left-radius","0");
			$("#ticp").css("margin-right","-3px");
			$("#ticp").css("z-index","160");			
			var old_tast="";
			var Pclose= true;
			var Tclose= true;
			var first=true;
			var string="0px";
			var last_string="0px";
			var open_string="0px";
			var heightP=0;
			var Correct_Height=160;
			var color="#ffffff";
			var color_act="#000000";
			var b_color="#6666ad";
			var b_color_act="#bfbfff";
			var this_prec="";
			var T_this="";
			var str_attesa="<div class='attesa'><img border='0' src='../img/spinner.gif'></div>";
			/* Cambiare l'effetto da utilizzare */
			/* nasconde il tasto home */
			$("#home").css("visibility","hidden");
			$("#home").css("height","0px");	
					
			
			
			/* Associare una funzione all'evento click sul link */
			$('li.title a').click(function(e){
				/* Finding the drop down list that corresponds to the current section: */
				if(this==this_prec || Tclose) Tclose= (!Tclose);
				T_this=this;
				var subMenu = $(this).parent().next();
				$('.sub-menu').not(subMenu).slideUp('slow');
				subMenu.stop(false,true).slideToggle('slow');
				/* Trovare il sotto menu che corrisponde alla voce cliccata */
				e.preventDefault();
				/* Prevenire l'evento predefinito (che sarebbe di seguire il collegamento) */
				var new_tast= $(this).attr("name");
				/* cambio colore al tasto menu */

				if(this_prec && this!=this_prec){
					$(this_prec).css("background-color",b_color);
  					$(this_prec).css("color",color);
				}
				if(this!=this_prec){
   		  			$(this).css("background-color",b_color_act);
    				$(this).css("color",color_act);
     				this_prec=this;
     			}	
				
/*--------------------------------*/			
				
				if (new_tast!=old_tast){
					old_tast=new_tast;
					var lgh=parseInt($(this).attr("name"));
					var height=lgh*36 + heightP;
					string=(height+"px");
					last_string=string;
				}
				else{
					string=open_string;
				}
				$(".tox").css("height",string);
				var str=$(this).attr("href");
				var firststr=str[0];
				var secondstr=str[1];
			})
			
			
			$('li.Ptitle a').click(function(e){
				/* Finding the drop down list that corresponds to the current section: */
				var subPMenu = $(this).parent().next();
				$('.sub-Pmenu').not(subPMenu).slideUp('slow');
				subPMenu.stop(false,true).slideToggle('slow');
				/* Trovare il sotto menu che corrisponde alla voce cliccata */
				e.preventDefault();
				/* Prevenire l'evento predefinito (che sarebbe di seguire il collegamento) */
				if (Pclose){
					if(first){
						var new_tast= $(this).attr("name");
						var lgh=parseInt($(this).attr("name"));
						heightP=lgh* 36 + Correct_Height;
						first=false;
						string=(heightP+"px");
						last_string=string;
						open_string=string;
					}
					Pclose=false;
					string=last_string;	
				}
				else {
					if(!Tclose && T_this) $(T_this).click();
					T_this="";
					Tclose=true;
					first=true;
					string="0px";
					old_tast="";
					last_string="0px";
					open_string="0px";
					heightP="0";
					Pclose=true;
					heightP="0";
					this_prec="";
				}
				$(".tox").css("height",string);
			})
			
			
			$(document).click( function(e){
    			// Se il click è fuori dal popup o dai suoi "figli"
    			if (( $(e.target).closest("#min").length === 0 )&&(!Pclose)) {
       			 // Nascondo il popup
       			{ 
				$(this_prec).css("background-color",b_color);
				$(this_prec).css("color",color);
      			$('#openmenu').click();
      			}
             	return false;
    		}
			});	
			
			$('#tic').click(function(e){
			  $('#openmenu').click();
             	return false;
			});	
	
/*******  questo controlla se l'indirizzo punta ad una specifica pagina e la processa  **********/
	$('#inner2').load("../right/inner2.htm");
	var risultato_ricerca="";
			var url=self.location.href;
			var home="../home/home.php";
			var page_prec=home;
			var prot=location.protocol;
			var host=location.hostname;
			var pre_url=prot+"//"+host+"/home/";
			var page_error="../home/error.htm";
			var url_error=pre_url+"#!../home/page_error.htm";
			var url_home=pre_url+"#!../home/home.php";
			var vsplit=url.split('#!');
			var aaa=vsplit[0];
			var page=vsplit[1];
			if(!page){
				page=home;
				url=url_home;
			}
			$('#inner').load(page, load_init);

/*******  questo controlla e processa le pagine dei link  **********/
			$('li.link a').click(function(e){
				flag_open=true;
				flag=true;
				var str=$(this).attr("href");
				$(this_prec).css("background-color",b_color);
				$(this_prec).css("color",color);
				if(str.length>6)
				{
					var firststr=str[0];
					var secondstr=str[1];
					if(firststr=="#" && secondstr=="!")
					{
						page= str.substring(2, str.length);
						$('#openmenu').click();
						
						if(page!=page_prec||page==home)
						{
							window.scrollTo(0,0);
							$('#inner').html("<br><br><br><br><br><p align='center'><img border='0' src='../img/spinner.gif'></p>");
							$('#inner').load(page,load_link);
						}
						else
						  return false;
					}	  
					else
					{
						$('#openmenu').click();
						return true;
					}
				}
				else
				{
					$('#openmenu').click();
					return false;
				}
			});	
/************************************************************************/
			function load_init(responseTxt, statusTxt, xhr)
			{
        		if(statusTxt == "error")
        			alert("Error: " + xhr.status + ": " + xhr.statusText);
            }

			function load_link(responseTxt, statusTxt, xhr)
			{
        		if(statusTxt == "success")
        		{
					page_prec=page;
					url=(prot+"//"+host+"/home/#!"+page);
					return false
        			
        		}
        		if(statusTxt == "error")
        		{
        			alert("Error: " + xhr.status + ": " + xhr.statusText);
					page=page_prec;
					$('#inner').load(page_error);
					url=url_error;
					history.replaceState(null,null,url);
					return true
        		}
            }

		/*************************************************************************/
		var flag_open=false;
		var flag=false;
			
 
 		window.addEventListener("hashchange", function(e) {
 		var url2=window.location.href;
 			var path=location.pathname;
       		if(!flag)
 			{
				var vsplit=url2.split('#!');
				var page=vsplit[1];
				if(!page) page=home;
				page_prec=page;
				flag_open=false;
				$('#inner').load(page);
			}
			else
				flag=false;
 		}) 			


});		
			
/*****************************fine ready function ***********************************/




/******************************** clientscript **************************************/
 
 
 
 
 
$(function(){
  
  /*
  POSIZIONO IL FOOTER IN FONDO AL DISPLAY
  */
  var altezza = $(window).height(); // calcolo altezza display
  var menu = 95; // altezza menu + bordo + margine
  var footer = 40; // altezza footer
  var immagine= 135; // altezza immgine
  var contenitore = (altezza-menu-footer-immagine); // calcolo altezza minima del contenitore
  $('#contenitore').css('min-height',contenitore); // assegno
});
/******************************** fine clientscript **************************************/
