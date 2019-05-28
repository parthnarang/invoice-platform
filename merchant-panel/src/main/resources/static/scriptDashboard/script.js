// Our labels along the x-axis
var years = [];
var i,j;
for (i = 0; i <=totalAmountList.length+3; i+=3) {
    years[i] = i;
}
var years2 = [];
for (i = 0; i <=totalTransactionList.length+3; i+=3) {
    years2[i] = i;
}

var dataRevenue = [];
for (i = 0,j=0; i <totalAmountList.length-2; i++) {
    dataRevenue[j] = (totalAmountList[i]+totalAmountList[i+1]+totalAmountList[i+2])/3;
    j += 1;
}
var totTransactions = [];
for (i = 0,j=0; i <totalTransactionList.length-2; i++) {
    totTransactions[j] = totalTransactionList[i]+totalTransactionList[i+1]+totalTransactionList[i+2];
    j += 1;
}

var totTxn = document.getElementById("total-transactions");
var totAmt = document.getElementById("total-revenue");
totTxn.textContent = invoiceListSize.toString();
totAmt.textContent = totalAmount.toString();

Chart.defaults.global.defaultFontFamily = 'sans-serif';
var ctx = document.getElementById("txn-amt");
var myChart = new Chart(ctx, {
    type: 'line',
    data: {
        labels: years,
        datasets: [
            {
                data: dataRevenue,
                label: "Total Revenue Generated This Month(Last 30 Days)",
                borderColor: "#3cbc4c",
                fill: false
            }

        ]
    },
    options:{
        scales:{
            xAxes: [{
                ticks: {
                    display: false //this will remove only the label
                }
            }]
        }
    }
});

var ctx1 = document.getElementById("txn-cnt");
var myChart1 = new Chart(ctx1, {
    type: 'line',
    data: {
        labels: years2,
        datasets: [
            {
                data: totTransactions,
                label: "Number of Transactions This Month(Last 30 Days)",
                borderColor: "#3cbc4c",
                backgroundColor: "#3cbc4c",
                fill: false
            }
        ]
    },
    options:{
        scales:{
            xAxes: [{
                ticks: {
                    display: false //this will remove only the label
                }
            }]
        }
    }
});