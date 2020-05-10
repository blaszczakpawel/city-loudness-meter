// Source for heatmap
let defaultUri = '../api/location/all'
util.poke(defaultUri).catch(() => util.notify('Error loading sound data from server'))
const source = new ol.source.Vector({
    url: defaultUri,
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

// Parses data and time string to timestamp
function parseToTimestamp(date, time = '00:00') {
    return Date.parse(`${date} ${time}`, 'yyyy-MM-dd HH:mm')
}

// Update map when filters change
document.querySelectorAll('#filters input').forEach((input) => {
    input.addEventListener('change', (event) => {
        let timestamps = []

        // On one date range side change, set other side to be the same if not set
        if (input.type == 'date') {
            let other = document.querySelector(`#filters [type="date"][data-range-pos="${(parseInt(input.dataset.rangePos) + 1) % 2}"]`)

            if (other.value == '') {
                other.value = input.value
            }
        }

        // Parse input values to timestamps
        for (let i in [0, 1]) {
            timestamps[i] = parseToTimestamp(
                document.querySelector(`#filters [type="date"][data-range-pos="${i}"]`).value || undefined,
                document.querySelector(`#filters [type="time"][data-range-pos="${i}"]`).value || undefined
            )
        }

        // Update source to show new filters applied
        let uri = `../api/location/all?fromDate=${timestamps[0]}&toDate=${timestamps[1]}`
        util.poke(uri).then(() => {
            source.setUrl(uri)
            source.refresh()
        }).catch(() => util.notify('Error loading sound data from server'))
    })
})

function goToCity() {
    if (document.getElementById("city").value == "Kraków") {
        map.getView().setCenter(ol.proj.fromLonLat([19.945, 50.064]));
        map.getView().setZoom(15);
    } else if (document.getElementById("city").value == "Katowice") {
        map.getView().setCenter(ol.proj.fromLonLat([19.024, 50.265]));
        map.getView().setZoom(15);
    } else if (document.getElementById("city").value == "Poznań") {
        map.getView().setCenter(ol.proj.fromLonLat([16.925, 52.406]));
        map.getView().setZoom(15);
    } else if (document.getElementById("city").value == "Warszawa") {
        map.getView().setCenter(ol.proj.fromLonLat([21.012, 52.230]));
        map.getView().setZoom(15);
    } else if (document.getElementById("city").value == "Wrocław") {
        map.getView().setCenter(ol.proj.fromLonLat([17.036, 51.108]));
        map.getView().setZoom(15);
    } else if (!document.getElementById("city").value) {
        util.notify('Select a city')
    } else {
        util.notify('There is no data for given city yet')
    }
}