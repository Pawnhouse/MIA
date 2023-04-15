import {useContext} from 'react';
import {Form, Button} from 'react-bootstrap';
import {controlIds, names, types} from '../../utils/formData';
import LabeledInput from '../LabeledInput';
import {login} from '../../http/userAPI';
import {Context} from '../../index';
import {showError, hideMessage} from '../../utils/formMessage';

export default function SignInForm({values, setters}) {
    const {userInfo} = useContext(Context);

    async function signIn(e) {
        e.preventDefault();
        hideMessage();
        let i, message;
        if (!values[0] || !values[1]) {
            i = values.slice(0, 2).indexOf('');
            message = 'Fill in all the fields';
            showError(i, message, '.sign-in-form');
            return;
        }

        try {
            userInfo.user = await login(values[0], values[1]);
            userInfo.isAuthenticated = true;
        } catch (e) {
            message = e.response?.data?.message ?? 'Server error';
            showError(undefined, message, '.sign-in-form');
        }
    }

    return (
        <Form className='center vertical sign-in-form auth-form' onSubmit={signIn}>
            <h2 className='page-heading'>Sign in to continue</h2>
            {
                [0, 1].map((i) => (
                    <LabeledInput
                        controlId={controlIds[i]}
                        name={names[i]}
                        type={types[i]}
                        key={i}
                        value={values[i]}
                        onChange={e => setters[i](e.target.value)}
                    />
                ))
            }
            <Button variant="primary m-3 rounded-button" type="submit">Continue</Button>
            <span className='error-message form-message'></span>
        </Form>
    )
}
