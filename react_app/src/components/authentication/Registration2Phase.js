import {useContext} from 'react';
import {Button, Form} from 'react-bootstrap';
import {controlIds, names, types} from '../../utils/formData';
import LabeledInput from '../LabeledInput';
import {register} from '../../http/userAPI';
import {Context} from '../../index';
import {hideMessage, showError} from '../../utils/formMessage';

export default function Registration2Phase({values, setters}) {
    const {userInfo} = useContext(Context);

    async function signUp() {
        hideMessage();
        let i, message;
        if (!values[3] || !values[5] || !values[6]) {
            if (!values[3]) {
                i = 3;
                message = 'Enter first name';
            } else if (!values[5]) {
                i = 5;
                message = 'Enter surname';
            } else {
                i = 6;
                message = 'Enter birthday';
            }
            showError(i, message, '.register-second-phase');
            return;
        }

        try {
            userInfo.user = await register(values[0], values[1], values[3], values[4], values[5], values[6]);
            userInfo.isAuthenticated = true;
        } catch (e) {
            message = e.response?.data?.message ?? 'Server error';
            showError(undefined, message, '.register-second-phase');
        }
    }

    return (
        <Form className='center vertical register-second-phase auth-form' style={{display: 'none'}}>
            <h2 className='page-heading'>Registration</h2>
            {
                [3, 4, 5, 6].map((i) => (
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
            <Button variant='primary m-3 rounded-button' onClick={signUp}>Sign up</Button>
            <span className='error-message form-message'></span>
        </Form>
    )
}