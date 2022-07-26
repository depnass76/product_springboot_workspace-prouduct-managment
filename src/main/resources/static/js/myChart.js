var chartDataStr = decodeHtml(chartData);
var chartJsonArray = JSON.parse(chartDataStr);

var arrayLength = chartJsonArray.length;

var numericData = [];
var labelData = [];

for (var i = 0; i < arrayLength; i++) {
	numericData[i] =(chartJsonArray[i].value) ;
	labelData[i] = chartJsonArray[i].label;
}

/* For a pie chart */
new Chart(document.getElementById("myPieChart"), {
	plugins: [labelData],
	type: 'pie',
	/* The data for our dataset*/
	data: {
		labels: labelData,
		datasets: [{
			label: 'My First dataset',
			backgroundColor: ["#F7464A", "#46BFBD", "#FDB45C", "#949FB1", "#4D5360"],
			hoverBackgroundColor: ["#FF5A5E", "#5AD3D1", "#FFC870", "#A8B3C5", "#616774"],
			borderColor: 'rgb(255, 99, 132)',
			data: numericData 
		}]
	},

	/* Configuration options go here*/
	options: {
		title: {
			display: true,
			text: 'Project Status'
		
		}
	}

});


/* return [{"label":"COMPLETED","value":1},{"label":"INPROGRESS","value":2},{"label":"NOTSTARTED","value":1}]*/
function decodeHtml(html) {
	var txt = document.createElement("textarea");
	txt.innerHTML = html;
	return txt.value ;
}

