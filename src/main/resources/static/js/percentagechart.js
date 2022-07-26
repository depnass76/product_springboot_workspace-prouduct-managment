

var arrayLength = chartJsonArray.length;
let sum = 0;
var numericData = [];
var labelData = [];

for(var i=0 ; i < arrayLength; i++)
{
    numericData[i] =chartJsonArray[i].value;
	labelData[i] = chartJsonArray[i].label;
	
}

var ctxP = document.getElementById("myPieChart").getContext('2d');
var myPieChart = new Chart(ctxP, {
	plugins: [ChartDataLabels],
	type: 'pie',
	data: {
		labels: labelData,
		datasets: [{
			label: 'My First dataset',
			backgroundColor: ["#2E86C1", "#C0392B", "#239B56"],
			borderColor: 'rgb(255, 99, 132)',
			data: numericData
		}]
	},
	options: {
		responsive: true,
		legend: {
			position: 'right',
			labels: {
				padding: 20,
				boxWidth: 10
			}
		},
		plugins: {
			datalabels: {
				formatter: (value, ctx) => {
					let sum = 0;
					let dataArr = ctx.chart.data.datasets[0].data;
					dataArr.map(data => {
						sum += data;
					});
					let percentage = (value * 100 / sum).toFixed(2) + "%";
					return percentage;
				},
				color: 'white',
				labels: {
					title: {
						font: {
							size: '16'
						}
					}
				}
			}
		}
	}
});