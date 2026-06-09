// src/services/chrome.ts

export async function getActiveTab() {
  const [tab] = await chrome.tabs.query({
    active: true,
    currentWindow: true
  });

  return tab;
}

export async function sendScanRequest() {
  const tab = await getActiveTab();

  if (!tab.id) return;

  return chrome.tabs.sendMessage(tab.id, {
    action: "SCAN_JOBS"
  });
}