import React, {useEffect, useState} from 'react';
import {Form, Button, FormControl, Container} from 'react-bootstrap';
import {hideMessage, setDefaultBorder, showError, showResult} from '../utils/formMessage';
import LabelsGroup from '../components/LabelsGroup'
import {addCriminal, getCriminals, updateCriminal} from "../http/criminalAPI";
import {useNavigate, useParams} from "react-router-dom";
import {CRIMINAL_PATH} from "../utils/paths";

export default function Criminal() {
    const params = useParams();
    const criminalId = params.id == null ? null : +params.id;
    const [isCreate, setIsCreate] = useState(criminalId == null);
    const [isLoaded, setIsLoaded] = useState(false);
    const [criminal, setCriminal] = useState(null);

    const [id, setId] = useState(criminalId);
    const [nickname, setNickname] = useState('');
    const [biography, setBiography] = useState('');
    const [name, setName] = useState('');
    const [middleName, setMiddleName] = useState('');
    const [surname, setSurname] = useState('');
    const [birthday, setBirthday] = useState('');

    const values = [null, null, null, name, middleName, surname, birthday];
    const setters = [null, null, null, setName, setMiddleName, setSurname, setBirthday];
    const navigate = useNavigate();
    useEffect(() => {
        function dateToString(date) {
            const mm = date.getMonth() + 1;
            const dd = date.getDate();

            return [date.getFullYear(),
                (mm > 9 ? '' : '0') + mm,
                (dd > 9 ? '' : '0') + dd
            ].join('-');
        }

        if (isLoaded) {
            return;
        }
        if (criminalId === null) {
            setIsLoaded(true);
            return;
        }

        getCriminals().then((criminals) => {
            const result = criminals.find((criminal) => criminalId === criminal.id);
            if (result) {
                setCriminal(result);
                setNickname(result.nickname || '');
                setBiography(result.biography);
                setName(result.person.name);
                setMiddleName(result.person.middleName);
                setSurname(result.person.surname);
                setBirthday(dateToString(new Date(result.person.birthday)));
            }
            setIsLoaded(true);
        });
    }, []);
    if (!isLoaded) {
        return <div/>
    }
    if (!criminal && !isCreate) {
        return <p className='error-not-found'>Not found</p>;
    }

    async function processCriminal(e) {
        e.preventDefault();
        hideMessage();

        try {
            const newCriminal = {id}
            newCriminal.nickname = nickname;
            newCriminal.biography = biography;

            const person = {};
            [person.name, person.middleName, person.surname, person.birthday] = values.slice(3);
            newCriminal.person = person;
            if (isCreate) {
                const result = await addCriminal(newCriminal);
                setId(result);
                setCriminal(newCriminal)
                navigate(CRIMINAL_PATH + result);
            } else {
                await updateCriminal(newCriminal);
            }
        } catch (e) {
            showError(undefined, 'Server error', '.criminal-form');
            return;
        }
        const message = isCreate ? 'Criminal created' : 'Criminal data updated';
        showResult(message, '.criminal-form');
        setIsCreate(false);
    }

    return (
        <Container>
            <Form className='mx-4 criminal-form' onSubmit={processCriminal}>
                <h3 className='mb-4'>Criminal</h3>
                <div className='top-center'>
                    <div style={{marginRight: 30}}>

                        <LabelsGroup labelArray={[3, 4, 5]} values={values} setters={setters}/>

                    </div>
                    <div>
                        <LabelsGroup labelArray={[6]} values={values} setters={setters}/>
                        <Form.Group className="my-form-line" controlId='nickname'>
                            <Form.Label>Nickname</Form.Label>
                            <Form.Control
                                name='nickname'
                                type='text'
                                className="same-width-input"
                                value={nickname}
                                onChange={e => setNickname(e.target.value)}
                                onFocus={setDefaultBorder('nickname')}
                            />
                        </Form.Group>
                    </div>

                </div>
                <Form.Label>Biography</Form.Label>
                <FormControl style={{maxWidth: 1000}} as="textarea" value={biography}
                             onChange={e => setBiography(e.target.value)}/>
                <div>
                    <Button variant="primary m-3 rounded-button" type="submit">Save</Button>
                    <span className='success-message form-message'></span>
                    <span className='error-message form-message'></span>
                </div>
            </Form>
        </Container>

    )
}