import {$host, $authHost} from ".";
import jwt_decode from 'jwt-decode';

function processData(token) {
    const user = jwt_decode(token);
    user.person.birthday = new Date(user.person.birthday);
    return user;
}

export async function register(
    email, password, name, middleName, surname, birthday
) {
    const {data} = await $host.post(
        '/api/account/sign-up',
        {
            email,
            password,
            person: {
                name,
                middleName,
                surname,
                birthday
            }
        }
    );
    localStorage.setItem('token', data);
    return processData(data);
}

export async function login(email, password) {
    const {data} = await $host.post(
        '/api/account/sign-in',
        {email, password}
    );
    localStorage.setItem('token', data);
    return processData(data);
}

export async function updateUser(user) {
    const {data} = await $authHost.put('/api/account', user);
    localStorage.setItem('token', data);
    return processData(data);
}

