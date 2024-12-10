import http from 'k6/http';
import { check, group, sleep } from 'k6';

const BASE_URL = 'https://reqres.in/api';

export let options = {
    stages: [
        { duration: '5s', target: 10 },
        { duration: '5s', target: 10 },
        { duration: '5s', target: 0 },
    ],
};


export default function() {
    group('Test GET User', function() {
        let res = http.get(`${BASE_URL}/users/2`);
        check(res, {
            'GET Response Code is 200': (r) => r.status === 200,
            'GET User ID is 2': (r) => JSON.parse(r.body).data.id === 2,
            'GET User Name is Janet': (r) => JSON.parse(r.body).data.first_name === 'Janet',
        });
        sleep(1);
    });

    group('Test POST Create User', function() {
        let payload = JSON.stringify({ name: 'John', job: 'Engineer' });
        let headers = { 'Content-Type': 'application/json' };

        let res = http.post(`${BASE_URL}/users`, payload, { headers });
        check(res, {
            'POST Response Code is 201': (r) => r.status === 201,
            'POST User Name is John': (r) => JSON.parse(r.body).name === 'John',
            'POST Job is Engineer': (r) => JSON.parse(r.body).job === 'Engineer',
        });
        sleep(1);
    });

    group('Test PUT Update User', function() {
        let payload = JSON.stringify({ name: 'John', job: 'Manager' });
        let headers = { 'Content-Type': 'application/json' };

        let res = http.put(`${BASE_URL}/users/2`, payload, { headers });
        check(res, {
            'PUT Response Code is 200': (r) => r.status === 200,
            'PUT User Name is John': (r) => JSON.parse(r.body).name === 'John',
            'PUT Job is Manager': (r) => JSON.parse(r.body).job === 'Manager',
        });
        sleep(1);
    });

    group('Test DELETE User', function() {
        let res = http.del(`${BASE_URL}/users/2`);
        check(res, {
            'DELETE Response Code is 204': (r) => r.status === 204,
        });
        sleep(1);
    });

    group('Test GET User List', function() {
        let res = http.get(`${BASE_URL}/users`);
        check(res, {
            'GET User List Response Code is 200': (r) => r.status === 200,
            'User List is not empty': (r) => JSON.parse(r.body).data.length > 0,
        });
        sleep(1);
    });
}
