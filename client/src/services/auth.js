import {fetchLogin} from '@/services/login.js';

async function handleAuth(adriaId, next) {
    try {
        const isAuthenticated = await checkAuthentication(adriaId);
        if (isAuthenticated) {
            next();
        } else {
            next({path: '/login'});
        }
    } catch (error) {
        console.error("Authentication error:", error);
        next({path: '/login'});
    }
}


async function checkAuthentication(adriaId) {
    const response = await fetchLogin(adriaId);
    return response.Success;
}

export {handleAuth};
