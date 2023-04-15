import {$authHost} from '.';


export async function getComplaints() {
    const {data} = await $authHost.get('/api/complaint');
    data.forEach((complaint) => {

        complaint.sent = new Date(complaint.sent);
    });
    return data;
}

export async function sendComplaint(description) {
    await $authHost.post(
        '/api/complaint',
        {description}
    );
}

export async function deleteComplaint(id) {
    await $authHost.delete('/api/complaint/' + id);
}
