import React, {useEffect, useState} from "react";
import {Button, Form, Modal} from "react-bootstrap"
import {getCriminals, setCriminals} from "../http/criminalAPI";
import {generateName} from "../utils/formMessage";


function SetCriminalsModal({crime, setCrime, show, onHide}) {
    const [allCriminals, setAllCriminals] = useState('');
    const [isLoaded, setIsLoaded] = useState(false);
    const [checkedCriminals, setCheckedCriminals] = useState([]);

    useEffect(() => {
        getCriminals().then((criminals) => {
            setAllCriminals(criminals);
            const criminalsMap = [];
            crime.criminals.forEach((criminal) => criminalsMap[criminal.id] = true);
            setCheckedCriminals(criminalsMap);
            setIsLoaded(true);
        });
    }, []);

    if (!isLoaded) {
        return <div/>
    }

    function addCriminals() {
        const criminalIds = checkedCriminals.flatMap((value, index) => value ? index : []);
        setCriminals(criminalIds, crime.id).then(() => {
            setCrime({...crime, criminals: allCriminals.filter(({id}) => criminalIds.includes(id))});
            onHide();
        });
    }

    return (
        <Modal
            show={show}
            onHide={onHide}
            centered
        >
            <Modal.Header closeButton>
                <Modal.Title id="contained-modal-title-vcenter">
                    Choose criminals
                </Modal.Title>
            </Modal.Header>
            <Modal.Body>
                <Form>
                    {
                        allCriminals.map((criminal) => (
                            <Form.Check
                                key={criminal.id}
                                type='checkbox'
                                id={criminal.id}
                                label={generateName(criminal)}
                                checked={checkedCriminals[criminal.id]}
                                onChange={() => {
                                    checkedCriminals[criminal.id] ^= 1;
                                    setCheckedCriminals([...checkedCriminals]);
                                }
                                }
                            />
                        ))
                    }
                </Form>
            </Modal.Body>
            <Modal.Footer>
                <Button onClick={addCriminals}>Set</Button>
            </Modal.Footer>
        </Modal>
    )
}

export default SetCriminalsModal