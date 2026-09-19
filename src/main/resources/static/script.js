async function shortenUrl() {

    const input = document.getElementById("urlInput");
    const message = document.getElementById("message");
    const result = document.getElementById("result");

    const originalUrl = input.value.trim();

    if (!originalUrl) {
        message.textContent = "Please enter a URL.";
        return;
    }

    try {

        message.textContent = "Creating short URL...";
        result.classList.add("hidden");

        const response = await fetch("/api/urls/shorten", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                originalUrl: originalUrl
            })
        });

        if (!response.ok) {

            const errorText = await response.text();

            message.textContent =
                errorText || "Unable to shorten URL.";

            return;
        }

        const data = await response.json();

        document.getElementById("shortUrl").textContent =
            data.shortUrl;

        document.getElementById("shortUrl").href =
            data.shortUrl;

        result.classList.remove("hidden");

        message.textContent = "URL shortened successfully.";

        input.value = "";

    } catch (error) {

        console.error(error);

        message.textContent =
            "Unable to connect to the server.";
    }
}


async function getAnalytics() {

    const input = document.getElementById("analyticsInput");

    const analytics = document.getElementById("analytics");

    const shortCode = input.value.trim();

    if (!shortCode) {
        alert("Please enter a short code.");
        return;
    }

    try {

        const response = await fetch(
            `/api/analytics/${encodeURIComponent(shortCode)}`
        );

        if (!response.ok) {

            alert("Short URL not found.");
            analytics.classList.add("hidden");

            return;
        }

        const data = await response.json();

        document.getElementById("originalUrl").textContent =
            data.originalUrl;

        document.getElementById("shortCode").textContent =
            data.shortCode;

        document.getElementById("clicks").textContent =
            data.clicks;

        document.getElementById("createdAt").textContent =
            new Date(data.createdAt).toLocaleString();

        analytics.classList.remove("hidden");

    } catch (error) {

        console.error(error);

        alert("Unable to connect to the server.");
    }
}


function copyUrl() {

    const shortUrl =
        document.getElementById("shortUrl").textContent;

    navigator.clipboard.writeText(shortUrl)
        .then(() => {
            alert("Short URL copied!");
        })
        .catch(() => {
            alert("Unable to copy URL.");
        });
}