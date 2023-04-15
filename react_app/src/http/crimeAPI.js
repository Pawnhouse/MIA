import {$authHost} from '.';

export async function getCrimes() {
    const {data} = await $authHost.get('/api/crime');
    return data;
}

export async function addCrime(crime) {
    await $authHost.post(
        '/api/crime',
        crime
    );
}
