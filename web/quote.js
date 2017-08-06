var xHRObject = new XMLHttpRequest();
// status 0

function getNewQuote () {
	xHRObject.open("GET", "ManageQuoteServlet", true);
	// status 1
	xHRObject.onreadystatechange = getData;
	xHRObject.send(null);
	// status 2
} 

// status 3
function getData () {
	if (xHRObject.readyState == 4) {
		// status 4
		if (xHRObject.status == 200) {
			var response = xHRObject.responseText;
			
			var quoteDiv = document.getElementById("quote");
			var quoteParagraph = quoteDiv.childNodes[0];

			if (quoteParagraph == null) {
				quoteParagraph = document.createElement('p');
				quoteParagraph.id = "quoteText";
				var quoteText = document.createTextNode(response);
				quoteParagraph.appendChild(quoteText);
				quoteDiv.appendChild(quoteParagraph);
			}
			else {
				var quoteText = document.createTextNode(response);
				quoteParagraph.removeChild(quoteParagraph.childNodes[0]);
				quoteParagraph.appendChild(quoteText);
			}	
		}
	}					
}