function menu()
{
    document.write( `
        <div id="panel">
            <img src="assets/logo.svg">
            <ul id="menu">
                <li><a href="/" data-title="Homepage - map"><img src="assets/homepage.svg" alt="Map"></a>
                <li><a href="about" data-title="About us"><img src="assets/about.svg" alt="About"></a>
                <li><a href="contribute" data-title="Contribute - Help us to collect data!"><img src="assets/networking.svg" alt="Contribute"></a>
                <li><a href="APIGuide" data-title="Documentation"><img src="assets/document.svg" alt="Documentation"></a>
            </ul>
        </div>
    `);
}