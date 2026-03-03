/**
 * @Author: Pinky Gupta 
 */

async function shortenUrl() {
    const originalUrl = document.getElementById("originalUrl").value;
    if (!originalUrl) {
        alert("Please enter a URL");
        return;
    }

    try {
        const response = await fetch(
            `http://localhost:7003/shorten?originalUrl=${encodeURIComponent(originalUrl)}`,
            { method: "POST" }
        );

        if (!response.ok) {
            throw new Error("Failed to shorten URL");
        }

        const data = await response.json();
        const shortLink = `http://localhost:7003/${data.shortUrl}`;

        document.getElementById("result").innerHTML = `
            <p>Short URL:</p>
            <a href="${shortLink}" target="_blank">${shortLink}</a>
            <br><br>
            <button onclick="redirectNow('${data.shortUrl}')">
                Go to Original URL
            </button>
            <button onclick="copyToClipboard('${shortLink}')">
                Copy
            </button>
        `;
    } catch (err) {
        alert(err.message);
    }
}
function redirectNow(shortUrl) {
    // Browser redirect → backend handles mapping
    window.location.href = `http://localhost:7003/${shortUrl}`;
}
function copyToClipboard(text) {
    navigator.clipboard.writeText(text)
        .then(() => alert("Copied: " + text))
        .catch(() => alert("Copy failed"));

}
