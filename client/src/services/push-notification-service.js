import config from '../config.js';

const subscribeOptions = {
    userVisibleOnly: true,
    applicationServerKey: urlB64ToUint8Array(config.vapidPublicKey),
};

function urlB64ToUint8Array(base64String) {
    const padding = '='.repeat((4 - base64String.length % 4) % 4);
    const base64 = (base64String + padding)
        .replace(/-/g, '+')
        .replace(/_/g, '/');

    const rawData = window.atob(base64);
    const outputArray = new Uint8Array(rawData.length);

    for (let i = 0; i < rawData.length; ++i) {
        outputArray[i] = rawData.charCodeAt(i);
    }
    return outputArray;
}

async function enablePushNotifications() {
    const permission = await Notification.requestPermission();
    if (permission === "granted") {
        registerPush();
    } else {
        console.error("Permission for notifications was denied");
    }
}

async function registerPush() {
    const registration = await navigator.serviceWorker.ready;
    let subscription = await registration.pushManager.getSubscription();

    if (!subscription) {
        subscription = await registration.pushManager.subscribe(subscribeOptions);
        postSubscriptionToServer(subscription);
    }

}

async function isAlreadySubscribed() {
    const registration = await navigator.serviceWorker.ready;
    const subscription = await registration.pushManager.getSubscription();
    return subscription !== null;
}

function postSubscriptionToServer(subscription) {
    try {
        fetch(`${config.serverBaseURL}/api/subscribe`, {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify(subscription)
        });

    } catch (error) {
        console.error('Error sending subscription to server:', error);
    }
}

export { enablePushNotifications, isAlreadySubscribed };
