document.querySelectorAll('[data-here]').forEach((span) => {
	span.innerText = location.origin
})