import {useState} from "react";
import {Button, Col, FormControl, Row} from "react-bootstrap";
import {sendComplaint} from "../http/complaintAPI";

export default function Complaint() {
    const [complaint, setComplaint] = useState('');
    const [response, setResponse] = useState('');

    function addComplaint() {
        if (complaint === '') {
            setResponse('Input complaint description');
            return;
        }
        sendComplaint(complaint).then(
            () => {
                setComplaint('');
                setResponse('Success');
            }
        ).catch(() => {
            setResponse('Error');
        });
    }

    return (
        <>
            <h3 className='mx-4 mb-4'>Send complaint</h3>
            <p className='m-3'>Please describe complaint</p>
            <Row className='align-items-end m-3'>
                <Col md={8}>
                    <FormControl as="textarea" rows={3} value={complaint} onChange={e => setComplaint(e.target.value)}/>
                </Col>
            </Row>
            <Button className='rounded-button m-3' onClick={addComplaint}>Post</Button>
            <p className='m-3'>{response}</p>

        </>
    )
}
