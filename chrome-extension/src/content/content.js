console.log("[JobFilter] content.js loaded");

chrome.runtime.onMessage.addListener((message, sender, sendResponse) => {
    console.log("[JobFilter] Message received:", message);

    switch (message.action) {
        case "SCAN_JOBS": {
            const jobs = scanLinkedInJobs();

            sendResponse({
                success: true,
                count: jobs.length,
                jobs
            });

            break;
        }

        default:
            sendResponse({
                success: false,
                error: "Unknown action"
            });

            break;
    }

    return true;
});

function scanLinkedInJobs() {
    const container = document.querySelector(
        '[data-testid="lazy-column"][data-component-type="LazyColumn"]'
    );

    if (!container) {
        console.warn("[JobFilter] Results container not found");
        return [];
    }

    const cards = [...container.querySelectorAll('div[role="button"][componentkey]')]
        .filter(card => {
            const text = card.innerText || "";

            return (
                text.includes("Posted") &&
                !text.includes("Are these results helpful?") &&
                !text.includes("Get job alerts")
            );
        });

    const jobs = cards.map(extractJobFromCard);

    console.group("[JobFilter] Scan Results");
    console.log("Cards found:", cards.length);
    console.table(jobs);
    console.groupEnd();

    return jobs;
}

function extractJobFromCard(card) {
    debugger;

    const pTags = [...card.querySelectorAll("p")];

    const title = extractTitleFromTitleP(pTags[0]);
    const company = cleanText(pTags[1]?.innerText || "");
    const location = cleanText(pTags[2]?.innerText || "");

    const postedDateRaw = pTags
        .map(p => p.innerText)
        .find(text => /posted/i.test(text)) || "";

    return {
        title,
        company,
        location,
        postedDate: cleanPostedDate(postedDateRaw),
        url: window.location.href
    };
}

function cleanText(text) {
    return text
        .replace(/\s+/g, " ")
        .trim();
}

function extractTitleFromTitleP(titleP) {
    if (!titleP) return "";

    const visibleTitleSpan = titleP.querySelector('span[aria-hidden="true"]');

    if (visibleTitleSpan) {
        return cleanText(visibleTitleSpan.innerText);
    }

    return cleanTitle(titleP.innerText);
}

function cleanTitle(text) {
    const parts = text
        .split("\n")
        .map(cleanText)
        .filter(Boolean);

    const visibleTitle = parts[parts.length - 1] || text;

    return visibleTitle
        .replace(/^Selected,\s*/i, "")
        .replace(/\s*\(Verified job\)\s*/i, "")
        .trim();
}

function cleanPostedDate(text) {
    return text
        .split("\n")
        .map(cleanText)
        .filter(Boolean)[0] || "";
}


async function resolveJobUrlForCard(card) {
    card.scrollIntoView({ block: "center" });

    const beforeUrl = window.location.href;

    card.click();

    const jobId = await waitForJobIdChange(beforeUrl, 2000);

    if (!jobId) {
        return null;
    }

    return `https://www.linkedin.com/jobs/view/${jobId}/`;
}

function waitForJobIdChange(beforeUrl, timeoutMs) {
    return new Promise(resolve => {
        const start = Date.now();

        const timer = setInterval(() => {
            const currentUrl = window.location.href;
            const url = new URL(currentUrl);
            const jobId = url.searchParams.get("currentJobId");

            if (currentUrl !== beforeUrl && jobId) {
                clearInterval(timer);
                resolve(jobId);
            }

            if (Date.now() - start > timeoutMs) {
                clearInterval(timer);
                resolve(null);
            }
        }, 100);
    });
}