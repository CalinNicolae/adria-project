async function getConfig() {
    const rootURL = window.location.origin;
    const baseURL = import.meta.env.BASE_URL;
    return await fetch(`${rootURL}${baseURL}/config.json`)
        .then(res => res.json())
        .catch(err => console.error("Could not fetch config.json:", err));
}

const config = await getConfig();
const siteBaseUrl = `${config.year ? config.year + '/' : ''}${config.group ? config.group + '/' : ''}`;
const apiBaseUrl = `${config.host ? config.host + '/' : ''}${siteBaseUrl}api/`;
// CHANGE THIS TO YOUR IOT DEVICE URL
const IOT_DEVICE_URL = "http://172.20.10.2:5000/"; //NOSONAR This is the Raspi IP address

export { siteBaseUrl, apiBaseUrl, IOT_DEVICE_URL };
