export function isUserLoggedIn(): boolean {
    const token = sessionStorage.getItem('auth_token');
    return token != null;
}
