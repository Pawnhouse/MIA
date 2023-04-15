import React, {useEffect, useState} from "react";
import {Container, Form} from "react-bootstrap";
import {getCrimes} from "../http/crimeAPI";
import {useParams} from "react-router-dom";
import {getTypes} from "../http/typeAPI";
import {ReactComponent as Edit} from '../img/edit.svg';
import SetCriminalsModal from "../components/SetCriminalsModal";
import {generateName} from "../utils/formMessage";

export default function Crime() {
    const params = useParams();
    const crimeId = +params.id;
    const [crime, setCrime] = useState(null);
    const [types, setTypes] = useState([]);
    const [isLoaded, setIsLoaded] = useState(false);
    const [isEdit, setIsEdit] = useState(false);

    useEffect(() => {
        getCrimes().then((crimes) => {
            const result = crimes.find((crime) => crimeId === crime.id)
            if (result) {
                setCrime(result);
            }
            setIsLoaded(true);
        });
        getTypes().then((types) => setTypes(types));
    }, []);

    if (!isLoaded) {
        return <div/>
    }
    if (!crime) {
        return <p className='error-not-found'>Not found</p>;
    }
    const criminalNames = crime.criminals.map((criminal) => generateName(criminal));
    const criminalList = criminalNames.join(', ');
    return (
        <Container className='col-md'>
            <h3 className='mt-3'>Crime №{crime.id}</h3>

            <Form.Select
                className='g-0 my-3'
                style={{width: '200px'}}
                value={crime.type.id}
                readOnly
            >
                {
                    types.map((type) => (
                        <option key={type.id} value={type.id} disabled>{type.name}</option>
                    ))
                }
            </Form.Select>
            <p>{crime.description}</p>
            <p><b>Participants:</b></p>
            {criminalList}
            <button className='ms-3' onClick={() => setIsEdit(true)}>
                <Edit/>
            </button>
            <SetCriminalsModal
                show={isEdit}
                crime={crime}
                setCrime={setCrime}
                onHide={() => setIsEdit(false)}
            />
        </Container>
    )
}
