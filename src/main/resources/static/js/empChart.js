var dataStr = decodeHtml(empData);
var jsonArray = JSON.parse(dataStr);


var arrLength = jsonArray.length;
var labData = [];
var numData = [];

//var labelData2 = [];

for(var i=0 ; i < arrLength; i++)
{   
	labData[i] = jsonArray[i].label;
	numData[i] = jsonArray[i].value;

	//labelData2[i] = chartJsonArray1[i].label;	
}

/* For a pie chart */
new Chart(document.getElementById("myPieChartEmp"),{
    type: 'pie',
 /* The data for our dataset*/
    data:{
        labels: labData,
        datasets: [{
            label: 'My First dataset',
            backgroundColor: ["#2E86C1", "#C0392B", "#239B56","#27AE60","#F1C40F","#1C2833","#4A235A ","#76D7C4","#F2D7D5"],
            borderColor: 'rgb(255, 99, 132)',
            data: numData
        }]
    },

    /* Configuration options go here*/
    options: {
    	title:{
    	display:true,
    	text:'Employee Status'
        }
    }
});


/* return [{"label":"COMPLETED","value":1},{"label":"INPROGRESS","value":2},{"label":"NOTSTARTED","value":1}]*/
function decodeHtml(html) {
	var txt= document.createElement("textarea");
	txt.innerHTML=html;
	return txt.value;
}

