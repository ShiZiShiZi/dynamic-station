const rowColor = [ 'success', 'info', 'warning', 'danger' ]

function addElementA(station, index) {
	var parent = document.getElementById("listAllStation")
	var linkA = document.createElement("a")

	linkA.setAttribute("class", "list-group-item list-group-item-"
			+ rowColor[index])
	linkA.innerHTML = "<span class='badge'>" + station.station_id + "</span>"
			+ station.name

	linkA.onclick = function() {
		location.replace('DynamicStation.html?station_id=' + station.station_id
				+ '&name=' + station.name);
	}
	parent.appendChild(linkA)

}

function getStationID() {
	var paras = location.search
	var result = paras.match(/[^\?&]*=[^&]*/g)
	paras = {}
	for (i in result) {
		
		var temp = result[i].split('=')
		paras[temp[0]] = temp[1].replace(/%20/g, ' ')
	}
	return paras
}

function deleteAllChilds(parent) {
	var el = document.getElementById(parent);
	var childs = el.childNodes;
	for (var i = childs.length - 1; i >= 0; i--) {
		el.removeChild(childs[i]);
	}
}