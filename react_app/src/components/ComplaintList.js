import React, {useEffect, useState} from "react";
import {deleteComplaint, getComplaints} from "../http/complaintAPI";
import ComplaintItem from "./ComplaintItem";
import {Container} from "react-bootstrap";

export default function ComplaintList() {
    const [complaints, setComplaints] = useState([]);
    const [isLoaded, setIsLoaded] = useState(false);

    useEffect(() => {
        getComplaints().then((complaints) => {
            setComplaints(complaints);
            setIsLoaded(true);
        })
    }, [])
    if (!isLoaded) {
        return <div/>
    }

    function removeComplaint(id) {
        deleteComplaint(id).then(() => {
            setComplaints(complaints.filter(complaint => complaint.id !== id));
        }).catch(() => {
        });
    }

    return (
        <>
            <Container className='col-md'>

                <h3>Complaints</h3>
                {
                    complaints.map((complaint, index) => {
                        return <ComplaintItem
                            complaint={complaint}
                            key={complaint.id}
                            isBlue={index % 2}
                            onDelete={() => removeComplaint(complaint.id)}
                        />
                    })
                }
            </Container>
        </>
    )
}
