import {useState} from 'react';
import {Container} from 'react-bootstrap';
import SignInForm from '../components/authentication/SignIn';
import Registration from '../components/authentication/Registration';
import Registration2Phase from '../components/authentication/Registration2Phase';
import Tab from 'react-bootstrap/Tab';
import Tabs from 'react-bootstrap/Tabs';


function Auth() {
    const [key, setKey] = useState('sign-in');

    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [password2, setPassword2] = useState('');
    const [firstName, setFirstName] = useState('');
    const [middleName, setMiddleName] = useState('');
    const [surname, setSurname] = useState('');
    const [birthday, setBirthday] = useState('');

    const values = [email, password, password2, firstName, middleName, surname, birthday];
    const setters = [setEmail, setPassword, setPassword2, setFirstName, setMiddleName, setSurname, setBirthday];

    return (
        <Container className='center' style={{width: '60%'}}>
            <div>
                <Tabs
                    activeKey={key}
                    onSelect={(k) => {
                        setKey(k)
                        setters.forEach((setter) => setter(''));
                    }}
                    id='auth-tabs'
                    className='mb-3'
                >
                    <Tab eventKey='sign-in' title='Sign in'>
                        <SignInForm values={values} setters={setters}/>
                    </Tab>
                    <Tab eventKey='sign-up' title='Sign up'>
                        <div>
                            <Registration values={values} setters={setters}/>
                            <Registration2Phase values={values} setters={setters}/>
                        </div>
                    </Tab>
                </Tabs>
            </div>

        </Container>
    );
}

export default Auth;