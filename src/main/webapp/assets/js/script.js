$(document).ready(function(){
var states = []

var myMethod = $('#product').keyup(function(){
	
		var enteredText = $(this).val();
		
		var query = 
        {
            "query": {
              "match": {
                "_all": enteredText
              }
            }
          }
		
		if(query != ''){
			$.ajax({
				url:"http://localhost:9200/addresses2/_search",
				type : 'POST',
				success:function(data)
				{
                    autoPop(data);
				},
				error: function(data) {
					alert("Fail");
				},
				data : JSON.stringify(query)
			});
		}
	})
			
function autoPop(data){
	console.log("This is the autoPop func. length of array is: " + data.hits.hits.length);
	var x;
	var length = data.hits.hits.length;
	
	if(data.hits.hits.length != 0) {
		console.log("data.hits.hits is ========= " + data.hits.hits);
		
		if(data.hits.hits[0]._source.buildingNumber != undefined){
		
			for( x = 0; x < length; x++){
				$("#tbody").append( $("<tr><td>"+ data.hits.hits[x]._source.buildingNumber +
									   "</td><td>"+ data.hits.hits[x]._source.roadwayName + " " +data.hits.hits[0]._source.roadwayType +
									   "</td><td>" + data.hits.hits[x]._source.city +
									   "</td><td>" + data.hits.hits[x]._source.state +
									   "</td><td>" +data.hits.hits[x]._source.country +
									   "</td></tr>") );
			}
		}else{
			for( x = 0; x < length; x++){
				$("#tbody").append( $( "<tr><td>"+ data.hits.hits[x]._source.buildingnum +
									   "</td><td>"+ data.hits.hits[x]._source.roadwayname + " " +data.hits.hits[0]._source.roadwaytype +
									   "</td><td>" + data.hits.hits[x]._source.city +
									   "</td><td>" + data.hits.hits[x]._source.state +
									   "</td><td>" +data.hits.hits[x]._source.country +
									   "</td></tr>"	) );
			}
		}
	}
	
	if($('#product').val() == ""){
		console.log("NULL SEARCH BAR");
		$("#tbody tr").remove();
	}
}

});


