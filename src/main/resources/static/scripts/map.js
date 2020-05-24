const LOCATION_API_PREFIX = '../api/location'

// Update map when filters change
function changeDateFiltersHandler(event = null) {
	let timestamps = []

	if (event !== null) {
		let input = event.target

		// On one date range side change, set other side to be the same if not set
		if (input.type == 'date') {
			let other = document.querySelector(`#filters [type="date"][data-range-pos="${(parseInt(input.dataset.rangePos) + 1) % 2}"]`)

			if (other.value == '') {
				other.value = input.value
			}
		}
	}

	// Parse input values to timestamps
	for (let i of [0, 1]) {
		timestamps[i] = util.parseToTimestamp(
			document.querySelector(`#filters [type="date"][data-range-pos="${i}"]`).value || undefined,
			document.querySelector(`#filters [type="time"][data-range-pos="${i}"]`).value || undefined
		)
	}

	// Update source to show new filters applied
	updateHeatmapSource(`/all?fromDate=${timestamps[0]}&toDate=${timestamps[1]}`)
}

// Focus on city
function findCityHandler(event) {
	let selected = document.querySelector('#searchBar input').value
	let coordsDic = {
		'Kraków': 	[19.945, 50.064],
		'Katowice': [19.024, 50.265],
		'Poznań': 	[16.925, 52.406],
		'Warszawa': [21.012, 52.230],
		'Wrocław': 	[17.036, 51.108]
	}

	if (selected === '') {
		util.notify('Select a city')
	} else if (selected in coordsDic) {
		map.getView().setCenter(ol.proj.fromLonLat(coordsDic[selected]));
		map.getView().setZoom(15);
	} else {
		util.notify('There is no data for given city yet')
	}
}

// Source for heatmap
const source = new ol.source.Vector({
	format: new ol.format.GeoJSON()
})

const heatmap = new ol.layer.Heatmap({
	source,
	blur: 25,
	radius: 25,
	weight: (feature) => feature.get('magnitude') / 100
})

const tile = new ol.layer.Tile({
	source: new ol.source.OSM()
})

const map = new ol.Map({
	target: 'map',
	layers: [tile, heatmap],
	view: new ol.View({
		center: ol.proj.fromLonLat([19.945, 50.064]),
		zoom: 15
	})
})

function updateHeatmapSource(uri) {
	uri = LOCATION_API_PREFIX + uri

	util.poke(uri)
		.then(() => {
			source.setUrl(uri)
			source.refresh()
		}).catch(() => {
			util.notify('Error loading sound data from server')
		})
}

function setDefaultFilters() {
	let now = new Date()
	now.setSeconds(0, 0)
	let hourAgo = new Date()
	hourAgo.setHours(hourAgo.getHours() - 1, hourAgo.getMinutes(), 0, 0)

	for (let pos of [0, 1]) {
		for (let t of ['date', 'time']) {
			document.querySelector(`#filters [type="${t}"][data-range-pos="${pos}"]`).valueAsDate = pos ? now : hourAgo
		}
	}

	changeDateFiltersHandler()
}

document.querySelectorAll('#filters input').forEach((input) => {
	input.addEventListener('change', changeDateFiltersHandler)
})

document.querySelector('#searchBar input').addEventListener('keypress', (event) => {
	if (event.key == 'Enter') {
		findCityHandler(event)
	}
})

document.querySelector('#searchBar button').addEventListener('click', findCityHandler)

setDefaultFilters()