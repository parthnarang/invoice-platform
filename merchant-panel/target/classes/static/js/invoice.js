
$(document).ready(function () {
    var counter = 0;

    $("#AddProduct").on("click", function () {
        var newRow = $("<tr>");
        var cols = "";

        cols += '<td><textarea class="editable input-block-level" type="text" style="overflow: hidden; overflow-wrap: break-word; resize: none; height: 40px;"/></td>';
        cols += '<td><input data-key="qty" class="editable input-mini" value="1" size="3"/></td>';
        cols += '<td><input data-key="unit_price" class="editable input-mini" value="5" size="5" ><span style="display:inline"/><span style="display:inline"> Rs</span></td>';
        cols += '<td><input data-key="unit_price" class="editable input-mini" value="5" size="5" ><span style="display:inline"/><span style="display:inline"> Rs</span> </td>';
        cols += '<td><btn href="#" class="btn btn-danger btn-sm remove-item"><i class="fa fa-trash icon-white"></i></btn></td>';

        //cols += '<td><input type="button" class="ibtnDel btn btn-md btn-danger "  value="Delete"></td>';
        newRow.append(cols);
        $('#ItemsTable').append(newRow);
        // counter++;
    });



    $('#ItemsTable').on("click", ".btn", function (event) {
        $(this).closest("tr").remove();
    });

    $("#myInput").on("keyup", function() {
        var len= $(this).val().length;
        if(len == 0){
            $('#myList').css({'display':'none'});
        }else {
            $('#myList').css({'display': 'block'});
            var value = $(this).val();
            $("#myList li").filter(function () {

                $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
            });
        }
    });

    $("ul#myList li").click(function() {
        //  alert("Clicked list." + $(this).text());

        var newRow = $("<tr>");
        var cols = "";

        cols += '<td><textarea class="editable input-block-level" type="text" value="5ff" style="overflow: hidden;  overflow-wrap: break-word; resize: none; height: 40px;" >'+$(this).text()+'</textarea></td>';
        cols += '<td><input data-key="qty" class="editable input-mini" value="1" size="3"'+ 'th:field="*{invoiceItems[0].QUANTITY}"'+'/></td>';
        cols += '<td><input data-key="unit_price" class="editable input-mini" value="5" size="5" th:field="*{invoiceItems[0].RATE}" ><span style="display:inline"/><span style="display:inline"> Rs</span></td>';
        cols += '<td><input data-key="unit_price" class="editable input-mini" value="5" size="5" th:field="*{invoiceItems[0].AMOUNT}" ><span style="display:inline"/><span style="display:inline"> Rs</span> </td>';
        cols += '<td><btn href="#" class="btn btn-danger btn-sm remove-item"><i class="fa fa-trash icon-white"></i></btn></td>';

        //cols += '<td><input type="button" class="ibtnDel btn btn-md btn-danger "  value="Delete"></td>';
        newRow.append(cols);
        $('#ItemsTable').append(newRow);
    });




});



function calculateRow(row) {
    var price = +row.find('input[name^="price"]').val();

}

function calculateGrandTotal() {
    var grandTotal = 0;
    $("table.order-list").find('input[name^="price"]').each(function () {
        grandTotal += +$(this).val();
    });
    $("#grandtotal").text(grandTotal.toFixed(2));
}

function changeFunc() {
    var selectBox = document.getElementById("search-form");
    var selectedValue = selectBox.options[selectBox.selectedIndex].value;
    var category = document.getElementById("category");
    if(selectedValue!=0)
    {
        var selectedText = selectBox.options[selectBox.selectedIndex].text;
        category.value = selectedText;
    } else {
        alert("ac");
        category.value = "";
    }


}