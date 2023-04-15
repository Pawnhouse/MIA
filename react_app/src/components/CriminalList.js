import React, {useEffect, useState} from "react";
import {Button, Container, Row} from "react-bootstrap";
import {useNavigate} from "react-router-dom";
import {CRIMINAL_PATH} from "../utils/paths";
import {getCriminals} from "../http/criminalAPI";
import {generateName} from "../utils/formMessage";

export default function CriminalList() {
    const [criminals, setCriminals] = useState([]);
    const [isLoaded, setIsLoaded] = useState(false);
    useEffect(() => {
        getCriminals().then((criminals) => {
            setCriminals(criminals);
            setIsLoaded(true);
        })
    }, []);
    const navigate = useNavigate();
    if (!isLoaded) {
        return <div/>
    }

    return (
        <Container className='col-md'>
            <h3 className='mt-4'>
                Criminals
                <Button className='mx-3' onClick={() => navigate(CRIMINAL_PATH)}>Add</Button>
            </h3>

            <Row>
                {
                    criminals.map((criminal, index) => {
                        return <div key={criminal.id} className='w-25 p-3 d-flex'
                                    style={{background: Math.floor(index / 4) % 2 ? '#edf4f6' : 'white'}}>
                            <Container className='ms-3 clickable' onClick={() => navigate(CRIMINAL_PATH + criminal.id)}>

                                {generateName(criminal)}
                            </Container>
                        </div>
                    })
                }
            </Row>

        </Container>
    )
}
