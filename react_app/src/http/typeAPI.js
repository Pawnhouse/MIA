import {$authHost} from '.';

export async function getTypes() {
    const {data} = await $authHost.get('/api/type');
    return data;
}


