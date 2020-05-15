const util = {
	poke: (uri) => {		
		return new Promise((resolve, reject) => {
			let xhr = new XMLHttpRequest()
			xhr.open('GET', uri)

			xhr.onload = event => {
				if (xhr.status == 200) {
					resolve(xhr.status)
				} else {
					reject(xhr.status)
				}
			}

			xhr.send(null)
		})
	},

	notify: (text) => {
		let notification = document.createElement('div'),
			wrapper = document.querySelector('#notificationWrapper')
		notification.innerText = text
		wrapper.appendChild(notification)

		let timeout = setTimeout(() => {
			wrapper.removeChild(notification)
		}, 5e4)

		notification.addEventListener('click', () => {
			wrapper.removeChild(notification)
			clearTimeout(timeout)
		})

	},

	// Parses data and time string to timestamp
	parseToTimestamp: (date, time = '00:00') => {
		return Date.parse(`${date} ${time}`, 'yyyy-MM-dd HH:mm')
	}
}