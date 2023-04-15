import {Form, Button} from 'react-bootstrap';
import {controlIds, names, types} from '../../utils/formData';
import LabeledInput from '../LabeledInput';
import {showError, hideMessage} from '../../utils/formMessage';

export default function Registration({values, setters}) {
    function nextPhase(e) {
        e.preventDefault();
        hideMessage();
        let i, message;
        if (!values[0] || !values[1]) {
            i = values.slice(0, 3).indexOf('');
            message = 'Fill in all the fields';
            showError(i, message, '.register-first-phase');
            return;
        }
        if (values[1] !== values[2]) {
            message = 'The passwords don\'t match';
            showError(undefined, message, '.register-first-phase');
            return;
        }

        document.querySelector('.register-first-phase').style.display = 'none';
        document.querySelector('.register-second-phase').style.display = null;
    }

    return (
        <Form className='center vertical register-first-phase auth-form' onSubmit={nextPhase}>
            <h2 className='page-heading'>Registration</h2>
            {
                [0, 1, 2].map((i) => (
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
