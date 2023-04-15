import React, {useEffect, useState} from 'react';
import {Form, Button} from 'react-bootstrap';
import {useContext} from 'react';
import {Context} from '../index'
import {hideMessage, showError, showResult} from '../utils/formMessage';
import {updateUser} from '../http/userAPI';
import LabelsGroup from '../components/LabelsGroup'


function Profile() {
    const {userInfo} = useContext(Context);
    const user = userInfo.user;

    const [email, setEmail] = useState(user.email);
    const [password, setPassword] = useState('');
    const [password2, setPassword2] = useState('');
    const [name, setName] = useState(user.person.name);
    const [middleName, setMiddleName] = useState(user.person.middleName || '');
    const [surname, setSurname] = useState(user.person.surname);
    const [birthday, setBirthday] = useState('');

    const values = [email, password, password2, name, middleName, surname, birthday];
    const setters = [setEmail, setPassword, setPassword2, setName, setMiddleName, setSurname, setBirthday];
    const role = user.role || 'user';

    useEffect(() => {
        function dateToString(date) {
            const mm = date.getMonth() + 1;
            const dd = date.getDate();

            return [date.getFullYear(),
                (mm > 9 ? '' : '0') + mm,
                (dd > 9 ? '' : '0') + dd
            ].join('-');
        }

        setBirthday(dateToString(user.person.birthday))
    }, []);

    async function requestUpdate() {
        const newUser = {}
        newUser.email = email;
        newUser.role = role;

        if (values[1] !== '') {
            newUser.password = password;
        }
        const person = {};
        [person.name, person.middleName, person.surname, person.birthday] = values.slice(3);
        newUser.person = person;
        userInfo.user = await updateUser(newUser);
    }

    async function updateProfile(e) {
        e.preventDefault();
        hideMessage();
        let message = '';

        let i;
        [5, 3, 0].forEach(index => {
            if (!values[index]) {
                i = index;
            }
        });
        if (i !== undefined) {
            message = 'Fill in all the fields';
            showError(i, message, '.profile-form');
            return;
        }
        if (values[1] !== '' && values[1] !== values[2]) {
            message = 'The passwords don\'t match';
            showError(undefined, message, '.profile-form');
            return;
        }

        try {
            await requestUpdate();
        } catch (e) {
            showError(undefined, 'Server error', '.profile-form');
            return;
        }
        setPassword('');
        setPassword2('');
        showResult('Success', '.profile-form');
    }

    return (
        <Form className='mx-4 profile-form' onSubmit={updateProfile}>
            <h3 className='mb-4'>Profile</h3>
            <div className='top-center'>
                <div style={{marginRight: 30}}>
                    <LabelsGroup labelArray={[3, 4, 5, 6]} values={values} setters={setters}/>
                </div>
                <div>
                    <LabelsGroup labelArray={[0, 1, 2]} values={values} setters={setters}/>
                    <Form.Group className="my-form-line">
                        <Form.Label>Role</Form.Label>
                        <Form.Control
                            type='text'
                            className="same-width-input"
                            value={role}
                            disabled
                        />
                    </Form.Group>
                </div>

            </div>

            <div>
                <Button variant="primary m-3 rounded-button" type="submit">Save</Button>
                <span className='success-message form-message'></span>
                <span className='error-message form-message'></span>
            </div>
        </Form>
    )
}

export default Profile;