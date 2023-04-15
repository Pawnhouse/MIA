import {$authHost} from '.';

export async function getCriminals() {
    const {data} = await $authHost.get('/api/criminal');
    return data;
}

export async function setCriminals(criminalIds, crimeId) {
    await $authHost.post(
        '/api/criminal/add-to-crime',
        {crimeId, criminalIds}
    );
}

export async function addCriminal(criminal) {
    const {data} = await $authHost.post(
        '/api/criminal',
        criminal
    );
    return data;
}

export async function updateCriminal(criminal) {
    await $authHost.put(
        '/api/criminal',
        criminal
    );
}