import {Container, Row} from "react-bootstrap";
import {ReactComponent as Cross} from '../img/cross.svg';
import {generateName} from "../utils/formMessage";

export default function ComplaintItem({complaint, isBlue, onDelete}) {
    return (
        <div className='w-100 p-3 d-flex' style={{background: isBlue ? '#edf4f6' : 'white'}}>
            <Container className='ms-3'>
                <div className='d-flex justify-content-between'>
                    <div className=''>
                        <div style={{minWidth: 150, display: 'inline-block', textAlign: 'left'}}>
                            <b>{generateName(complaint.sender)}</b>
                        </div>
                    </div>
                    <div>
                        {complaint.sent.toLocaleString()}

                        <button className='svg ms-3' onClick={onDelete}>
                            <Cross/>
                        </button>

                    </div>
                </div>
                <Row>
                    {complaint.description}
                </Row>
            </Container>
        </div>
    )
}
