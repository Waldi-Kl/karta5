/**
 * 
 */

// Side navigation
		function w3_open() {
			var x = document.getElementById("mySidebar");
			x.style.width = "100%";
			x.style.fontSize = "40px";
			x.style.paddingTop = "10%";
			x.style.display = "block";
		}
		function w3_close() {
			document.getElementById("mySidebar").style.display = "none";
		}

		// Tabs
		function openTabs(evt, tabsName) {
			var i;
			var x = document.getElementsByClassName("tab");
			for (i = 0; i < x.length; i++) {
				x[i].style.display = "none";
			}
			var activebtn = document.getElementsByClassName("testbtn");
			for (i = 0; i < x.length; i++) {
				activebtn[i].className = activebtn[i].className.replace(
						" w3-dark-grey", "");
			}
			document.getElementById(tabsName).style.display = "block";
			evt.currentTarget.className += " w3-dark-grey";
		}

		var mybtn = document.getElementsByClassName("testbtn")[0];
		mybtn.click();

		// Accordions
		function myAccFunc(id) {
			var x = document.getElementById(id);
			if (x.className.indexOf("w3-show") == -1) {
				x.className += " w3-show";
			} else {
				x.className = x.className.replace(" w3-show", "");
			}
		}

		// Slideshows
		var slideIndex = 1;

		function plusDivs(n) {
			slideIndex = slideIndex + n;
			showDivs(slideIndex);
		}

		function showDivs(n) {
			var x = document.getElementsByClassName("mySlides");
			if (n > x.length) {
				slideIndex = 1
			}
			if (n < 1) {
				slideIndex = x.length
			}
			;
			for (i = 0; i < x.length; i++) {
				x[i].style.display = "none";
			}
			x[slideIndex - 1].style.display = "block";
		}

		showDivs(1);

		// Progress Bars
		function move() {
			var elem = document.getElementById("myBar");
			var width = 5;
			var id = setInterval(frame, 10);
			function frame() {
				if (width == 100) {
					clearInterval(id);
				} else {
					width++;
					elem.style.width = width + '%';
					elem.innerHTML = width * 1 + '%';
				}
			}
		}
		
		// Przesukiwanie listy
		function findUserFunction() {
			  var input, filter, table, tr, td, i;
			  document.getElementById("userTable"); // To musiałem dodać bo nie wywoływała się fukcja
			  input = document.getElementById("adminInput");
			  filter = input.value.toUpperCase();
			  table = document.getElementById("userTable");
			  tr = table.getElementsByTagName("tr");
			  for (i = 0; i < tr.length; i++) {
			    td = tr[i].getElementsByTagName("td")[1];
			    if (td) {
			      if (td.innerHTML.toUpperCase().indexOf(filter) > -1) {
			        tr[i].style.display = "";
			      } else {
			        tr[i].style.display = "none";
			      }
			    }
			  }
			}

		function myFunction() {
		    var x = document.getElementById("demo");
		    x.value = x.value.toUpperCase();
		}
		
		// Walidacja formulaża
		function validateUserForm() {
		    var x = document.forms["userForm"]["first"].value;
		    if (x == "") {
		        alert("Pola nie mogą być puste");
		        return false;
		    }
		}
