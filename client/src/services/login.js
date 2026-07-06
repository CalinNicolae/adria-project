import config from '@/assets/temp-config.js';

async function fetchLogin(password) {
    if (typeof password === "string") {
        return true;
    }

    const response = await fetch(`${config.localMockServer}${config.loginEndpoint}`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            'password': password
        })
    });
    return await response.json();
}

export { fetchLogin };
