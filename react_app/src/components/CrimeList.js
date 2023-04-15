import React, {useEffect, useState} from "react";
import {Col, Container, Row} from "react-bootstrap";
import {getCrimes} from "../http/crimeAPI";
import {useNavigate} from "react-router-dom";
import {CRIME_PATH} from "../utils/paths";

export default function CrimeList() {
    const [crimes, setCrimes] = useState([]);
    const [isLoaded, setIsLoaded] = useState(false);
    useEffect(() => {
        getCrimes().then((complaints) => {
            setCrimes(complaints);
            setIsLoaded(true);
        })
    }, []);
    const navigate = useNavigate();
    if (!isLoaded) {
        return <div/>
    }

    return (
        <Container className='col-md'>
            <h3 className='mt-4'>Crimes</h3>
            {
                crimes.map((crime, index) => {
                    return <div key={crime.id} className='w-100 p-3 d-flex'
                                style={{background: index % 2 ? '#edf4f6' : 'white'}}>
                        <Container className='ms-3 clickable' onClick={() => navigate(CRIME_PATH + crime.id)}>
                            <Row>
                                <Col md='auto'>
                                    <b>
                                        {crime.id}
                                    </b>
                                </Col>
                                <Col>
                                    {crime.description}
                                </Col>
                            </Row>
                        </Container>
                    </div>
                })
            }
        </Container>
    )
}
